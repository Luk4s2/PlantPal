package eu.plantpal.app.repository

import eu.plantpal.app.model.Plant
import eu.plantpal.app.model.PlantDetail

interface IPlantRepository {
    suspend fun fetchPlants(page: Int): List<Plant>
    suspend fun fetchPlantDetail(plantId: Int): PlantDetail
}
