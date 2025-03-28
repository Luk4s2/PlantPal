package eu.plantpal.app.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.plantpal.app.viewmodel.PlantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantListScreen(onPlantClick: (Int) -> Unit, viewModel: PlantViewModel = viewModel()) {
    val plants by viewModel.plants.collectAsState()
    val currentPage by viewModel.currentPage
    val hasNextPage by viewModel.hasNextPage
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage
    val retryAfter by viewModel.retryAfter
    val showButtons = errorMessage == null

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "PlantPal",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Crossfade(
                    targetState = Triple(isLoading, errorMessage, plants),
                    animationSpec = tween(300),
                    label = "fade-content"
                ) { (loading, error, content) ->
                    when {
                        loading -> {
                            Box(modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                        }

                        error != null -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = if (retryAfter != null) {
                                            "You’ve hit the rate limit. Retry in ${retryAfter}s"
                                        } else {
                                            error
                                        },
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.error,
                                        textAlign = TextAlign.Center
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Button(
                                        onClick = { viewModel.retryFetch() },
                                        enabled = retryAfter == null
                                    ) {
                                        Text("Try Again")
                                    }
                                }
                            }
                        }

                        else -> {
                            LazyColumn {
                                items(content) { plant ->
                                    PlantCard(
                                        plant = plant,
                                        onPlantClick = onPlantClick
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if (showButtons) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { viewModel.previousPage() },
                        enabled = currentPage > 1 && !isLoading,
                        modifier = Modifier
                            .weight(1f)
                            .height(35.dp)
                    ) {
                        Text(
                            text = "Previous",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.width(65.dp))

                    Button(
                        onClick = { viewModel.nextPage() },
                        enabled = hasNextPage && !isLoading,
                        modifier = Modifier
                            .weight(1f)
                            .height(35.dp)
                    ) {
                        Text(
                            text = "Next",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
