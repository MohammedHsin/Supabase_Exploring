package com.example.supabaseexploring.presentation.signup

data class SignupState(
    val username : String = "",
    val password : String = "",
    val email : String = "",
    val login :Boolean = true
)
