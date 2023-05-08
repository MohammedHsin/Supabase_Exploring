package com.example.supabaseexploring.presentation.login

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

//fun LoginScreen(viewModel: LoginViewModel) {
//    val loginState by viewModel.loginState.collectAsState()
//    launchedeffect(key1 = loginstate) {
//        if (loginstate is uistate.success) navigatetowelcomescreen()
//    }
//    LoginScreenUI(loginState) { username, password -> viewModel.performLogin(username, password) }
//}