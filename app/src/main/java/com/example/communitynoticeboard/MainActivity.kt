package com.example.communitynoticeboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.communitynoticeboard.navigation.NavGraph
import com.example.communitynoticeboard.ui.components.BottomNavBar
import com.example.communitynoticeboard.ui.screens.LoginScreen
import com.example.compose.CommunityNoticeBoardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CommunityNoticeBoardTheme {
//                AppContent()
                LoginScreen(onAuthSuccess = {})
            }
        }
    }
}

@Composable
fun AppContent() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->
        NavGraph(navController = navController)

    }
}