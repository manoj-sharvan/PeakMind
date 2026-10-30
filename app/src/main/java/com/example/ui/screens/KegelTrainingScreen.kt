package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ExerciseVisualType
import com.example.ui.components.ExerciseIllustration
import com.example.ui.components.PeakMindTopBar
import com.example.ui.components.PrimaryLimeButton
import com.example.ui.theme.LimeContainer
import com.example.ui.theme.LimePrimary
import com.example.ui.theme.TealSecondary
import kotlinx.coroutines.delay

data class KegelRoutineItem(
    val title: String,
    val subtitle: String,
    val holdTime: Int,
    val relaxTime: Int,
    val icon: ImageVector
)

@Composable
fun KegelTrainingScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isTrainerActive by remember { mutableStateOf(false) }
    var currentPhase by remember { mutableStateOf("Contract & Lift (3s)") }
    var phaseSeconds by remember { mutableIntStateOf(3) }
    var isContraction by remember { mutableStateOf(true) }

    val routines = listOf(
        KegelRoutineItem("Basic Slow Kegel", "Hold 5s • Relax 10s", 5, 10, Icons.Default.Favorite),
        KegelRoutineItem("Quick Kegel", "Quick contractions (1s/1s)", 1, 1, Icons.Default.Speed),
        KegelRoutineItem("Seated Kegel", "While sitting on chair", 3, 6, Icons.Default.Chair),
        KegelRoutineItem("Standing Kegel", "While standing upright", 3, 6, Icons.Default.Person),
        KegelRoutineItem("Supine Kegel", "Lying down on mat", 5, 10, Icons.Default.Hotel),
        KegelRoutineItem("Breathing Coordination", "With diaphragmatic breath", 4, 8, Icons.Default.Air)
    )

    LaunchedEffect(isTrainerActive, isContraction) {
        if (isTrainerActive) {
            val duration = if (isContraction) 3 else 6
            phaseSeconds = duration
            currentPhase = if (isContraction) "LIFT & HOLD" else "COMPLETELY RELAX"
            delay((duration * 1000).toLong())
            isContraction = !isContraction
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("kegel_training_screen")
    ) {
        PeakMindTopBar(
            title = "Kegel Training",
            onBack = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 6.dp)
        ) {
            // Interactive Pelvic Biofeedback Wave
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, LimePrimary.copy(alpha = 0.4f))
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Pelvic-Floor Pacer",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    val scale by animateFloatAsState(
                        targetValue = if (isContraction) 1.25f else 0.85f,
                        animationSpec = tween(durationMillis = if (isContraction) 3000 else 6000),
                        label = "pacerScale"
                    )

                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .scale(scale)
                            .background(
                                if (isContraction) LimePrimary.copy(alpha = 0.25f) else TealSecondary.copy(alpha = 0.2f),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .background(
                                    if (isContraction) LimePrimary else TealSecondary,
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (isContraction) "HOLD" else "RELAX",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = currentPhase,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isContraction) LimePrimary else TealSecondary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    PrimaryLimeButton(
                        text = if (isTrainerActive) "Pause Pacer" else "Start Guided Pacer",
                        onClick = { isTrainerActive = !isTrainerActive },
                        modifier = Modifier.height(44.dp),
                        testTag = "kegel_pacer_toggle"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Pelvic Anatomy Diagram Card
            ExerciseIllustration(
                visualType = ExerciseVisualType.PELVIC_ANATOMY,
                height = 140.dp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Training Routines",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(10.dp))

            routines.forEach { routine ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable {
                            isContraction = true
                            isTrainerActive = true
                        },
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(TealSecondary.copy(alpha = 0.15f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = routine.icon,
                                contentDescription = null,
                                tint = TealSecondary,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = routine.title,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = routine.subtitle,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.outline,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
