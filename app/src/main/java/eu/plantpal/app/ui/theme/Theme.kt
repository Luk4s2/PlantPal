package eu.plantpal.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightGreenColorScheme = lightColorScheme(
    primary = Color(0xFF30B432),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFC8E6C9),
    onPrimaryContainer = Color.Black,

    secondary = Color(0xFF73CB76),
    onSecondary = Color.White,

    background = Color(0xFFF9F9F9),
    onBackground = Color.Black,

    surface = Color.White,
    onSurface = Color.Black,

    error = Color(0xFFD32F2F),
    onError = Color.White
)

@Composable
fun PlantPalTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightGreenColorScheme,
        typography = Typography,
        content = content
    )
}
