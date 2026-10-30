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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ExerciseVisualType
import com.example.ui.components.ExerciseIllustration
import com.example.ui.components.PeakMindTopBar
import com.example.ui.components.PrimaryLimeButton
import com.example.ui.theme.TealSecondary

data class PelvicRelaxationItem(
    val title: String,
    val duration: String,
    val description: String,
    val visual: ExerciseVisualType,
    val icon: ImageVector
)

@Composable
fun PelvicFloorScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        PelvicRelaxationItem(
            "Diaphragmatic Breathing",
            "5 min",
            "Synchronize abdominal expansion to soften the pelvic base naturally.",
            ExerciseVisualType.DIAPHRAGMATIC_BREATHING,
            Icons.Default.Air
        ),
        PelvicRelaxationItem(
            "Pelvic-Floor Awareness",
            "5 min",
            "Identify involuntary tension and practice deep downward relaxation.",
            ExerciseVisualType.KEGEL_RELAXATION,
            Icons.Default.SelfImprovement
        ),
        PelvicRelaxationItem(
            "Pelvic Tilts",
            "5 min",
            "Subtle anterior-posterior sacral tilting to loosen tight hip rotators.",
            ExerciseVisualType.GLUTE_BRIDGE,
            Icons.Default.SwapVert
        ),
        PelvicRelaxationItem(
            "Cat-Cow",
            "5 min",
            "Fluid spinal mobilization to ease pelvic compression.",
            ExerciseVisualType.CAT_COW,
            Icons.Default.Pets
        ),
        PelvicRelaxationItem(
            "Child's Pose",
            "5 min",
            "Sacral opening posture with deep posterior diaphragmatic breathing.",
            ExerciseVisualType.CHILDS_POSE,
            Icons.Default.ChildCare
        ),
        PelvicRelaxationItem(
            "Hip Mobility",
            "5 min",
            "Relieve tension in hip flexors, adductors, and piriformis muscles.",
            ExerciseVisualType.LUNGE,
            Icons.Default.Accessibility
        )
    )

    var selectedItem by remember { mutableStateOf(items.first()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("pelvic_floor_screen")
    ) {
        PeakMindTopBar(
            title = "Pelvic-Floor Relaxation",
            onBack = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            // Selected Active Exercise Demonstration
            ExerciseIllustration(
                visualType = selectedItem.visual,
                height = 170.dp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(14.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedItem.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = selectedItem.duration,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = TealSecondary
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = selectedItem.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Relaxation Practices",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(10.dp))

            items.forEach { item ->
                val isCurrent = selectedItem.title == item.title
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { selectedItem = item },
                    shape = RoundedCornerShape(14.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(
                        width = if (isCurrent) 1.5.dp else 1.dp,
                        color = if (isCurrent) TealSecondary else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .background(TealSecondary.copy(alpha = 0.15f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = TealSecondary,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = item.duration,
                                style = MaterialTheme.typography.bodySmall,
                                color = TealSecondary,
                                fontWeight = FontWeight.SemiBold
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
