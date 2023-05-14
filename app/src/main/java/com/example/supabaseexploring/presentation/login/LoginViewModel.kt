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
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.flow.*
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






    fun getUserIfAny() : UserInfo?{
        return goTrueClient.gotrue.currentSessionOrNull()?.user
    }

    fun onEmailChange(newEmail : String){
        _loginState.value = _loginState.value.copy(email = newEmail)
        validateEmail()
    }

    fun onPasswordChange(newPassword : String){
        _loginState.value = _loginState.value.copy(password = newPassword)
        validPassword()
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



    fun validateEmail() {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        _loginState.update {   _loginState.value.copy(validEmail = emailRegex.matches(_loginState.value.email))}
    }

    fun validPassword(){
        val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
        _loginState.update {  _loginState.value.copy(validPassword = passwordRegex.matches(_loginState.value.password))}
    }



}










