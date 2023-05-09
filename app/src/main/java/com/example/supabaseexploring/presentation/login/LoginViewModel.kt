package com.example.supabaseexploring.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supabaseexploring.common.Resource
import com.example.supabaseexploring.common.UIState
import com.example.supabaseexploring.data.repository.LoginRepo
import com.example.supabaseexploring.di.GoTrueSupabaseClient
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepo: LoginRepo,
    @GoTrueSupabaseClient val goTrueClient : SupabaseClient
) : ViewModel(){

    private val _loginUIState = MutableStateFlow<UIState>(UIState.Idle)
    val loginUIState = _loginUIState.asStateFlow()

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()


    fun isAuthenticated() : Boolean{
        return goTrueClient.gotrue.sessionStatus.value.toString().substring(0 ,13) == "Authenticated"
    }


    fun onEmailChange(newEmail : String){
        _loginState.value = _loginState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword : String){
        _loginState.value = _loginState.value.copy(password = newPassword)
    }

    fun onPerformLogin(userEmail : String , userPassword : String){
        loginRepo(userEmail , userPassword).onEach {result ->

            when(result){
                is Resource.Loading ->{
                    _loginUIState.value = UIState.Loading
                }
                is Resource.Error ->{
                    _loginUIState.value = UIState.Error(result.message)
                }
                is Resource.Success ->{
                    _loginUIState.value = UIState.Success(result.data)
                }
            }


        }.launchIn(viewModelScope)
    }
}










