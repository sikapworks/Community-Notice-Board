package com.example.communitynoticeboard.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Post: Screen("post")
    object Profile: Screen("profile")
}