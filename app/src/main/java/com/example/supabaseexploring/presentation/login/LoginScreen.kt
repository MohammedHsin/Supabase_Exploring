package com.example.supabaseexploring.presentation.login


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.supabaseexploring.R
import com.example.supabaseexploring.common.UIState
import com.example.supabaseexploring.common.components.CircularProgressBar
import com.example.supabaseexploring.presentation.common.LoadingAnimation
import com.example.supabaseexploring.presentation.common.rememberImeState
import com.example.supabaseexploring.ui.theme.primaryColor
import com.example.supabaseexploring.ui.theme.whiteBackground
import io.github.jan.supabase.gotrue.gotrue


@Composable
fun LoginPage(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {


    val context = LocalContext.current

    val loginState by viewModel.loginState.collectAsState()
    val loginUIState by viewModel.loginUIState.collectAsState()

    val scrollState = rememberScrollState()
    val imeState = rememberImeState()


    val alpha = animateFloatAsState(if (loginUIState is UIState.Loading) 0.5f else 1f)

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

    LaunchedEffect(key1 = viewModel.goTrueClient){


        if (viewModel.getUserIfAny() != null){
            Log.d("hello", "LoginPage: You are in  ..${viewModel.getUserIfAny()} ")
        }
        else{
            Log.d("hello", "LoginPage: you are so out")
        }
    }


    LaunchedEffect(key1 = imeState.value){
        if(imeState.value){
            scrollState.scrollTo(206)
        }
    }




    val image = painterResource(id = R.drawable.login_image)


    val passwordVisibility = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val localFocusManager = LocalFocusManager.current



    Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center) {

        BoxWithConstraints(contentAlignment = Alignment.Center) {

        Box(modifier = Modifier
            .fillMaxSize()
            .alpha(alpha.value), contentAlignment = Alignment.BottomCenter) {
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
                    .verticalScroll(scrollState)
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
                        isError = !loginState.validEmail && loginState.email.isNotBlank(),
                        value = loginState.email,
                        onValueChange = { viewModel.onEmailChange(it) },
                        label = { Text(text = "Email Address") },
                        placeholder = { Text(text = "Email Address") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = {
                            localFocusManager.moveFocus(FocusDirection.Down)
                        })
                    )


                    OutlinedTextField(
                        isError = !loginState.validPassword && loginState.password.isNotBlank(),
                        value = loginState.password,
                        onValueChange = { viewModel.onPasswordChange(it) },
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
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = {login(loginState, viewModel, context)})
                    )


                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(
                        onClick = {
                            login(loginState, viewModel, context)
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

                    Button( onClick = {
                        Log.d("hello", viewModel.getUserIfAny().toString())
                    }) {
                        Text(text = "check authentication")
                    }
                }


            }
        }

        if (loginUIState is UIState.Loading){
            LoadingAnimation()
        }

    }
}
}

private fun login(
    loginState: LoginState,
    viewModel: LoginViewModel,
    context: Context
) {
    if (loginState.validEmail && loginState.validPassword) {
        viewModel.onPerformLogin(
            loginState.email,
            loginState.password
        )
    } else {
        Toast.makeText(
            context,
            "invalid data! please check your email and password",
            Toast.LENGTH_LONG
        ).show()
    }
}