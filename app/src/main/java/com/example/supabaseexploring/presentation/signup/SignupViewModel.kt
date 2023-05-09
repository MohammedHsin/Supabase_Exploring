package com.example.supabaseexploring.presentation.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supabaseexploring.common.Resource
import com.example.supabaseexploring.common.UIState
import com.example.supabaseexploring.data.repository.SignupRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupRepo: SignupRepo
) : ViewModel(){
    private val _signupUIState = MutableStateFlow<UIState>(UIState.Idle)
    val signupUIState = _signupUIState.asStateFlow()


    private val _singupState = MutableStateFlow<SignupState>(SignupState())
    val signupState = _singupState.asStateFlow()


    fun onUsernameChange(newUsername : String){
        _singupState.value = _singupState.value.copy(username = newUsername)
    }

    fun onEmailChange(newEmail : String){
        _singupState.value = _singupState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword : String){
        _singupState.value = _singupState.value.copy(password = newPassword)
    }

    fun onConfirmPasswordChange(newPassword : String){
        _singupState.value = _singupState.value.copy(confirmPassword = newPassword)
    }







    fun performSignUp(userEmail : String ,username : String , userPassword : String) {
        //todo


           signupRepo(userEmail , userPassword).onEach {result->

               when(result){
                   is Resource.Error->{
                       _signupUIState.value = UIState.Error(result.message)
                   }
                   is Resource.Loading->{
                       _signupUIState.value = UIState.Loading
                   }
                   is Resource.Success->{
                       _signupUIState.value = UIState.Success(result.data)
                   }
               }
           }.launchIn(viewModelScope)


    }
}