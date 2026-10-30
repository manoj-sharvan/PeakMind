package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.ui.components.ExerciseStepByStepBar
import com.example.ui.components.PeakMindTopBar
import com.example.ui.components.PrimaryLimeButton
import com.example.ui.components.SecondaryDarkButton
import com.example.ui.components.WorkoutMetricPill
import com.example.ui.theme.LimeContainer
import com.example.ui.theme.LimePrimary
import com.example.ui.theme.TealSecondary
import kotlinx.coroutines.delay

@Composable
fun ActiveWorkoutScreen(
    exerciseId: String = "ex_squat",
    onStartRest: () -> Unit,
    onCompleteWorkout: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val exercise = remember(exerciseId) {
        ExerciseRepository.allExercises.find { it.id == exerciseId } ?: ExerciseRepository.allExercises.first()
    }

    var currentSet by remember { mutableIntStateOf(1) }
    var isTimerRunning by remember { mutableStateOf(false) }
    var elapsedSeconds by remember { mutableIntStateOf(0) }
    var isFavorite by remember { mutableStateOf(exercise.isFavorite) }
    var selectedLevel by remember { mutableStateOf(exercise.level) }

    LaunchedEffect(isTimerRunning) {
        while (isTimerRunning) {
            delay(1000)
            elapsedSeconds++
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("active_workout_screen")
    ) {
        PeakMindTopBar(
            title = "Full Body Workout",
            onBack = onBack,
            actions = {
                IconButton(onClick = { isFavorite = !isFavorite }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color(0xFFEF4444) else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { /* More options */ }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Options",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            // Large Demonstration Visual
            ExerciseIllustration(
                visualType = exercise.visualType,
                height = 200.dp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Title & Target Muscle Tags
            Text(
                text = exercise.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                exercise.targetMuscles.forEach { muscle ->
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
                    ) {
                        Text(
                            text = muscle,
                            style = MaterialTheme.typography.labelSmall,
                            color = TealSecondary,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Level Switcher Chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Beginner", "Intermediate", "Advanced").forEach { level ->
                    val isSelected = selectedLevel == level
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selectedLevel = level },
                        shape = RoundedCornerShape(10.dp),
                        color = if (isSelected) LimeContainer else MaterialTheme.colorScheme.surfaceVariant,
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (isSelected) LimePrimary else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                        )
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(vertical = 6.dp)
                        ) {
                            Text(
                                text = level,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) LimePrimary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Stats Pill Row (Sets, Reps, Rest)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                WorkoutMetricPill(
                    label = "Set",
                    value = "$currentSet / ${exercise.sets}",
                    modifier = Modifier.weight(1f)
                )
                WorkoutMetricPill(
                    label = if (exercise.durationSeconds > 0) "Seconds" else "Reps",
                    value = if (exercise.durationSeconds > 0) "${exercise.durationSeconds}s" else "${exercise.reps}",
                    modifier = Modifier.weight(1f)
                )
                WorkoutMetricPill(
                    label = "Rest",
                    value = "${exercise.restSeconds}s",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Movement Breakdown (Step 1 -> Step 2 -> Step 3)
            ExerciseStepByStepBar()

            Spacer(modifier = Modifier.height(16.dp))

            // Step-by-Step Instructions Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "Instructions:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    exercise.instructions.forEachIndexed { index, step ->
                        Row(modifier = Modifier.padding(vertical = 3.dp)) {
                            Text(
                                text = "${index + 1}. ",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = LimePrimary
                            )
                            Text(
                                text = step,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 18.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Breathing guide
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0x1A06B6D4), RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Air,
                            contentDescription = null,
                            tint = TealSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Breathing: ${exercise.breathing}",
                            style = MaterialTheme.typography.labelSmall,
                            color = TealSecondary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Bottom Workout Action Bar
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { isTimerRunning = !isTimerRunning },
                    modifier = Modifier
                        .size(48.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                ) {
                    Icon(
                        imageVector = if (isTimerRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = "Pause/Resume",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }

                PrimaryLimeButton(
                    text = if (currentSet >= exercise.sets) "Complete Workout" else "Complete Set & Rest",
                    onClick = {
                        if (currentSet < exercise.sets) {
                            currentSet++
                            onStartRest()
                        } else {
                            onCompleteWorkout()
                        }
                    },
                    modifier = Modifier.weight(1f),
                    testTag = "complete_set_button"
                )
            }
        }
    }
}
