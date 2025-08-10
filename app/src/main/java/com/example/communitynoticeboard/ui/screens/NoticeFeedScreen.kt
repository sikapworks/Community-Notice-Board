package com.example.communitynoticeboard.ui.screens

import android.R.style
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.communitynoticeboard.ui.components.CategoryFilterRow
import com.example.communitynoticeboard.ui.components.NoticeCard
import com.example.communitynoticeboard.ui.components.TopBar
import com.example.communitynoticeboard.viewmodel.NoticeViewModel
import java.text.DateFormat
import java.util.Date

@Composable
fun NoticeFeedScreen(
    viewModel: NoticeViewModel = viewModel()
) {
    val notices by viewModel.notices.collectAsState(emptyList())
    val errorMessage by viewModel.errorMessage.collectAsState()
    val context = LocalContext.current

    var selectedCategory by remember { mutableStateOf("All") }

    //fetch once when the screen loads
    LaunchedEffect(Unit) {
        viewModel.fetchAllNotices()
    }

    // show toast if error
    LaunchedEffect(errorMessage) {
        if (!errorMessage.isNullOrEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    Column {
        TopBar("Community Notices")
        CategoryFilterRow(
            selectedCategory = selectedCategory,
            onCategorySelected = { category ->
                if(category == "All") {
                    viewModel.fetchAllNotices()
                }
                else {
                    viewModel.fetchAllNotices()
                }
            }
        )

        // Empty state
        if (notices.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No notices yet",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(notices) { notice ->
                    NoticeCard(notice)
                }

            }
        }
    }

}