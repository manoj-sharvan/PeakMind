package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.UserProfileManager
import com.example.ui.components.PeakMindMetricCard
import com.example.ui.components.PeakMindProgressBar
import com.example.ui.components.PeakMindWorkoutCard
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.LimeOnPrimary
import com.example.ui.theme.LimePrimary
import com.example.ui.theme.OrangeTertiary
import com.example.ui.theme.PurpleAccent
import com.example.ui.theme.TealSecondary

@Composable
fun HomeScreen(
    onStartWorkout: () -> Unit,
    onNavigateCategory: (String) -> Unit,
    onOpenProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    val profile by UserProfileManager.userProfile.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("home_screen")
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Good Morning,",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${profile.name} \uD83D\uDC51",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { /* Notification action */ },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                // User Avatar
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(listOf(LimePrimary, TealSecondary))
                        )
                        .clickable { onOpenProfile() }
                        .testTag("profile_avatar"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = profile.name.take(1),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // Today's Workout Hero Card
            PeakMindWorkoutCard(
                title = "Full Body (${profile.fitnessLevel})",
                duration = "30 min",
                onStartWorkout = onStartWorkout,
                testTag = "start_workout_button"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Weight Metrics Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PeakMindMetricCard(
                    label = "Current Weight",
                    value = "${profile.weightKg.toInt()} kg",
                    valueColor = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                    testTag = "metric_current_weight"
                )

                PeakMindMetricCard(
                    label = "Target Weight",
                    value = "${profile.targetWeightKg.toInt()} kg",
                    valueColor = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.weight(1f),
                    testTag = "metric_target_weight"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 6 Month Progress Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = com.example.ui.theme.PeakMindShapes.cardSmall,
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.45f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "6 Month Progress",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Month ${profile.currentMonth} of ${profile.totalMonths}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "17%",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    PeakMindProgressBar(targetProgress = 0.17f)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Weekly Stats Tiles
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PeakMindMetricCard(
                    label = "Weekly Workouts",
                    value = "${profile.weeklyWorkoutsDone}/${profile.weeklyWorkoutsGoal}",
                    valueColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f),
                    testTag = "metric_weekly_workouts"
                )

                PeakMindMetricCard(
                    label = "Active Minutes",
                    value = "${profile.activeMinutes} min",
                    valueColor = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.weight(1f),
                    testTag = "metric_active_minutes"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Quick Category Shortcuts
            Text(
                text = "Training Focus",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickFocusButton(
                    title = "Kegel",
                    icon = Icons.Default.Favorite,
                    tint = LimePrimary,
                    onClick = { onNavigateCategory("kegel") },
                    modifier = Modifier.weight(1f)
                )
                QuickFocusButton(
                    title = "Pelvic Floor",
                    icon = Icons.Default.ElectricBolt,
                    tint = TealSecondary,
                    onClick = { onNavigateCategory("pelvic_floor") },
                    modifier = Modifier.weight(1f)
                )
                QuickFocusButton(
                    title = "Wellness",
                    icon = Icons.Default.SelfImprovement,
                    tint = PurpleAccent,
                    onClick = { onNavigateCategory("sexual_wellness") },
                    modifier = Modifier.weight(1f)
                )
                QuickFocusButton(
                    title = "Heat Care",
                    icon = Icons.Default.WaterDrop,
                    tint = OrangeTertiary,
                    onClick = { onNavigateCategory("body_heat") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Composable
private fun QuickFocusButton(
    title: String,
    icon: ImageVector,
    tint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .height(82.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(tint.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
        }
    }
}
