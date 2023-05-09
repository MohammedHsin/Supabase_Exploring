package com.example.supabaseexploring.presentation.login


import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.supabaseexploring.R
import com.example.supabaseexploring.common.UIState
import com.example.supabaseexploring.ui.theme.primaryColor
import com.example.supabaseexploring.ui.theme.whiteBackground


@Composable
fun LoginPage(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {


    val loginState by viewModel.loginState.collectAsState()
    val loginUIState by viewModel.loginUIState.collectAsState()


    LaunchedEffect(key1 = loginUIState){
        when(loginUIState){
            is UIState.Idle ->{
                Log.d("hello", "LoginPage: nothing")
            }
            is UIState.Loading ->{
                Log.d("hello", "LoginPage: loading")
            }
            is UIState.Error ->{
                Log.d("hello", "LoginPage: ${(loginUIState as UIState.Error).message.toString()}")
            }
            is UIState.Success ->{
                Log.d("hello", "LoginPage: ${(loginUIState as UIState.Success).data.toString()}")
            }
        }
    }


    val image = painterResource(id = R.drawable.login_image)


    val passwordVisibility = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White), contentAlignment = Alignment.TopCenter
        ) {
            Image(painter = image, contentDescription = "")
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(whiteBackground)
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = "Sign In",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                ),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = loginState.email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = { Text(text = "Email Address") },
                    placeholder = { Text(text = "Email Address") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
//                        onImeActionPerformed = { _, _ ->
//                            focusRequester.requestFocus()
//                        }
                )

                OutlinedTextField(
                    value = loginState.password,
                    onValueChange = {viewModel.onPasswordChange(it)},
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.password_eye),
                                contentDescription = "",
                                tint = if (passwordVisibility.value) primaryColor else Color.Gray
                            )
                        }
                    },
                    label = { Text("Password") },
                    placeholder = { Text(text = "Password") },
                    singleLine = true,
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .focusRequester(focusRequester = focusRequester),
//                        onImeActionPerformed = { _, controller ->
//                            controller?.hideSoftwareKeyboard()
//                        }

                )

                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {
                              viewModel.onPerformLogin(
                                  loginState.email,
                                  loginState.password
                              )
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
                    Text(text = "Sign In", fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = "Create An Account",
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate("signup") {
                            launchSingleTop = true
                        }
                    })
                )
                Spacer(modifier = Modifier.padding(20.dp))
            }


        }
    }

}