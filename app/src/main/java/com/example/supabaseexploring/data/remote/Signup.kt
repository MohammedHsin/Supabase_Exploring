package com.example.supabaseexploring.data.remote

import com.example.supabaseexploring.di.GoTrueSupabaseClient
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

class Signup @Inject constructor(
    @GoTrueSupabaseClient private val goTrueClient : SupabaseClient
){

    suspend fun signup(userEmail : String , userPassword : String , username : String) : SessionStatus{
        goTrueClient.gotrue.signUpWith(Email){
                email = userEmail
                password = userPassword
            data = buildJsonObject {
                put("username" , username)
            }
            }

//        goTrueClient.aut

        return goTrueClient.gotrue.sessionStatus.value
}
}