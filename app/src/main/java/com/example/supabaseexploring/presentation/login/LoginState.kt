package com.example.supabaseexploring.presentation.login

data class LoginState(
    val email : String = "",
    val password : String = "",
    val validEmail : Boolean = false,
    val validPassword : Boolean = false,
)
