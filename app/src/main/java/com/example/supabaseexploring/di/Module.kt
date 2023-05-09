package com.example.supabaseexploring.di

import com.example.supabaseexploring.data.remote.Signup
import com.example.supabaseexploring.data.repository.SignupRepo
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Qualifier
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @PostgrestSupabaseClient
    @Provides
    @Singleton
    fun providePostgrestSupabaseClient() :SupabaseClient{
        return createSupabaseClient(
            supabaseUrl ="https://wpnzhaybebonlogjwsgg.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndwbnpoYXliZWJvbmxvZ2p3c2dnIiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODMzODk4MTcsImV4cCI6MTk5ODk2NTgxN30.-GBqAmzJIA7p4Tl2m3TrWSojfaJWqQrJp_bVTvnTzrw"
        ){
            install(Postgrest)
        }
    }


    @GoTrueSupabaseClient
    @Provides
    @Singleton
    fun provideGoTrueSupabaseClient() :SupabaseClient{
        return createSupabaseClient(
            supabaseUrl ="https://wpnzhaybebonlogjwsgg.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndwbnpoYXliZWJvbmxvZ2p3c2dnIiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODMzODk4MTcsImV4cCI6MTk5ODk2NTgxN30.-GBqAmzJIA7p4Tl2m3TrWSojfaJWqQrJp_bVTvnTzrw"
        ){
            install(GoTrue)
        }
    }

    @Provides
    @Singleton
    fun provideSignup(@GoTrueSupabaseClient client : SupabaseClient) : Signup{
        return Signup(client)
    }

    @Provides
    @Singleton
    fun provideSignupRepo(signup: Signup) : SignupRepo{
            return SignupRepo(signup)
    }


}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PostgrestSupabaseClient


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GoTrueSupabaseClient
