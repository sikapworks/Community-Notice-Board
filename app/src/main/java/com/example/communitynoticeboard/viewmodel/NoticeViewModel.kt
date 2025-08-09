package com.example.communitynoticeboard.viewmodel

import android.icu.text.CaseMap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.communitynoticeboard.data.model.Notice
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoticeViewModel : ViewModel() {

    private val _notices = MutableStateFlow<List<Notice>>(emptyList())
    val notices: StateFlow<List<Notice>> = _notices

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("notices")
    private val auth = FirebaseAuth.getInstance()
    private var valueEventListener: ValueEventListener? = null

    fun postDummyNotice() {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            _errorMessage.value = "User not logged in."
            return
        }
        val uid = currentUser.uid

        val newNotice = Notice(
            title = "Test Notice",
            body = "This is a dummy notice for testing",
//            timestamp = System.currentTimeMillis()
        )

        database.child(uid).push().setValue(newNotice)
            .addOnSuccessListener {
                Log.d("NoticeViewModel", "Dummy notice posted successfully.")
            }
            .addOnFailureListener {
                val msg = "Failed to post dummy notice: ${it.message}"
                Log.e("NoticeViewModel", msg)
                _errorMessage.value = msg
            }

    }

    // to read all notices
    fun fetchAllNotices() {

        valueEventListener?.let {  database.removeEventListener(it) }
        // addValueEventListener listen to real time updates
        valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val noticeList = mutableListOf<Notice>()
                for(userSnapshot in snapshot.children) {
                    for(noticeSnapshot in userSnapshot.children) {
                        val notice = noticeSnapshot.getValue(Notice::class.java)
                        notice?.let { noticeList.add(it) }
                    }
                }
                viewModelScope.launch {
                    _notices.value = noticeList.sortedByDescending { it.timeStamp }
                    _errorMessage.value = null // clear any old errors
                }
            }
            override fun onCancelled(error: DatabaseError) {
                val msg = "Failed to fetch notices: ${error.message}"
                Log.e("NoticeViewModel", msg)
                _errorMessage.value = msg
            }
        }
        database.addValueEventListener(valueEventListener as ValueEventListener)
    }

    override fun onCleared() {
        super.onCleared()
        valueEventListener?.let { database.removeEventListener(it) }
    }

    fun postNotice(uid: String, title: String, body: String) {

        //push().key ensures a unique ID for every notice
        val noticeId = database.child(uid).push().key ?: return
        val notice = Notice(title = title, body = body)

        database.child(uid).child(noticeId)
            .setValue(notice)
            .addOnSuccessListener {
                Log.d("NoticeViewModel", "Notice posted successfully")
            }
            .addOnFailureListener { e ->
                Log.e("NoticeViewModel", "Failed to post notice", e)
            }
    }
}