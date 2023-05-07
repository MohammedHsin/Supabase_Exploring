package com.example.supabaseexploring.presentation.login

sealed class Screen{
    object Login : Screen()
    object SignUp : Screen()
}
