package com.example.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UserProfile(
    val name: String = "Ravi",
    val age: Int = 29,
    val heightCm: Int = 167,
    val weightKg: Float = 88.0f,
    val targetWeightKg: Float = 70.0f,
    val waistCircumferenceCm: Float = 96.0f,
    val fitnessLevel: String = "Beginner",
    val workoutTime: String = "30-45 min",
    val equipment: String = "Bodyweight + Chair + Mat",
    val currentMonth: Int = 1,
    val totalMonths: Int = 6,
    val weeklyWorkoutsDone: Int = 3,
    val weeklyWorkoutsGoal: Int = 5,
    val activeMinutes: Int = 42,
    val meditationStreakDays: Int = 5,
    val isDarkTheme: Boolean = true,
    val notificationsEnabled: Boolean = true
)

data class WeightRecord(
    val dateLabel: String,
    val weightKg: Float
)

object UserProfileManager {
    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    val weightHistory = listOf(
        WeightRecord("Apr 1", 88.0f),
        WeightRecord("Apr 15", 86.4f),
        WeightRecord("Apr 30", 84.8f),
        WeightRecord("May 15", 83.2f),
        WeightRecord("May 31", 81.5f)
    )

    val waistHistory = listOf(
        WeightRecord("Apr 1", 96.0f),
        WeightRecord("Apr 15", 94.5f),
        WeightRecord("Apr 30", 93.0f),
        WeightRecord("May 15", 91.5f),
        WeightRecord("May 31", 89.8f)
    )

    fun updateProfile(
        name: String = _userProfile.value.name,
        age: Int = _userProfile.value.age,
        heightCm: Int = _userProfile.value.heightCm,
        weightKg: Float = _userProfile.value.weightKg,
        targetWeightKg: Float = _userProfile.value.targetWeightKg,
        fitnessLevel: String = _userProfile.value.fitnessLevel,
        workoutTime: String = _userProfile.value.workoutTime,
        equipment: String = _userProfile.value.equipment
    ) {
        _userProfile.value = _userProfile.value.copy(
            name = name,
            age = age,
            heightCm = heightCm,
            weightKg = weightKg,
            targetWeightKg = targetWeightKg,
            fitnessLevel = fitnessLevel,
            workoutTime = workoutTime,
            equipment = equipment
        )
    }

    fun toggleTheme() {
        _userProfile.value = _userProfile.value.copy(isDarkTheme = !_userProfile.value.isDarkTheme)
    }

    fun completeWorkout() {
        val currentDone = _userProfile.value.weeklyWorkoutsDone
        _userProfile.value = _userProfile.value.copy(
            weeklyWorkoutsDone = (currentDone + 1).coerceAtMost(7),
            activeMinutes = _userProfile.value.activeMinutes + 25
        )
    }
}
