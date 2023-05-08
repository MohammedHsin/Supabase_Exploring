package com.example.supabaseexploring.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.supabaseexploring.presentation.login.UIState

class LoginViewModel : ViewModel(){
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




    fun performLogin(email : String , password : String){
        //todo
        Log.d("hello", "performLogin: $email , $password")
    }



    fun performSignUp(email : String ,username : String , password : String){
        //todo
        Log.d("hello", "performSignup: $email ,$username ,  $password")
    }

}