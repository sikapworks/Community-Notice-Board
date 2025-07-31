package com.example.communitynoticeboard.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.communitynoticeboard.ui.screens.AlertScreen
import com.example.communitynoticeboard.ui.screens.HomeScreen
import com.example.communitynoticeboard.ui.screens.PostScreen
import com.example.communitynoticeboard.ui.screens.ProfileScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.Post.route) {
            PostScreen()
        }
        composable(route = Screen.Alerts.route) {
            AlertScreen()
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }
    }
}