package com.example.supabaseexploring.presentation.login

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.supabaseexploring.ui.theme.SupabaseExploringTheme
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun Login(viewModel: LoginViewModel = hiltViewModel()) {


    val loginUIState by viewModel.LoginUIState.collectAsState()
    val loginState by viewModel.loginState.collectAsState()




    LaunchedEffect(key1 = loginUIState) {
        when (loginUIState) {

            is UIState.Idle ->{
                Log.d("hello", "nothing ..")
            }

            is UIState.Success -> {
                Log.d("hello", "you sign up !")
            }
            is UIState.Loading ->{
                Log.d("hello", "Loading ...")
            }
            is UIState.Error ->{
                Log.d("hello", (loginUIState as UIState.Error).message.toString())
            }
        }
    }

    MaterialTheme() {

// Use a scaffold to provide a structure for the screen
        Scaffold(
            content = {
                // Use a box to fill the entire screen
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    // Use a column to arrange the content vertically
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center) // Center the column in the box
                            .padding(16.dp), // Add some padding around the column
                        horizontalAlignment = Alignment.CenterHorizontally, // Center the items horizontally
                        verticalArrangement = Arrangement.spacedBy(8.dp) // Add some vertical spacing between the items
                    ) {
                        // Add a welcome text
                        Text(
                            text = "Welcome!",
                            style = MaterialTheme.typography.h4,
                            color = MaterialTheme.colors.onBackground
                        )
                        // Add a text field for the username
                        OutlinedTextField(
                            value = loginState.email, // TODO: use a state variable to store and update the value
                            onValueChange = { viewModel.onEmailChange(it) }, // TODO: use a lambda to update the value
                            label = { Text(text = "Email") }, // Add a label for the text field
                            singleLine = true, // Limit the input to one line
                            modifier = Modifier.fillMaxWidth(), // Make the text field fill the available width
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = ""
                                )
                            }
                        )



                        AnimatedVisibility(visible = !loginState.login) {
                            OutlinedTextField(
                                value = loginState.username, // TODO: use a state variable to store and update the value
                                onValueChange = { viewModel.onUsernameChange(it) }, // TODO: use a lambda to update the value
                                label = { Text(text = "Username") }, // Add a label for the text field
                                singleLine = true, // Limit the input to one line
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = ""
                                    )
                                }
                            )
                        }


                        // Add a text field for the password
                        OutlinedTextField(
                            value = loginState.password, // TODO: use a state variable to store and update the value
                            onValueChange = { viewModel.onPasswordChange(it) }, // TODO: use a lambda to update the value
                            label = { Text(text = "Password") }, // Add a label for the text field
                            singleLine = true, // Limit the input to one line
                            modifier = Modifier.fillMaxWidth(), // Make the text field fill the available width
                            visualTransformation = PasswordVisualTransformation(), // Hide the password characters
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // Use a password keyboard type
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = ""
                                )
                            }
                        )
                        // Add a button for login
                        Button(
                            onClick = {
                                if (loginState.login) {
                                    viewModel.performLogin(loginState.email, loginState.password)
                                } else {
                                    viewModel.performSignUp(
                                        loginState.email,
                                        loginState.username,
                                        loginState.password
                                    )
                                }
                            }, // TODO: use a lambda to handle the login logic
                            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary), // Use the secondary color for the button background
                            modifier = Modifier.width(100.dp) // Make the button fill the available width
                        ) {
                            Text(
                                text = if (loginState.login) "Login" else "Sign up",
                                color = MaterialTheme.colors.onSecondary // Use the onSecondary color for the button text
                            )
                        }
                        Row() {
                            Text(
                                modifier = Modifier,
                                text = if (loginState.login) "Don't have an account?" else "Already have an account?"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = if (loginState.login) "Sign up!" else "Log in!",
                                color = MaterialTheme.colors.secondary,
                                modifier = Modifier.clickable {
                                    viewModel.onLoginChange()
                                })
                        }
                    }
                }
            }
        )
    }

}

