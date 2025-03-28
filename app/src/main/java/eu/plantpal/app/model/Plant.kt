package eu.plantpal.app.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Plant(
    @Json(name = "id") val id: Int,
    @Json(name = "common_name") val commonName: String?,
    @Json(name = "scientific_name") val scientificName: List<String>,
    @Json(name = "watering") val watering: String?,
    @Json(name = "sunlight") val sunlight: List<String>?,
    @Json(name = "default_image") val image: Image?
)

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "original_url") val url: String?
)
