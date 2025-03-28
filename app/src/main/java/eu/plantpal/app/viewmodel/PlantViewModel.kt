package eu.plantpal.app.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.plantpal.app.model.Plant
import eu.plantpal.app.repository.PlantRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
    private val repository: PlantRepository
) : ViewModel() {

    private val _plants = MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>> = _plants

    private val _currentPage = mutableIntStateOf(1)
    val currentPage: State<Int> = _currentPage

    private val _hasNextPage = mutableStateOf(true)
    val hasNextPage: State<Boolean> = _hasNextPage

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private val _retryAfter = mutableStateOf<Long?>(null)
    val retryAfter: State<Long?> = _retryAfter

    // LRU cache 10 pages
    private val cache = object : LinkedHashMap<Int, List<Plant>>(10, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, List<Plant>>): Boolean {
            return size > 10
        }
    }

    init {
        fetchPlants()
    }

    private fun fetchPlants() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            _retryAfter.value = null

            val page = _currentPage.intValue

            // Use cached page if available
            cache[page]?.let {
                _plants.value = it
                _isLoading.value = false
                return@launch
            }

            try {
                val result = repository.fetchPlants(page)
                Log.d("PlantList", "Loaded JSON: $result")
                _plants.value = result
                _hasNextPage.value = result.isNotEmpty()

                cache[page] = result
            } catch (e: HttpException) {
                if (e.code() == 429) {
                    val retryAfterSeconds = extractRetryAfterFromBody(e.response()?.errorBody())
                    if (retryAfterSeconds != null) {
                        _retryAfter.value = retryAfterSeconds
                        _errorMessage.value =
                            "You've hit the rate limit. Retry in ${retryAfterSeconds}s"
                        startCountdown(retryAfterSeconds)
                    } else {
                        _errorMessage.value = "Rate limit exceeded. Please try again later."
                    }
                } else {
                    _errorMessage.value = "Server error: ${e.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load data. Please try again."
                Log.e("PlantViewModel", "Error fetching plants", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun extractRetryAfterFromBody(errorBody: ResponseBody?): Long? {
        return try {
            val errorString = errorBody?.string() ?: return null
            val json = JSONObject(errorString)
            json.optLong("Retry-After")
        } catch (e: Exception) {
            Log.e("PlantViewModel", "Failed to parse Retry-After from body", e)
            null
        }
    }

    private fun startCountdown(seconds: Long) {
        viewModelScope.launch {
            var timeLeft = seconds
            while (timeLeft > 0) {
                delay(1000)
                timeLeft--
                _retryAfter.value = timeLeft
                _errorMessage.value = "You've hit the rate limit. Retry in ${timeLeft}s"
            }
            _retryAfter.value = null
            _errorMessage.value = null
            fetchPlants()
        }
    }

    fun retryFetch() {
        fetchPlants()
    }

    fun nextPage() {
        if (_hasNextPage.value && !_isLoading.value) {
            _currentPage.intValue++
            fetchPlants()
        }
    }

    fun previousPage() {
        if (_currentPage.intValue > 1 && !_isLoading.value) {
            _currentPage.intValue--
            fetchPlants()
        }
    }
}
