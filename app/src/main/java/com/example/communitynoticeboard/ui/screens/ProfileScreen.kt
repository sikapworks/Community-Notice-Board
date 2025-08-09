package com.example.communitynoticeboard.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.communitynoticeboard.viewmodel.NoticeViewModel
import com.example.communitynoticeboard.viewmodel.ProfileViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import com.example.communitynoticeboard.navigation.Screen

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel()
) {
    val isLoggedOut by viewModel.isLoggedOut.collectAsState()

    LaunchedEffect(isLoggedOut) {
        if (isLoggedOut) {
            navController.navigate(Screen.Profile.route) {
                popUpTo(0) {
                    inclusive = true  //clear entire backstack
                }
            }
        }
    }

    val user = viewModel.currentUser

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("User Profile")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Email: ${user?.email ?: "Unknown"}")
        Text("UID: ${user?.uid ?: "Unknown"}")
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { viewModel.logout() }) {
            Text("Logout")
        }

    }
}