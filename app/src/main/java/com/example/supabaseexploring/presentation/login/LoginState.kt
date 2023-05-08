package com.example.supabaseexploring.presentation.login

import kotlinx.coroutines.flow.MutableStateFlow

data class LoginState(
    val username : String = "",
    val password : String = "",
    val email : String = "",
    val login :Boolean = true
)
