package com.example.supabaseexploring.data.remote

import com.example.supabaseexploring.di.GoTrueSupabaseClient
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Inject

class Login @Inject constructor(
    @GoTrueSupabaseClient private val goTrueClient: SupabaseClient
) {
    suspend fun login(userEmail : String , userPassword : String) : SessionStatus{
        goTrueClient.gotrue.loginWith(Email){
            email = userEmail
            password = userPassword
        }


        return goTrueClient.gotrue.sessionStatus.value
    }
}