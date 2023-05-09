package com.example.supabaseexploring.presentation.newLogin


import android.util.Log
import androidx.compose.foundation.*
import com.example.supabaseexploring.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.supabaseexploring.presentation.signup.SignupViewModel
import com.example.supabaseexploring.presentation.signup.UIState
import com.example.supabaseexploring.ui.theme.primaryColor
import com.example.supabaseexploring.ui.theme.whiteBackground

@Composable
fun RegisterPage(
//    navController: NavController
viewModel: SignupViewModel = hiltViewModel()
) {

    val loginState by viewModel.loginState.collectAsState()
    val loginUIState by viewModel.LoginUIState.collectAsState()

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

    val image = painterResource(id = R.drawable.register_page)

    val nameValue = remember { mutableStateOf("") }
//    val emailValue = remember { mutableStateOf("") }
    val phoneValue = remember { mutableStateOf("") }
//    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(painter = image, contentDescription = "")
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.70f)
                .clip(RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
                .background(whiteBackground)
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

                Text(
                    text = "Sign Up", fontSize = 30.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        value = nameValue.value,
                        onValueChange = { nameValue.value = it },
                        label = { Text(text = "Name") },
                        placeholder = { Text(text = "Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    OutlinedTextField(
                        value = loginState.email,
                        onValueChange = {viewModel.onEmailChange(it)},
                        label = { Text(text = "Email Address") },
                        placeholder = { Text(text = "Email Address") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )



                    OutlinedTextField(
                        value = loginState.password,
                        onValueChange = { viewModel.onPasswordChange(it) },
                        label = { Text(text = "Password") },
                        placeholder = { Text(text = "Password") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }) {
//

                                Icon(painter = painterResource(id =R.drawable.password_eye), contentDescription = "",
                                tint =if (passwordVisibility.value) primaryColor else Color.Gray )
                            }
                        },
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation()
                    )

                    OutlinedTextField(
                        value = confirmPasswordValue.value,
                        onValueChange = { confirmPasswordValue.value = it },
                        label = { Text(text = "Confirm Password") },
                        placeholder = { Text(text = "Confirm Password") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        trailingIcon = {
                            IconButton(onClick = {
                                confirmPasswordVisibility.value = !confirmPasswordVisibility.value
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.password_eye),
                                    contentDescription = "",
                                    tint = if (confirmPasswordVisibility.value) primaryColor else Color.Gray
                                )
                            }
                        },
                        visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(onClick = {
                                     viewModel.performSignUp(loginState.email,
                                     loginState.username,
                                     loginState.password)
                    }, modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)) {
                        Text(text = "Sign Up", fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "Login Instead",
                        modifier = Modifier.clickable(onClick = {
//                            navController.navigate("login_page"){
//                                popUpTo = navController.graph.startDestination
//                                launchSingleTop = true
//                            }
                        }
                        )
                    )
                    Spacer(modifier = Modifier.padding(20.dp))

                }

            }
        }
    }




