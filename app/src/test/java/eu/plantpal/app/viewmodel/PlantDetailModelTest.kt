package eu.plantpal.app.viewmodel

import app.cash.turbine.test
import eu.plantpal.app.model.DefaultImage
import eu.plantpal.app.model.PlantDetail
import eu.plantpal.app.repository.IPlantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PlantDetailModelTest {

	private val testDispatcher = StandardTestDispatcher()
	private lateinit var repository: IPlantRepository
	private lateinit var viewModel: PlantDetailModel

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

	@Before
	fun setUp() {
		Dispatchers.setMain(testDispatcher)
		repository = mock()
		viewModel = PlantDetailModel(repository, testDispatcher)
	}

	@After
	fun tearDown() {
		Dispatchers.resetMain()
	}

	@Test
	fun `fetchPlantDetail updates state with data`() = runTest {
		whenever(repository.fetchPlantDetail(1)).thenReturn(samplePlant)

		viewModel.plantDetail.test {
			val initial = awaitItem()
			assertNull(initial)

			launch { viewModel.fetchPlantDetail(1) }
			advanceUntilIdle()

			val result = awaitItem()
			assertEquals(samplePlant, result)
			cancelAndIgnoreRemainingEvents()
		}
	}
}
