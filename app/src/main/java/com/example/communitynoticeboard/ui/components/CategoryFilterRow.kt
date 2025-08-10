package com.example.communitynoticeboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoryFilterRow(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val categories = listOf("All", "Urgent", "Info", "Event", "General")

    // Define category colors
    val categoryColors = mapOf(
        "All" to Color.LightGray.copy(alpha = 0.4f),
        "Urgent" to Color.Red.copy(alpha = 0.4f),
        "Info" to Color.Yellow.copy(alpha = 0.4f),
        "Event" to Color.Green.copy(alpha = 0.4f),
        "General" to Color.Gray.copy(alpha = 0.4f)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        categories.forEach { category ->
            FilterChip(
                selected = selectedCategory.equals(category, ignoreCase = true),
                onClick = { onCategorySelected(category) },
                label = { Text(category) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = categoryColors[category]
                        ?: Color.Gray.copy(alpha = 0.3f)
                )
            )
        }

    }
}