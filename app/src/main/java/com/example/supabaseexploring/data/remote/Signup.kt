package com.example.supabaseexploring.data.remote

import android.util.Log
import com.example.supabaseexploring.di.GoTrueSupabaseClient
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import javax.inject.Inject

class Signup @Inject constructor(
    @GoTrueSupabaseClient private val goTrueClient : SupabaseClient
){

    suspend fun signup(userEmail : String , userPassword : String){
        try {
            goTrueClient.gotrue.signUpWith(Email){
                email = userEmail
                password = userPassword
            }
            val i = goTrueClient.gotrue.sessionStatus.value
            Log.d("hello", i.toString())
        }catch (e :Exception){
            Log.d("hello", e.message.toString())
        }
    }
}