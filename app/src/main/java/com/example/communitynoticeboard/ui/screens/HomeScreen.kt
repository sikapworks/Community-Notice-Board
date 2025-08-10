package com.example.communitynoticeboard.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.communitynoticeboard.navigation.NavGraph
import com.example.communitynoticeboard.ui.components.BottomNavBar
import com.example.communitynoticeboard.ui.components.TopBar

@Composable
fun HomeScreen(
    uid: String
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavGraph(
            navController = navController,
            uid = uid,
            modifier = Modifier.padding(innerPadding)
        )

    }

}