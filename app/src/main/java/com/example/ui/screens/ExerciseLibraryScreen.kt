package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Exercise
import com.example.data.ExerciseRepository
import com.example.ui.components.ExerciseIllustration
import com.example.ui.components.PeakMindTopBar
import com.example.ui.theme.LimeContainer
import com.example.ui.theme.LimePrimary
import com.example.ui.theme.TealSecondary

@Composable
fun ExerciseLibraryScreen(
    onSelectExercise: (Exercise) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    var favoritesOnly by remember { mutableStateOf(false) }

    val categories = listOf("All", "Full Body", "Cardio", "Warm-up", "Pelvic Floor", "Kegel Training")

    val exercises = remember(searchQuery, selectedCategory, favoritesOnly) {
        ExerciseRepository.allExercises.filter { ex ->
            val matchesCategory = selectedCategory == "All" || ex.category.contains(selectedCategory, ignoreCase = true)
            val matchesQuery = searchQuery.isEmpty() || ex.name.contains(searchQuery, ignoreCase = true) || ex.targetMuscles.any { it.contains(searchQuery, ignoreCase = true) }
            val matchesFavorite = !favoritesOnly || ex.isFavorite
            matchesCategory && matchesQuery && matchesFavorite
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("exercise_library_screen")
    ) {
        PeakMindTopBar(
            title = "Exercise Library",
            onBack = onBack,
            actions = {
                IconButton(onClick = { favoritesOnly = !favoritesOnly }) {
                    Icon(
                        imageVector = if (favoritesOnly) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Filter Favorites",
                        tint = if (favoritesOnly) Color(0xFFEF4444) else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )

        // Search Box
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search exercises...", color = MaterialTheme.colorScheme.onSurfaceVariant) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedBorderColor = LimePrimary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 6.dp)
                .testTag("search_exercises_input")
        )

        // Category Filter Chips
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                val isSelected = selectedCategory == category
                com.example.ui.components.PeakMindFilterChip(
                    label = category,
                    isSelected = isSelected,
                    onClick = { selectedCategory = category },
                    testTag = "filter_chip_$category"
                )
            }
        }

        // Exercise List
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(exercises) { exercise ->
                var isFav by remember { mutableStateOf(exercise.isFavorite) }
                val statsText = if (exercise.durationSeconds > 0) {
                    "${exercise.sets} sets • ${exercise.durationSeconds}s • ${exercise.restSeconds}s"
                } else {
                    "${exercise.sets} sets • ${exercise.reps} reps • ${exercise.restSeconds}s"
                }

                com.example.ui.components.PeakMindExerciseCard(
                    title = exercise.name,
                    categoryAndLevel = "${exercise.category} • ${exercise.level}",
                    stats = statsText,
                    isFavorite = isFav,
                    onFavoriteToggle = {
                        isFav = !isFav
                        exercise.isFavorite = isFav
                    },
                    onClick = { onSelectExercise(exercise) },
                    thumbnailContent = {
                        ExerciseIllustration(
                            visualType = exercise.visualType,
                            modifier = Modifier.fillMaxSize(),
                            height = 70.dp
                        )
                    },
                    testTag = "exercise_row_${exercise.id}"
                )
            }
        }
    }
}
