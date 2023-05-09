package com.example.supabaseexploring.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supabaseexploring.common.Resource
import com.example.supabaseexploring.data.remote.Signup
import com.example.supabaseexploring.data.repository.SignupRepo
import com.example.supabaseexploring.di.GoTrueSupabaseClient
import com.example.supabaseexploring.presentation.login.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signupRepo: SignupRepo
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


           signupRepo(userEmail , userPassword).onEach {result->
               when(result){
                   is Resource.Error->{
                       _LoginUIState.value = UIState.Error(result.message)
                   }
                   is Resource.Loading->{
                       _LoginUIState.value = UIState.Loading
                   }
                   is Resource.Success->{
                       _LoginUIState.value = UIState.Success(result.data)
                   }
               }
           }.launchIn(viewModelScope)


    }
}