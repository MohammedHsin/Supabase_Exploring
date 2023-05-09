package com.example.supabaseexploring.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supabaseexploring.common.Resource
import com.example.supabaseexploring.common.UIState
import com.example.supabaseexploring.data.repository.LoginRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepo: LoginRepo
) : ViewModel(){

    private val _loginUIState = MutableStateFlow<UIState>(UIState.Idle)
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










