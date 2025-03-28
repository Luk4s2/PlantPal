package eu.plantpal.app.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlantDetail(
	val id: Int?,
	@Json(name = "common_name") val commonName: String?,
	@Json(name = "scientific_name") val scientificName: List<String>?,
	@Json(name = "other_name") val otherName: List<String>?,
	val family: String?,
	val origin: List<String>?,
	val type: String?,
	val dimension: String?,
	val dimensions: Dimensions?,
	val cycle: String?,
	val attracts: List<String>?,
	val propagation: List<String>?,
	val hardiness: Hardiness?,
	@Json(name = "hardiness_location") val hardinessLocation: HardinessLocation?,
	val watering: String?,
	@Json(name = "watering_period") val wateringPeriod: String?,
	@Json(name = "watering_general_benchmark") val wateringGeneralBenchmark: Benchmark?,
	@Json(name = "plant_anatomy") val plantAnatomy: List<PlantAnatomy>?,
	val sunlight: List<String>?,
	@Json(name = "pruning_month") val pruningMonth: List<String>?,
	val seeds: Boolean?,
	val maintenance: String?,
	@Json(name = "care-guides") val careGuides: String?,
	val soil: List<String>?,
	@Json(name = "growth_rate") val growthRate: String?,
	@Json(name = "drought_tolerant") val droughtTolerant: Boolean?,
	@Json(name = "salt_tolerant") val saltTolerant: Boolean?,
	val thorny: Boolean?,
	val invasive: Boolean?,
	val tropical: Boolean?,
	val indoor: Boolean?,
	@Json(name = "care_level") val careLevel: String?,
	@Json(name = "pest_susceptibility") val pestSusceptibility: List<String>?,
	@Json(name = "pest_susceptibility_api") val pestSusceptibilityApi: String?,
	val flowers: Boolean?,
	@Json(name = "flowering_season") val floweringSeason: String?,
	@Json(name = "flower_color") val flowerColor: String?,
	val cones: Boolean?,
	val fruits: Boolean?,
	@Json(name = "edible_fruit") val edibleFruit: Boolean?,
	@Json(name = "edible_fruit_taste_profile") val edibleFruitTasteProfile: String?,
	@Json(name = "fruit_nutritional_value") val fruitNutritionalValue: String?,
	@Json(name = "fruit_color") val fruitColor: List<String>?,
	@Json(name = "harvest_season") val harvestSeason: String?,
	val leaf: Boolean?,
	@Json(name = "leaf_color") val leafColor: List<String>?,
	@Json(name = "edible_leaf") val edibleLeaf: Boolean?,
	val cuisine: Boolean?,
	val medicinal: Boolean?,
	@Json(name = "poisonous_to_humans") val poisonousToHumans: Boolean?,
	@Json(name = "poisonous_to_pets") val poisonousToPets: Boolean?,
	val description: String?,
	@Json(name = "default_image") val defaultImage: DefaultImage?,
	@Json(name = "other_images") val otherImages: String?
)

@JsonClass(generateAdapter = true)
data class Dimensions(
	val type: String?,
	@Json(name = "min_value") val minValue: Int?,
	@Json(name = "max_value") val maxValue: Int?,
	val unit: String?
)

@JsonClass(generateAdapter = true)
data class Hardiness(
	val min: String?,
	val max: String?
)

@JsonClass(generateAdapter = true)
data class HardinessLocation(
	@Json(name = "full_url") val fullUrl: String?,
	@Json(name = "full_iframe") val fullIframe: String?
)

@JsonClass(generateAdapter = true)
data class WaterRequirement(
	val unit: String?,
	val value: String?
)

@JsonClass(generateAdapter = true)
data class Benchmark(
	val value: String?,
	val unit: String?
)

@JsonClass(generateAdapter = true)
data class PlantAnatomy(
	val part: String?,
	val color: List<String>?
)

@JsonClass(generateAdapter = true)
data class DefaultImage(
	val license: Int?,
	@Json(name = "license_name") val licenseName: String?,
	@Json(name = "license_url") val licenseUrl: String?,
	@Json(name = "original_url") val originalUrl: String?,
	@Json(name = "regular_url") val regularUrl: String?,
	@Json(name = "medium_url") val mediumUrl: String?,
	@Json(name = "small_url") val smallUrl: String?,
	val thumbnail: String?
)
