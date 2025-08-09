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
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
    }
else {
        LazyColumn(
            modifier = Modifier
//                .fillMaxSize()
                .padding(24.dp)
        ) {
            items(notices) { notice ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = notice.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = notice.body
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Posted on: ${
                                DateFormat.getDateTimeInstance().format(Date(notice.timeStamp))
                            }",
                            style = MaterialTheme.typography.labelSmall
                        )

                    }

                }
            }

        }
    }

}