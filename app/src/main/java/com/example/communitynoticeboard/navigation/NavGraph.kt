package com.example.communitynoticeboard.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.communitynoticeboard.ui.screens.NoticeFeedScreen
import com.example.communitynoticeboard.ui.screens.PostScreen
import com.example.communitynoticeboard.ui.screens.ProfileScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    uid: String,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            NoticeFeedScreen()
        }
        composable(route = Screen.Post.route) {
            PostScreen(uid) {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = false }
                    launchSingleTop = true
                }
            }
        }
        composable(route = Screen.Alerts.route) {
            NoticeFeedScreen()
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController)
        }
    }
}