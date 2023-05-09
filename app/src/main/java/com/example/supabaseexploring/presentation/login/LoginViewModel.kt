package com.example.supabaseexploring.presentation.login

import androidx.lifecycle.ViewModel
import com.example.supabaseexploring.common.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel(){

    private val _loginUIState = MutableStateFlow(UIState.Idle)
    val loginUIState = _loginUIState.asStateFlow()

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()



    fun onEmailChange(newEmail : String){
        _loginState.value = _loginState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword : String){
        _loginState.value = _loginState.value.copy(password = newPassword)
    }

    fun onPerformLogin(userEmail : String , userPassword : String){
        // todo login
    }
}