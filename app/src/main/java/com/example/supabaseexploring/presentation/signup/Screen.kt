package com.example.supabaseexploring.presentation.signup

sealed class Screen{
    object Login : Screen()
    object SignUp : Screen()
}
