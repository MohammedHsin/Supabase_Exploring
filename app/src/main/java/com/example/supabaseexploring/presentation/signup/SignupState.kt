package com.example.supabaseexploring.presentation.signup

data class SignupState(
    val username : String = "",
    val password : String = "",
    val confirmPassword : String = "",
    val email : String = "",
)
