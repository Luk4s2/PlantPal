package eu.plantpal.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.plantpal.app.model.PlantDetail
import eu.plantpal.app.repository.IPlantRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Target(AnnotationTarget.CLASS)
annotation class OpenForTesting

@HiltViewModel
@OpenForTesting
class PlantDetailModel @Inject constructor(
    private val repository: IPlantRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _plantDetail = MutableStateFlow<PlantDetail?>(null)
    val plantDetail: StateFlow<PlantDetail?> = _plantDetail

    fun fetchPlantDetail(plantId: Int) {
        viewModelScope.launch(dispatcher) {
            try {
                val detail = repository.fetchPlantDetail(plantId)
                _plantDetail.value = detail
                println("PlantList Loaded JSON: $detail")
            } catch (e: Exception) {
                println("Error fetching plant detail: ${e.message}")
            }
        }
    }
}
