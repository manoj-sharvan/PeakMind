package com.example.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Timeline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

sealed class BottomNavTab(
    val route: String,
    val label: String,
    val selectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
    val unselectedIcon: androidx.compose.ui.graphics.vector.ImageVector
) {
    data object Home : BottomNavTab("home", "Home", Icons.Filled.Home, Icons.Outlined.Home)
    data object Workouts : BottomNavTab("workouts", "Workouts", Icons.Filled.FitnessCenter, Icons.Outlined.FitnessCenter)
    data object Exercises : BottomNavTab("exercises", "Exercises", Icons.Filled.Search, Icons.Outlined.Search)
    data object Progress : BottomNavTab("progress", "Progress", Icons.Filled.Timeline, Icons.Outlined.Timeline)
    data object Profile : BottomNavTab("profile", "Profile", Icons.Filled.Person, Icons.Outlined.Person)
}

@Composable
fun PeakMindBottomNavBar(
    currentRoute: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabItems = listOf(
        BottomNavTabItem(BottomNavTab.Home.route, BottomNavTab.Home.label, BottomNavTab.Home.selectedIcon, BottomNavTab.Home.unselectedIcon),
        BottomNavTabItem(BottomNavTab.Workouts.route, BottomNavTab.Workouts.label, BottomNavTab.Workouts.selectedIcon, BottomNavTab.Workouts.unselectedIcon),
        BottomNavTabItem(BottomNavTab.Exercises.route, BottomNavTab.Exercises.label, BottomNavTab.Exercises.selectedIcon, BottomNavTab.Exercises.unselectedIcon),
        BottomNavTabItem(BottomNavTab.Progress.route, BottomNavTab.Progress.label, BottomNavTab.Progress.selectedIcon, BottomNavTab.Progress.unselectedIcon),
        BottomNavTabItem(BottomNavTab.Profile.route, BottomNavTab.Profile.label, BottomNavTab.Profile.selectedIcon, BottomNavTab.Profile.unselectedIcon)
    )

    PeakMindBottomNavigation(
        currentRoute = currentRoute,
        onTabSelected = onTabSelected,
        tabs = tabItems,
        modifier = modifier
    )
}
