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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Star
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
import com.example.data.UserProfileManager
import com.example.ui.components.PeakMindTopBar
import com.example.ui.components.PrimaryLimeButton
import com.example.ui.theme.LimeContainer
import com.example.ui.theme.LimePrimary
import com.example.ui.theme.OrangeTertiary
import com.example.ui.theme.TealSecondary

data class TrainingLevelInfo(
    val levelKey: String,
    val title: String,
    val subtitle: String,
    val stats: String,
    val icon: ImageVector,
    val accentColor: Color
)

@Composable
fun LevelSelectionScreen(
    onContinue: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedLevel by remember { mutableStateOf("Beginner") }

    val levels = listOf(
        TrainingLevelInfo(
            levelKey = "Beginner",
            title = "Beginner",
            subtitle = "New to fitness. Learn basics.",
            stats = "15–25 min • 3–4 exercises",
            icon = Icons.Default.FitnessCenter,
            accentColor = LimePrimary
        ),
        TrainingLevelInfo(
            levelKey = "Intermediate",
            title = "Intermediate",
            subtitle = "Some experience. Build endurance.",
            stats = "20–35 min • 4–6 exercises",
            icon = Icons.Default.Speed,
            accentColor = TealSecondary
        ),
        TrainingLevelInfo(
            levelKey = "Advanced",
            title = "Advanced",
            subtitle = "Progressive training. Higher intensity.",
            stats = "30–50 min • 6–8 exercises",
            icon = Icons.Default.Star,
            accentColor = OrangeTertiary
        ),
        TrainingLevelInfo(
            levelKey = "Master",
            title = "Master",
            subtitle = "Full potential. Advanced routine.",
            stats = "45–60 min • 8–10 exercises",
            icon = Icons.Default.EmojiEvents,
            accentColor = Color(0xFFA855F7)
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("level_selection_screen")
    ) {
        PeakMindTopBar(
            title = "Select Your Level",
            onBack = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            levels.forEach { level ->
                val isSelected = selectedLevel == level.levelKey
                com.example.ui.components.PeakMindLevelCard(
                    title = level.title,
                    subtitle = level.subtitle,
                    stats = level.stats,
                    icon = level.icon,
                    accentColor = level.accentColor,
                    isSelected = isSelected,
                    onClick = { selectedLevel = level.levelKey },
                    testTag = "level_card_${level.levelKey}"
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp)
        ) {
            PrimaryLimeButton(
                text = "Continue",
                onClick = {
                    UserProfileManager.updateProfile(fitnessLevel = selectedLevel)
                    onContinue(selectedLevel)
                },
                testTag = "level_continue_button"
            )
        }
    }
}
