package com.example.supabaseexploring.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel(){
    private val _loginState = MutableStateFlow<UIState>(UIState.Idle)
    val loginState : StateFlow<UIState> = _loginState.asStateFlow()

    fun performLogin(username : String , password : String){
        //todo
    }
}