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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.PeakMindTopBar

data class WorkoutCategoryItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val gradientColors: List<Color>
)

@Composable
fun WorkoutCategoriesScreen(
    onSelectCategory: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = listOf(
        WorkoutCategoryItem(
            id = "full_body",
            title = "Full Body",
            subtitle = "Squats, Push-ups, Core",
            icon = Icons.Default.FitnessCenter,
            gradientColors = listOf(Color(0xFF1E3A8A), Color(0xFF0F172A))
        ),
        WorkoutCategoryItem(
            id = "weight_loss",
            title = "Weight Loss",
            subtitle = "Fat burn & conditioning",
            icon = Icons.Default.TrendingDown,
            gradientColors = listOf(Color(0xFF14532D), Color(0xFF064E3B))
        ),
        WorkoutCategoryItem(
            id = "cardio",
            title = "Cardio",
            subtitle = "Marching & step touch",
            icon = Icons.Default.DirectionsRun,
            gradientColors = listOf(Color(0xFF7C2D12), Color(0xFF431407))
        ),
        WorkoutCategoryItem(
            id = "warm_up",
            title = "Warm-up",
            subtitle = "Joint mobility & flow",
            icon = Icons.Default.WbSunny,
            gradientColors = listOf(Color(0xFF0E7490), Color(0xFF155E75))
        ),
        WorkoutCategoryItem(
            id = "cool_down",
            title = "Cool-down",
            subtitle = "Stretches & slow walk",
            icon = Icons.Default.HourglassBottom,
            gradientColors = listOf(Color(0xFF1E293B), Color(0xFF0F172A))
        ),
        WorkoutCategoryItem(
            id = "kegel",
            title = "Kegel Training",
            subtitle = "Male pelvic coordination",
            icon = Icons.Default.Favorite,
            gradientColors = listOf(Color(0xFF4C1D95), Color(0xFF2E1065))
        ),
        WorkoutCategoryItem(
            id = "pelvic_floor",
            title = "Pelvic Floor",
            subtitle = "Relaxation & Cat-Cow",
            icon = Icons.Default.ElectricBolt,
            gradientColors = listOf(Color(0xFF831843), Color(0xFF500724))
        ),
        WorkoutCategoryItem(
            id = "sexual_wellness",
            title = "Sexual Wellness",
            subtitle = "Start-stop & ED care",
            icon = Icons.Default.LocalFireDepartment,
            gradientColors = listOf(Color(0xFF701A75), Color(0xFF4A044E))
        ),
        WorkoutCategoryItem(
            id = "meditation",
            title = "Meditation",
            subtitle = "Mindfulness & body scan",
            icon = Icons.Default.SelfImprovement,
            gradientColors = listOf(Color(0xFF1E1B4B), Color(0xFF0F172A))
        ),
        WorkoutCategoryItem(
            id = "body_heat",
            title = "Body Heat Mgmt",
            subtitle = "Cooling & hydration habits",
            icon = Icons.Default.Thermostat,
            gradientColors = listOf(Color(0xFF78350F), Color(0xFF451A03))
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("workout_categories_screen")
    ) {
        PeakMindTopBar(
            title = "Choose a Category",
            onBack = onBack
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { item ->
                com.example.ui.components.PeakMindCategoryCard(
                    title = item.title,
                    subtitle = item.subtitle,
                    icon = item.icon,
                    gradientColors = item.gradientColors,
                    onClick = { onSelectCategory(item.id) },
                    testTag = "category_card_${item.id}"
                )
            }
        }
    }
}
