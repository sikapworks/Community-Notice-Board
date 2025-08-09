package com.example.communitynoticeboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.communitynoticeboard.ui.screens.HomeScreen
import com.example.communitynoticeboard.ui.screens.LoginScreen
import com.example.compose.CommunityNoticeBoardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CommunityNoticeBoardTheme {
                var loggedInUserId by remember { mutableStateOf<String?>(null) }

                if (loggedInUserId == null) {
                    LoginScreen { uid ->
                        loggedInUserId = uid
                    }
                } else {
                    HomeScreen("123")
                }
            }
        }
    }
}

