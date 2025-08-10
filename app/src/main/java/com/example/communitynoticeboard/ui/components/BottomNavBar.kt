package com.example.communitynoticeboard.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.communitynoticeboard.navigation.BottomNavItem
import com.example.communitynoticeboard.navigation.Screen

@Composable
fun BottomNavBar(navController: NavController) {

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar {
        bottomNavItem.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState =
                                    true  // Saves the state of the screen like scroll position or entered text
                            }
                            launchSingleTop =
                                true  // If screen is already at the top, duplicate instance is not created
                            restoreState = true  // Restores the above saved state
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(item.label)
                },
                alwaysShowLabel = true

            )
        }
    }

}

val bottomNavItem = listOf(
    BottomNavItem(Screen.Home.route, Icons.Outlined.Home, "Home"),
    BottomNavItem(Screen.Post.route, Icons.Outlined.Add, "Post"),
    BottomNavItem(Screen.Profile.route, Icons.Outlined.Person, "Profile"),
)