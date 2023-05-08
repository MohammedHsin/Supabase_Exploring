package com.example.supabaseexploring.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supabaseexploring.data.remote.Signup
import com.example.supabaseexploring.di.GoTrueSupabaseClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.supabaseexploring.presentation.login.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signup: Signup
) : ViewModel(){
    private val _LoginUIState = MutableStateFlow<UIState>(UIState.Idle)
    val LoginUIState = _LoginUIState.asStateFlow()


    private val _loginState = MutableStateFlow<LoginState>(LoginState())
    val loginState = _loginState.asStateFlow()


    fun onUsernameChange(newUsername : String){
        _loginState.value = _loginState.value.copy(username = newUsername)
    }

    fun onEmailChange(newEmail : String){
        _loginState.value = _loginState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword : String){
        _loginState.value = _loginState.value.copy(password = newPassword)
    }

    fun onLoginChange(){
        _loginState.value = _loginState.value.copy(
            login = !_loginState.value.login
        )
    }




    fun performLogin(userEmail : String , userPassword : String){
        //todo
    }



    fun performSignUp(userEmail : String ,username : String , userPassword : String) {
        //todo

        viewModelScope.launch {
            signup.signup(userEmail , userPassword)
        }

    }
}