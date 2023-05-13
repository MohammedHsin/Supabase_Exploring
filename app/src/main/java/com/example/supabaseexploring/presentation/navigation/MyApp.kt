package com.example.supabaseexploring.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.supabaseexploring.presentation.login.LoginPage
import com.example.supabaseexploring.presentation.signup.SignupScreen


@Composable
fun MyApp(){
    
    val navController = rememberNavController()

    NavHost(navController = navController , startDestination = Screen.Login.route){
        composable(Screen.SignUp.route){
            SignupScreen(navController)
        }
        
        composable(Screen.Login.route){
            LoginPage(navController = navController)
        }
    }
}