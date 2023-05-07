package com.example.supabaseexploring.presentation.login

sealed class UIState {
    object Idle : UIState()
    object Loading : UIState()
    data class Success(val data: Any? = null) : UIState()
    data class Error(val message: String? = null) : UIState()
}
