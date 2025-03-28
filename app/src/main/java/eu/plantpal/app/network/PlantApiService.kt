package eu.plantpal.app.network

import com.squareup.moshi.JsonClass
import eu.plantpal.app.model.Plant
import eu.plantpal.app.model.PlantDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@JsonClass(generateAdapter = true)
data class PlantResponse(
    val data: List<Plant>
)

interface PlantApiService {
    @GET("species-list")
    suspend fun getPlants(
        @Query("key") apiKey: String = "sk-HR3567e5b712439b69458",
        @Query("page") page: Int = 1
    ): PlantResponse

    @GET("species/details/{plant_id}")
    suspend fun getPlantDetails(
        @Path("plant_id") plantId: Int,
        @Query("key") apiKey: String = "sk-HR3567e5b712439b69458",
    ): PlantDetail
}
