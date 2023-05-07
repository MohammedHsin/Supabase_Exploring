package com.example.supabaseexploring.di

import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideSupabaseClient() :SupabaseClient{
        return createSupabaseClient(
            supabaseUrl ="https://wpnzhaybebonlogjwsgg.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndwbnpoYXliZWJvbmxvZ2p3c2dnIiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODMzODk4MTcsImV4cCI6MTk5ODk2NTgxN30.-GBqAmzJIA7p4Tl2m3TrWSojfaJWqQrJp_bVTvnTzrw"
        ){
            install(Postgrest)
        }
    }
}