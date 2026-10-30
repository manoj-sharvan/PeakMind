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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.UserProfileManager
import com.example.ui.components.PeakMindTopBar
import com.example.ui.components.PrimaryLimeButton
import com.example.ui.theme.LimeContainer
import com.example.ui.theme.LimePrimary

@Composable
fun UserProfileScreen(
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var age by remember { mutableStateOf("29") }
    var height by remember { mutableStateOf("167 cm") }
    var weight by remember { mutableStateOf("88 kg") }
    var targetWeight by remember { mutableStateOf("70 kg") }
    var selectedLevel by remember { mutableStateOf("Beginner") }
    var workoutTime by remember { mutableStateOf("30-45 min") }
    var equipment by remember { mutableStateOf("Bodyweight + Chair + Mat") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("user_profile_screen")
    ) {
        PeakMindTopBar(
            title = "Your Profile",
            onBack = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            ProfileFieldRow(label = "Age", value = age, onValueChange = { age = it })
            Spacer(modifier = Modifier.height(10.dp))
            ProfileFieldRow(label = "Height", value = height, onValueChange = { height = it })
            Spacer(modifier = Modifier.height(10.dp))
            ProfileFieldRow(label = "Weight", value = weight, onValueChange = { weight = it })
            Spacer(modifier = Modifier.height(10.dp))
            ProfileFieldRow(label = "Target Weight", value = targetWeight, onValueChange = { targetWeight = it })

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Fitness Level",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LevelChip(
                    label = "Beginner",
                    isSelected = selectedLevel == "Beginner",
                    onClick = { selectedLevel = "Beginner" },
                    modifier = Modifier.weight(1f)
                )
                LevelChip(
                    label = "Intermediate",
                    isSelected = selectedLevel == "Intermediate",
                    onClick = { selectedLevel = "Intermediate" },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LevelChip(
                    label = "Advanced",
                    isSelected = selectedLevel == "Advanced",
                    onClick = { selectedLevel = "Advanced" },
                    modifier = Modifier.weight(1f)
                )
                LevelChip(
                    label = "Master",
                    isSelected = selectedLevel == "Master",
                    onClick = { selectedLevel = "Master" },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Workout Time",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            DropdownPickerRow(
                value = workoutTime,
                onClick = {
                    workoutTime = when (workoutTime) {
                        "15-30 min" -> "30-45 min"
                        "30-45 min" -> "45-60 min"
                        else -> "15-30 min"
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Equipment",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            DropdownPickerRow(
                value = equipment,
                onClick = {
                    equipment = if (equipment.contains("Chair")) "Bodyweight Only" else "Bodyweight + Chair + Mat"
                }
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp)
        ) {
            PrimaryLimeButton(
                text = "Next",
                onClick = {
                    UserProfileManager.updateProfile(
                        age = age.filter { it.isDigit() }.toIntOrNull() ?: 29,
                        heightCm = height.filter { it.isDigit() }.toIntOrNull() ?: 167,
                        weightKg = weight.filter { it.isDigit() || it == '.' }.toFloatOrNull() ?: 88f,
                        targetWeightKg = targetWeight.filter { it.isDigit() || it == '.' }.toFloatOrNull() ?: 70f,
                        fitnessLevel = selectedLevel,
                        workoutTime = workoutTime,
                        equipment = equipment
                    )
                    onNext()
                },
                testTag = "profile_next_button"
            )
        }
    }
}

@Composable
private fun ProfileFieldRow(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun LevelChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .height(44.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) LimeContainer else MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = if (isSelected) 1.5.dp else 1.dp,
            color = if (isSelected) LimePrimary else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) LimePrimary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun DropdownPickerRow(
    value: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
