package edu.cuhk.csci3310.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavController) {
    val navigationList =
        listOf(
            BottomNavBarItemData(
                route = Graph.Habits.name,
                name = "Habits",
                selectedIcon = Icons.Filled.Task,
                unselectedIcon = Icons.Outlined.Task,
            ),
            BottomNavBarItemData(
                route = Graph.Groups.name,
                name = "Groups",
                selectedIcon = Icons.Filled.Folder,
                unselectedIcon = Icons.Outlined.Folder,
            ),
            BottomNavBarItemData(
                route = Screen.Settings.route,
                name = "Settings",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
            ),
        )
    NavigationBar {
        val navBackstackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackstackEntry?.destination
        navigationList.forEach { item ->
            // if the whole expression evaluates to null, coalesce it to false
            val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.secondary,
                    indicatorColor = Color.Transparent
                ),
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        when (isSelected) {
                            true -> item.selectedIcon
                            false -> item.unselectedIcon
                        },
                        contentDescription = item.name,
                    )
                },
                label = { Text(text = item.name) },
            )
        }
    }
}
