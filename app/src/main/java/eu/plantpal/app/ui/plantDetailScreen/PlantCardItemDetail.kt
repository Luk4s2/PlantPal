package eu.plantpal.app.ui.plantDetailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetailItem(title: String, value: String?) {
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
