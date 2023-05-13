package com.example.supabaseexploring.presentation.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(OnBoardingState())
    val state = _state.asStateFlow()


    fun onNameChange(newName : String){
        _state.value = _state.value.copy(name = newName)
    }

    fun onAgeChange(newAge : Int){
        _state.value = _state.value.copy(age = newAge)
    }

    fun onPageChange(){
        _state.value = _state.value.copy(page = _state.value.page+1)
    }
}