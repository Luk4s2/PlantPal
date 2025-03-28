package eu.plantpal.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.test.ext.junit.runners.AndroidJUnit4
import eu.plantpal.app.model.DefaultImage
import eu.plantpal.app.model.PlantDetail
import eu.plantpal.app.ui.plantDetailScreen.PlantDetailScreen
import eu.plantpal.app.ui.theme.PlantPalTheme
import eu.plantpal.app.viewmodel.PlantDetailModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class PlantDetailScreenTest {

	@get:Rule
	val composeTestRule = createComposeRule()

	private val samplePlant = PlantDetail(
		id = 1,
		commonName = "Test Plant",
		scientificName = listOf("Testus plantus"),
		origin = listOf("Europe"),
		type = "Shrub",
		dimension = "Height: 30 feet",
		dimensions = null,
		cycle = "Perennial",
		attracts = null,
		propagation = listOf("Seed"),
		hardiness = null,
		hardinessLocation = null,
		watering = "Moderate",
		wateringPeriod = null,
		wateringGeneralBenchmark = null,
		plantAnatomy = null,
		sunlight = listOf("Full Sun"),
		pruningMonth = null,
		seeds = null,
		maintenance = "Low",
		careGuides = null,
		soil = null,
		growthRate = "Fast",
		droughtTolerant = true,
		saltTolerant = false,
		thorny = false,
		invasive = false,
		tropical = false,
		indoor = false,
		careLevel = "Easy",
		pestSusceptibility = null,
		pestSusceptibilityApi = null,
		flowers = false,
		floweringSeason = null,
		flowerColor = null,
		cones = true,
		fruits = false,
		edibleFruit = false,
		edibleFruitTasteProfile = null,
		fruitNutritionalValue = null,
		fruitColor = null,
		harvestSeason = null,
		leaf = true,
		leafColor = listOf("Green"),
		edibleLeaf = false,
		cuisine = false,
		medicinal = false,
		poisonousToHumans = false,
		poisonousToPets = false,
		description = "A test description.",
		defaultImage = DefaultImage(
			license = 0,
			licenseName = "",
			licenseUrl = "",
			originalUrl = "",
			regularUrl = "",
			mediumUrl = "",
			smallUrl = "",
			thumbnail = ""
		),
		otherImages = null,
		otherName = listOf(""),
		family = ""
	)

	@Test
	fun testPlantDetailScreenRendersCorrectly() {
		val mockViewModel = Mockito.mock(PlantDetailModel::class.java)
		Mockito.`when`(mockViewModel.plantDetail).thenReturn(MutableStateFlow(samplePlant))

		composeTestRule.setContent {
			PlantPalTheme {
				PlantDetailScreen(
					plantId = 1,
					onBackClick = {},
					viewModel = mockViewModel
				)
			}
		}


		composeTestRule.onAllNodesWithText("Test Plant", ignoreCase = true).onFirst()
			.assertIsDisplayed()
		composeTestRule.onAllNodesWithText("Moderate").onFirst().assertIsDisplayed()
		composeTestRule.onAllNodesWithText("Shrub").onFirst().assertIsDisplayed()
		composeTestRule.onAllNodesWithText("Perennial").onFirst().assertIsDisplayed()
	}
}
