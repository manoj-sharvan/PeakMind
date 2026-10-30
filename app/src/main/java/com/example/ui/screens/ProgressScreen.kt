package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.UserProfileManager
import com.example.ui.components.PeakMindTopBar
import com.example.ui.theme.LimeContainer
import com.example.ui.theme.LimeOnPrimary
import com.example.ui.theme.LimePrimary
import com.example.ui.theme.OrangeTertiary
import com.example.ui.theme.TealSecondary

@Composable
fun ProgressScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val profile by UserProfileManager.userProfile.collectAsState()
    var selectedTab by remember { mutableStateOf("Weight") }

    val records = if (selectedTab == "Weight") UserProfileManager.weightHistory else UserProfileManager.waistHistory

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("progress_screen")
    ) {
        PeakMindTopBar(
            title = "Your Progress",
            onBack = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 6.dp)
        ) {
            // Tabs: Weight, Waist, Activity
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Weight", "Waist", "Activity").forEach { tab ->
                    com.example.ui.components.PeakMindFilterChip(
                        label = tab,
                        isSelected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        modifier = Modifier.weight(1f),
                        testTag = "progress_tab_$tab"
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Chart Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = if (selectedTab == "Weight") "Weight Trend" else if (selectedTab == "Waist") "Waist Circumference" else "Activity Trend",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = if (selectedTab == "Weight") "Goal: 70 kg (Milestone 1: 83.5 kg)" else if (selectedTab == "Waist") "Target: <90 cm" else "5 days/week target",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Text(
                            text = if (selectedTab == "Weight") "-6.5 kg" else "-6.2 cm",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = LimePrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Compose Canvas Trend Chart
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val w = size.width
                            val h = size.height

                            // Draw subtle horizontal grid lines
                            for (i in 0..4) {
                                val y = h * (0.15f + i * 0.18f)
                                drawLine(
                                    color = Color(0x22475569),
                                    start = Offset(0f, y),
                                    end = Offset(w, y),
                                    strokeWidth = 1.dp.toPx()
                                )
                            }

                            // Plot data points
                            val points = listOf(
                                Offset(w * 0.08f, h * 0.20f),
                                Offset(w * 0.28f, h * 0.35f),
                                Offset(w * 0.48f, h * 0.48f),
                                Offset(w * 0.68f, h * 0.62f),
                                Offset(w * 0.88f, h * 0.75f)
                            )

                            // Gradient Fill Area
                            val fillPath = Path().apply {
                                moveTo(points.first().x, points.first().y)
                                for (i in 1 until points.size) {
                                    val prev = points[i - 1]
                                    val curr = points[i]
                                    val cx = (prev.x + curr.x) / 2
                                    cubicTo(cx, prev.y, cx, curr.y, curr.x, curr.y)
                                }
                                lineTo(points.last().x, h)
                                lineTo(points.first().x, h)
                                close()
                            }

                            drawPath(
                                path = fillPath,
                                brush = Brush.verticalGradient(
                                    colors = listOf(LimePrimary.copy(alpha = 0.28f), Color.Transparent)
                                )
                            )

                            // Trend Line
                            val linePath = Path().apply {
                                moveTo(points.first().x, points.first().y)
                                for (i in 1 until points.size) {
                                    val prev = points[i - 1]
                                    val curr = points[i]
                                    val cx = (prev.x + curr.x) / 2
                                    cubicTo(cx, prev.y, cx, curr.y, curr.x, curr.y)
                                }
                            }

                            drawPath(
                                path = linePath,
                                color = LimePrimary,
                                style = Stroke(width = 3.5.dp.toPx(), cap = StrokeCap.Round)
                            )

                            // Point Dots
                            points.forEach { pt ->
                                drawCircle(color = Color(0xFF0F172A), radius = 6.dp.toPx(), center = pt)
                                drawCircle(color = LimePrimary, radius = 4.dp.toPx(), center = pt)
                            }
                        }
                    }

                    // X-Axis Labels
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        records.forEach { record ->
                            Text(
                                text = record.dateLabel,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Weekly Activity Bar Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Weekly Activity",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "${profile.weeklyWorkoutsDone}/5 workouts",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = LimePrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Day Bars: Mon, Tue, Wed, Thu, Fri, Sat, Sun
                    val days = listOf("M", "T", "W", "T", "F", "S", "S")
                    val completed = listOf(true, true, true, false, false, false, false)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        days.forEachIndexed { i, day ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(
                                    modifier = Modifier
                                        .size(width = 28.dp, height = if (completed[i]) 56.dp else 24.dp)
                                        .background(
                                            if (completed[i]) LimePrimary else Color(0xFF262D38),
                                            RoundedCornerShape(6.dp)
                                        )
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = day,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Meditation Streak Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, TealSecondary.copy(alpha = 0.4f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .background(TealSecondary.copy(alpha = 0.15f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.SelfImprovement,
                                contentDescription = null,
                                tint = TealSecondary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text(
                                text = "Meditation Streak",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Daily pelvic relaxation habit",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Text(
                        text = "${profile.meditationStreakDays} days",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TealSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
