package eu.plantpal.app.repository

import eu.plantpal.app.model.Plant
import eu.plantpal.app.model.PlantDetail
import eu.plantpal.app.network.PlantApiService
import javax.inject.Inject

class PlantRepository @Inject constructor(
    private val api: PlantApiService
) : IPlantRepository {

    override suspend fun fetchPlants(page: Int): List<Plant> {
        return api.getPlants(page = page).data
    }

    override suspend fun fetchPlantDetail(plantId: Int): PlantDetail {
        return api.getPlantDetails(plantId)
    }
}
