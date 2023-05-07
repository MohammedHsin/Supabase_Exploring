package com.example.supabaseexploring

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import com.example.supabaseexploring.ui.theme.SupabaseExploringTheme
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            getData()
        }
        setContent {
            SupabaseExploringTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }

    private suspend fun getData(){
            val client = getClient()
            val supabaseResponse = client.postgrest["users"].select()
            val data = supabaseResponse.decodeList<User>()
            Log.d("hello", data.toString())
    }

    private fun getClient() : SupabaseClient{
        return createSupabaseClient(
            supabaseUrl ="https://wpnzhaybebonlogjwsgg.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndwbnpoYXliZWJvbmxvZ2p3c2dnIiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODMzODk4MTcsImV4cCI6MTk5ODk2NTgxN30.-GBqAmzJIA7p4Tl2m3TrWSojfaJWqQrJp_bVTvnTzrw"
        ){
            install(Postgrest)
        }
    }
}