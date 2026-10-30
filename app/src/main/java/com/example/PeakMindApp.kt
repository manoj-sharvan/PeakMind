package com.example

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.UserProfileManager
import com.example.ui.components.PeakMindBottomNavBar
import com.example.ui.screens.ActiveWorkoutScreen
import com.example.ui.screens.BodyHeatScreen
import com.example.ui.screens.ExerciseLibraryScreen
import com.example.ui.screens.ExerciseVisualGuideScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.KegelTrainingScreen
import com.example.ui.screens.LevelSelectionScreen
import com.example.ui.screens.MeditationScreen
import com.example.ui.screens.OnboardingScreen
import com.example.ui.screens.PelvicFloorScreen
import com.example.ui.screens.ProgressScreen
import com.example.ui.screens.RestScreen
import com.example.ui.screens.SettingsProfileScreen
import com.example.ui.screens.SexualWellnessScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.screens.UserProfileScreen
import com.example.ui.screens.WorkoutCategoriesScreen
import com.example.ui.theme.MyApplicationTheme

object AppDestinations {
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val USER_PROFILE_SETUP = "user_profile_setup"
    const val HOME = "home"
    const val WORKOUTS = "workouts"
    const val LEVEL_SELECTION = "level_selection"
    const val EXERCISES = "exercises"
    const val EXERCISE_GUIDE = "exercise_guide/{exerciseId}"
    const val ACTIVE_WORKOUT = "active_workout/{exerciseId}"
    const val REST_SCREEN = "rest_screen"
    const val KEGEL_TRAINING = "kegel_training"
    const val PELVIC_FLOOR = "pelvic_floor"
    const val SEXUAL_WELLNESS = "sexual_wellness"
    const val MEDITATION = "meditation"
    const val BODY_HEAT = "body_heat"
    const val PROGRESS = "progress"
    const val PROFILE = "profile"
}

@Composable
fun PeakMindApp(
    navController: NavHostController = rememberNavController()
) {
    val userProfile by UserProfileManager.userProfile.collectAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: AppDestinations.SPLASH

    val isTopLevelRoute = currentRoute in listOf(
        AppDestinations.HOME,
        AppDestinations.WORKOUTS,
        AppDestinations.EXERCISES,
        AppDestinations.PROGRESS,
        AppDestinations.PROFILE
    )

    MyApplicationTheme(darkTheme = userProfile.isDarkTheme) {
        Scaffold(
            bottomBar = {
                if (isTopLevelRoute) {
                    PeakMindBottomNavBar(
                        currentRoute = currentRoute,
                        onTabSelected = { route ->
                            if (currentRoute != route) {
                                navController.navigate(route) {
                                    popUpTo(AppDestinations.HOME) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(
                    navController = navController,
                    startDestination = AppDestinations.SPLASH
                ) {
                    // 1. Splash Screen
                    composable(AppDestinations.SPLASH) {
                        SplashScreen(
                            onNavigateNext = {
                                navController.navigate(AppDestinations.ONBOARDING) {
                                    popUpTo(AppDestinations.SPLASH) { inclusive = true }
                                }
                            }
                        )
                    }

                    // 2. Onboarding Screen
                    composable(AppDestinations.ONBOARDING) {
                        OnboardingScreen(
                            onGetStarted = {
                                navController.navigate(AppDestinations.USER_PROFILE_SETUP)
                            },
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 3. User Profile Setup Screen
                    composable(AppDestinations.USER_PROFILE_SETUP) {
                        BackHandler {
                            navController.popBackStack()
                        }
                        UserProfileScreen(
                            onNext = {
                                navController.navigate(AppDestinations.HOME) {
                                    popUpTo(AppDestinations.ONBOARDING) { inclusive = true }
                                }
                            },
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 4. Home Dashboard Screen
                    composable(AppDestinations.HOME) {
                        HomeScreen(
                            onStartWorkout = {
                                navController.navigate("active_workout/ex_squat")
                            },
                            onNavigateCategory = { catKey ->
                                when (catKey) {
                                    "kegel" -> navController.navigate(AppDestinations.KEGEL_TRAINING)
                                    "pelvic_floor" -> navController.navigate(AppDestinations.PELVIC_FLOOR)
                                    "sexual_wellness" -> navController.navigate(AppDestinations.SEXUAL_WELLNESS)
                                    "body_heat" -> navController.navigate(AppDestinations.BODY_HEAT)
                                    else -> navController.navigate(AppDestinations.WORKOUTS)
                                }
                            },
                            onOpenProfile = {
                                navController.navigate(AppDestinations.PROFILE)
                            }
                        )
                    }

                    // 5. Workout Categories Screen
                    composable(AppDestinations.WORKOUTS) {
                        WorkoutCategoriesScreen(
                            onSelectCategory = { catId ->
                                when (catId) {
                                    "kegel" -> navController.navigate(AppDestinations.KEGEL_TRAINING)
                                    "pelvic_floor" -> navController.navigate(AppDestinations.PELVIC_FLOOR)
                                    "sexual_wellness" -> navController.navigate(AppDestinations.SEXUAL_WELLNESS)
                                    "meditation" -> navController.navigate(AppDestinations.MEDITATION)
                                    "body_heat" -> navController.navigate(AppDestinations.BODY_HEAT)
                                    else -> navController.navigate(AppDestinations.LEVEL_SELECTION)
                                }
                            },
                            onBack = {
                                navController.navigate(AppDestinations.HOME)
                            }
                        )
                    }

                    // 6. Level Selection Screen
                    composable(AppDestinations.LEVEL_SELECTION) {
                        BackHandler {
                            navController.popBackStack()
                        }
                        LevelSelectionScreen(
                            onContinue = {
                                navController.navigate(AppDestinations.EXERCISES)
                            },
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 7. Exercise Library Screen
                    composable(AppDestinations.EXERCISES) {
                        ExerciseLibraryScreen(
                            onSelectExercise = { exercise ->
                                navController.navigate("exercise_guide/${exercise.id}")
                            },
                            onBack = {
                                navController.navigate(AppDestinations.HOME)
                            }
                        )
                    }

                    // 8. Exercise Visual Guide Screen (A, B, C, D Breakdown)
                    composable(
                        route = AppDestinations.EXERCISE_GUIDE,
                        arguments = listOf(navArgument("exerciseId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val exerciseId = backStackEntry.arguments?.getString("exerciseId") ?: "ex_squat"
                        BackHandler {
                            navController.popBackStack()
                        }
                        ExerciseVisualGuideScreen(
                            exerciseId = exerciseId,
                            onStartWorkout = {
                                navController.navigate("active_workout/$exerciseId")
                            },
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 9. Active Workout Experience Screen
                    composable(
                        route = AppDestinations.ACTIVE_WORKOUT,
                        arguments = listOf(navArgument("exerciseId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val exerciseId = backStackEntry.arguments?.getString("exerciseId") ?: "ex_squat"
                        BackHandler {
                            navController.popBackStack()
                        }
                        ActiveWorkoutScreen(
                            exerciseId = exerciseId,
                            onStartRest = {
                                navController.navigate(AppDestinations.REST_SCREEN)
                            },
                            onCompleteWorkout = {
                                UserProfileManager.completeWorkout()
                                navController.navigate(AppDestinations.PROGRESS)
                            },
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 10. Rest Screen
                    composable(AppDestinations.REST_SCREEN) {
                        BackHandler {
                            navController.popBackStack()
                        }
                        RestScreen(
                            nextExerciseName = "Push Up",
                            onFinishRest = {
                                navController.popBackStack()
                            },
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 11. Kegel Training Screen
                    composable(AppDestinations.KEGEL_TRAINING) {
                        BackHandler {
                            navController.popBackStack()
                        }
                        KegelTrainingScreen(
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 12. Pelvic Floor Relaxation Screen
                    composable(AppDestinations.PELVIC_FLOOR) {
                        BackHandler {
                            navController.popBackStack()
                        }
                        PelvicFloorScreen(
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 13. Sexual Wellness & Arousal Awareness Screen
                    composable(AppDestinations.SEXUAL_WELLNESS) {
                        BackHandler {
                            navController.popBackStack()
                        }
                        SexualWellnessScreen(
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 14. Meditation Screen
                    composable(AppDestinations.MEDITATION) {
                        BackHandler {
                            navController.popBackStack()
                        }
                        MeditationScreen(
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 15. Body Heat Management & Recovery Screen
                    composable(AppDestinations.BODY_HEAT) {
                        BackHandler {
                            navController.popBackStack()
                        }
                        BodyHeatScreen(
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // 16. Progress Tracking Screen
                    composable(AppDestinations.PROGRESS) {
                        ProgressScreen(
                            onBack = {
                                navController.navigate(AppDestinations.HOME)
                            }
                        )
                    }

                    // 17. Settings & Profile Screen
                    composable(AppDestinations.PROFILE) {
                        SettingsProfileScreen(
                            onEditProfile = {
                                navController.navigate(AppDestinations.USER_PROFILE_SETUP)
                            },
                            onBack = {
                                navController.navigate(AppDestinations.HOME)
                            }
                        )
                    }
                }
            }
        }
    }
}
