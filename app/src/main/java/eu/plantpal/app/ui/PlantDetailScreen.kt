package eu.plantpal.app.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import eu.plantpal.app.util.capitalizeWords
import eu.plantpal.app.viewmodel.PlantDetailModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantDetailScreen(
	plantId: Int,
	onBackClick: () -> Unit,
	viewModel: PlantDetailModel = hiltViewModel()
) {
	var isDialogOpen by remember { mutableStateOf(false) }
	val plant = viewModel.plantDetail.collectAsState().value

	LaunchedEffect(plantId) {
		viewModel.fetchPlantDetail(plantId)
	}

	Scaffold(
		topBar = {
			TopAppBar(
				colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
					containerColor = MaterialTheme.colorScheme.background
				),
				title = {
					Text(
						text = plant?.commonName ?: "Plant Detail",
						style = MaterialTheme.typography.titleLarge,
						color = MaterialTheme.colorScheme.primary
					)
				},
				navigationIcon = {
					IconButton(onClick = onBackClick) {
						Icon(
							Icons.AutoMirrored.Filled.ArrowBack,
							contentDescription = "Back",
							tint = MaterialTheme.colorScheme.primary
						)
					}
				}
			)
		}
	) { padding ->
		plant?.let {
			Column(
				modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
				verticalArrangement = Arrangement.spacedBy(12.dp)
			) {
				it.defaultImage?.regularUrl?.let { img ->
					Card(
						colors = CardDefaults.cardColors(
							containerColor = MaterialTheme.colorScheme.primaryContainer
						),
						modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isDialogOpen = true },
						shape = RoundedCornerShape(16.dp),
						border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)

					) {
						Image(
							painter = rememberAsyncImagePainter(img),
							contentDescription = it.commonName,
							modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f)
                                .clip(RoundedCornerShape(16.dp))
						)
					}
				}

				DetailItem("🌱 Common Name", it.commonName?.capitalizeWords())
				DetailItem(
					"🔬 Scientific Name",
					it.scientificName?.joinToString()?.capitalizeWords()
				)
				DetailItem("🌍 Origin", it.origin?.joinToString()?.capitalizeWords())
				DetailItem("🌿 Type", it.type?.capitalizeWords())
				DetailItem("📏 Dimension", it.dimension?.capitalizeWords())
				DetailItem("🌞 Sunlight", it.sunlight?.joinToString()?.capitalizeWords())
				DetailItem("💧 Watering", it.watering?.capitalizeWords())
				DetailItem("♻️ Cycle", it.cycle?.capitalizeWords())
				DetailItem("🌱 Propagation", it.propagation?.joinToString()?.capitalizeWords())
				DetailItem("🧬 Family", it.family?.capitalizeWords())
				DetailItem("🧼 Maintenance", it.maintenance?.capitalizeWords())
				DetailItem("🧪 Growth Rate", it.growthRate?.capitalizeWords())
				DetailItem("📖 Description", it.description)
				DetailItem("💠 Flower Color", it.flowerColor?.capitalizeWords())
				DetailItem("🍂 Leaf Color", it.leafColor?.joinToString()?.capitalizeWords())
				DetailItem("🍓 Fruit Color", it.fruitColor?.joinToString()?.capitalizeWords())
				DetailItem("🧃 Fruit Taste", it.edibleFruitTasteProfile?.capitalizeWords())
				DetailItem("🥗 Cuisine", if (it.cuisine == true) "Yes" else null)
				DetailItem("💊 Medicinal", if (it.medicinal == true) "Yes" else null)
				DetailItem(
					"⚠️ Poisonous to Humans", if (it.poisonousToHumans == true) "Yes" else null
				)
				DetailItem("🐾 Poisonous to Pets", if (it.poisonousToPets == true) "Yes" else null)
			}
		} ?: Box(
			modifier = Modifier
                .fillMaxSize()
                .padding(padding),
			contentAlignment = Alignment.Center
		) {
			CircularProgressIndicator()
		}
	}

	if (isDialogOpen) {
		FullscreenImageDialog(
			imageUrl = plant?.defaultImage?.originalUrl,
			onDismiss = { isDialogOpen = false }
		)
	}
}

@Composable
private fun DetailItem(title: String, value: String?) {
	value?.takeIf { it.isNotBlank() }?.let {
		Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
			Text(
				text = title,
				style = MaterialTheme.typography.labelLarge,
				fontWeight = FontWeight.SemiBold
			)
			Text(
				text = it,
				style = MaterialTheme.typography.bodyLarge
			)
		}
	}
}
