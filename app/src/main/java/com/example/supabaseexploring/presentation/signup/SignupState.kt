package com.example.supabaseexploring.presentation.signup

data class SignupState(
    val username : String = "",
    val password : String = "",
    val confirmPassword : String = "",
    val email : String = "",
    val validEmail : Boolean =false,
    val validPassword: Boolean = false,
    val validRePassword : Boolean =false,
    val validUsername : Boolean = false
)
