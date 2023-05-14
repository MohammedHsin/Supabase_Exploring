package com.example.supabaseexploring.presentation.signup

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


    private val _signupState = MutableStateFlow<SignupState>(SignupState())
    val signupState = _signupState.asStateFlow()


    fun onUsernameChange(newUsername : String){
        _signupState.value = _signupState.value.copy(username = newUsername)
        validateUsername()
    }

    fun onEmailChange(newEmail : String){
        _signupState.value = _signupState.value.copy(email = newEmail)
        validateEmail()
    }

    fun onPasswordChange(newPassword : String){
        _signupState.value = _signupState.value.copy(password = newPassword)
        validatePassword()
    }

    fun onConfirmPasswordChange(newPassword : String){
        _signupState.value = _signupState.value.copy(confirmPassword = newPassword)
        validateRePassword()
    }







    fun performSignUp(userEmail : String ,username : String , userPassword : String) {
        //todo


           signupRepo(userEmail , userPassword , username).onEach {result->

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

    fun validateEmail(){
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        _signupState.update { _signupState.value.copy(validEmail = emailRegex.matches(_signupState.value.email)) }
    }



    fun validatePassword(){
        val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
        _signupState.update { _signupState.value.copy(validPassword = passwordRegex.matches(_signupState.value.password)) }
    }

    fun validateRePassword(){
        _signupState.update { _signupState.value.copy(validRePassword = _signupState.value.password == _signupState.value.confirmPassword) }
    }

    fun validateUsername() {
        val regex = Regex("^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]+$")
        _signupState.update { _signupState.value.copy(validUsername =  regex.matches(_signupState.value.username)) }
    }
}