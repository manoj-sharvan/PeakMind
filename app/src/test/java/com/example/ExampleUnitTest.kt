package com.example

import com.example.data.ExerciseRepository
import com.example.data.UserProfileManager
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun exerciseRepository_containsEssentialMovements() {
        val exercises = ExerciseRepository.allExercises
        assertTrue("Repository should have exercises", exercises.isNotEmpty())
        assertNotNull(exercises.find { it.id == "ex_squat" })
        assertNotNull(exercises.find { it.id == "ex_pushup" })
        assertNotNull(exercises.find { it.id == "ex_glute_bridge" })
        assertNotNull(exercises.find { it.id == "ex_kegel_slow" })
        assertNotNull(exercises.find { it.id == "ex_cat_cow" })
        assertNotNull(exercises.find { it.id == "ex_childs_pose" })
    }

    @Test
    fun userProfile_defaultValuesAndUpdates() {
        val profile = UserProfileManager.userProfile.value
        assertEquals("Ravi", profile.name)
        assertEquals(29, profile.age)
        assertEquals(167, profile.heightCm)
        assertEquals(88f, profile.weightKg)
        assertEquals(70f, profile.targetWeightKg)

        UserProfileManager.updateProfile(weightKg = 87.5f)
        assertEquals(87.5f, UserProfileManager.userProfile.value.weightKg)
    }
}
