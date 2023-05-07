package com.example.supabaseexploring.presentation.login

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

//fun LoginScreen(viewModel: LoginViewModel) {
//    val loginState by viewModel.loginState.collectAsState()
//    LaunchedEffect(key1 = loginState) {
//        if (loginState is UIState.Success) navigateToWelcomeScreen()
//    }
//    LoginScreenUI(loginState) { username, password -> viewModel.performLogin(username, password) }
//}