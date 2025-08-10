package com.example.communitynoticeboard.data.model

import com.google.api.HttpBody

data class Notice (
    val title: String = "",
    val body: String = "",
    val category: String = "",
    val author: String = "",
    val timeStamp: Long = System.currentTimeMillis()
)