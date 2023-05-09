package com.example.supabaseexploring.data.repository

import android.util.Log
import com.example.supabaseexploring.common.Resource
import com.example.supabaseexploring.data.remote.Login
import com.example.supabaseexploring.di.GoTrueSupabaseClient
import io.github.jan.supabase.gotrue.SessionStatus
import io.ktor.utils.io.errors.*
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class LoginRepo @Inject constructor(
    @GoTrueSupabaseClient private val login: Login
) {
    operator fun invoke(email : String , password : String) : Flow<Resource<SessionStatus>> {

        return flow {
            try {
                emit(Resource.Loading())
                val data = login.login(email , password)
                emit(Resource.Success(data))
            }catch (e : Exception){
                emit(Resource.Error(e.message.toString()))
            }catch (e : IOException){
                emit(Resource.Error(e.message.toString()))
            }
        }
    }
}