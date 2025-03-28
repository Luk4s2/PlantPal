package eu.plantpal.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import eu.plantpal.app.ui.navigation.PlantApp
import eu.plantpal.app.ui.theme.PlantPalTheme

@AndroidEntryPoint
class PlantPal : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PlantPalTheme {
				PlantApp()
			}
		}
	}
}
