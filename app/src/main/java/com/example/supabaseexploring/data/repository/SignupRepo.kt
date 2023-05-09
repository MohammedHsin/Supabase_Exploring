package com.example.supabaseexploring.data.repository

import android.util.Log
import com.example.supabaseexploring.common.Resource
import com.example.supabaseexploring.data.remote.Signup
import com.example.supabaseexploring.di.GoTrueSupabaseClient
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.SessionStatus
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignupRepo @Inject constructor(
    private val signup : Signup
) {
    operator fun invoke(email : String , password : String) : Flow<Resource<SessionStatus>> {
        return flow {
            try {
                Log.d("holle", "invoke: loading ")
                emit(Resource.Loading<SessionStatus>())
                val data = signup.signup(email , password)
                emit(Resource.Success(data))
            }catch (e : Exception){
                emit(Resource.Error(e.message.toString()))
            }catch (e : IOException){
                emit(Resource.Error(e.message.toString()))
            }
        }
    }
}