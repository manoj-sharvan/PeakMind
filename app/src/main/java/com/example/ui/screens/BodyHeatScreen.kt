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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.ui.components.PeakMindTopBar
import com.example.ui.theme.LimeOnPrimary
import com.example.ui.theme.LimePrimary
import com.example.ui.theme.OrangeTertiary
import com.example.ui.theme.TealSecondary

data class HeatCareItem(
    val title: String,
    val subtitle: String,
    val details: String,
    val icon: ImageVector,
    val tint: Color
)

@Composable
fun BodyHeatScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var waterGlasses by remember { mutableIntStateOf(5) }
    var activeItem by remember { mutableStateOf<HeatCareItem?>(null) }

    val items = listOf(
        HeatCareItem(
            title = "Cool-down Routines",
            subtitle = "Lower heart rate & surface flushing",
            details = "5 minutes of slow indoor walking followed by diaphragmatic breathing facilitates venous return and prevents post-exercise blood pooling without sudden thermal shock.",
            icon = Icons.Default.SelfImprovement,
            tint = TealSecondary
        ),
        HeatCareItem(
            title = "Hydration Reminders",
            subtitle = "Drink clean water consistently",
            details = "Maintain hydration according to thirst and environment. Drink ambient-temperature water regularly rather than chugging iced liquids immediately after strenuous activity.",
            icon = Icons.Default.LocalDrink,
            tint = Color(0xFF38BDF8)
        ),
        HeatCareItem(
            title = "Slow Breathing",
            subtitle = "Calm your vasomotor nervous system",
            details = "Perceived overheating often stems from sympathetic overdrive. Extended exhalations trigger peripheral vasodilation and mental composure.",
            icon = Icons.Default.Air,
            tint = LimePrimary
        ),
        HeatCareItem(
            title = "Recovery Exercises",
            subtitle = "Gentle passive hip and spinal stretches",
            details = "Gentle Child's Pose and low supine twists release tension from the pelvic floor and lower spine while muscles cool down safely.",
            icon = Icons.Default.Favorite,
            tint = OrangeTertiary
        ),
        HeatCareItem(
            title = "Hot Weather Safety",
            subtitle = "Exercise in cool, ventilated rooms",
            details = "Avoid midday sun. Wear light, breathable clothing. Use a fan or open windows. Never train in a humid, non-ventilated space.",
            icon = Icons.Default.WbSunny,
            tint = Color(0xFFFBBF24)
        ),
        HeatCareItem(
            title = "Warning Signs",
            subtitle = "Dizziness, weakness, or fever",
            details = "If you experience nausea, headache, extreme flushed confusion, or have an actual infectious fever, discontinue exercise immediately and seek medical assessment.",
            icon = Icons.Default.Warning,
            tint = Color(0xFFEF4444)
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("body_heat_screen")
    ) {
        PeakMindTopBar(
            title = "Recovery & Heat Care",
            onBack = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 6.dp)
        ) {
            // Interactive Hydration Tracker
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.4f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Daily Hydration",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "$waterGlasses of 8 glasses logged",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = { if (waterGlasses < 12) waterGlasses++ },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0284C7),
                                contentColor = Color.White
                            ),
                            modifier = Modifier.height(36.dp)
                        ) {
                            Text("+1 Glass", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Recovery Guides",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(10.dp))

            items.forEach { item ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { activeItem = item },
                    shape = RoundedCornerShape(14.dp),
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
                                .background(item.tint.copy(alpha = 0.15f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = item.tint,
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
                                text = item.subtitle,
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

        activeItem?.let { item ->
            AlertDialog(
                onDismissRequest = { activeItem = null },
                title = {
                    Text(text = item.title, fontWeight = FontWeight.Bold)
                },
                text = {
                    Text(text = item.details, style = MaterialTheme.typography.bodyMedium, lineHeight = 20.sp)
                },
                confirmButton = {
                    Button(
                        onClick = { activeItem = null },
                        colors = ButtonDefaults.buttonColors(containerColor = LimePrimary, contentColor = LimeOnPrimary)
                    ) {
                        Text("Close", fontWeight = FontWeight.Bold)
                    }
                },
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}
