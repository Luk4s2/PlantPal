package eu.plantpal.app.util

/**
 * Capitalizes the first letter of each word in the string.
 */
fun String.capitalizeWords(): String {
    return this.lowercase()
        .split(" ")
        .joinToString(" ") { word ->
            word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
}
