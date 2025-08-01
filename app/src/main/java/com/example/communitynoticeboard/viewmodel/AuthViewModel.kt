package com.example.communitynoticeboard.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow<AuthResult>(AuthResult.Idle)
    val authState: StateFlow<AuthResult> = _authState

//    var currentUser by mutableStateOf(auth.currentUser)
//        private set
//
//    var errorMessage by mutableStateOf<String?>(null)
//        private set


    fun registerWithEmail(
        email: String,
        password: String
    ) {
        _authState.value = AuthResult.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                _authState.value = AuthResult.Success(auth.currentUser?.uid.orEmpty())
            }
            .addOnFailureListener {
                _authState.value = AuthResult.Error(it.message ?: "Registration Failed")
            }
    }

    fun loginWithEmail(
        email: String,
        password: String
    ) {
        _authState.value = AuthResult.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                _authState.value = AuthResult.Success(auth.currentUser?.uid.orEmpty())
            }
            .addOnFailureListener {
                _authState.value = AuthResult.Error(it.message ?: "Login Failed")
            }
    }

    fun logout() {
        auth.signOut()
//        currentUser = null   // No used logged in
    }
}

sealed class AuthResult {
    object Idle : AuthResult()
    object Loading : AuthResult()
    data class Success(val uid: String) : AuthResult()
    data class Error(val message: String) : AuthResult()
}