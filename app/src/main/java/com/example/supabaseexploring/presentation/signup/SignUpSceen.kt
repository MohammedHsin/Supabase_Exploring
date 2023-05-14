package com.example.supabaseexploring.presentation.signup


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import com.example.supabaseexploring.R
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.supabaseexploring.common.UIState
import com.example.supabaseexploring.common.components.CircularProgressBar
import com.example.supabaseexploring.presentation.common.LoadingAnimation
import com.example.supabaseexploring.presentation.common.rememberImeState
import com.example.supabaseexploring.ui.theme.primaryColor
import com.example.supabaseexploring.ui.theme.whiteBackground
import kotlin.math.sign

@Composable
fun SignupScreen(
    navController: NavHostController,
viewModel: SignupViewModel = hiltViewModel()
) {

    val signupState by viewModel.signupState.collectAsState()
    val signupUIState by viewModel.signupUIState.collectAsState()

    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val imeState = rememberImeState()
    val localFocusManager = LocalFocusManager.current

    val alpha = animateFloatAsState(if (signupUIState is UIState.Loading) 0.5f else 1f)

    LaunchedEffect(key1 = signupUIState) {
        when (signupUIState) {

            is UIState.Idle -> {
                Log.d("hello", "nothing ..")
            }

            is UIState.Success -> {
                Log.d("hello", "you sign up !")
            }
            is UIState.Loading -> {
                Log.d("hello", "Loading ...")
            }
            is UIState.Error -> {
                Log.d("hello", (signupUIState as UIState.Error).message.toString())
            }
        }
    }

    val image = painterResource(id = R.drawable.register_page)

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        BoxWithConstraints(contentAlignment = Alignment.Center) {


            Box(modifier = Modifier
                .fillMaxSize()
                .alpha(alpha.value), contentAlignment = Alignment.BottomCenter) {
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
                        .verticalScroll(scrollState),
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
                            isError = !signupState.validUsername && signupState.username.isNotBlank(),
                            value = signupState.username,
                            onValueChange = { viewModel.onUsernameChange(it) },
                            label = { Text(text = "Username") },
                            placeholder = { Text(text = "Username") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(0.8f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(onNext = {
                                localFocusManager.moveFocus(FocusDirection.Down)
                            })
                        )



                        OutlinedTextField(
                            isError = !signupState.validEmail && signupState.email.isNotBlank(),
                            value = signupState.email,
                            onValueChange = { viewModel.onEmailChange(it) },
                            label = { Text(text = "Email Address") },
                            placeholder = { Text(text = "Email Address") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(0.8f) ,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(onNext = {
                            localFocusManager.moveFocus(FocusDirection.Down)
                        })
                        )



                        OutlinedTextField(
                            isError = !signupState.validPassword && signupState.password.isNotBlank(),
                            value = signupState.password,
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

                                    Icon(
                                        painter = painterResource(id = R.drawable.password_eye),
                                        contentDescription = "",
                                        tint = if (passwordVisibility.value) primaryColor else Color.Gray
                                    )
                                }
                            },
                            visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(onNext = {
                                localFocusManager.moveFocus(FocusDirection.Down)
                            })

                        )

                        OutlinedTextField(
                            isError = !signupState.validRePassword && signupState.confirmPassword.isNotBlank(),
                            value = signupState.confirmPassword,
                            onValueChange = { viewModel.onConfirmPasswordChange(it) },
                            label = { Text(text = "Confirm Password") },
                            placeholder = { Text(text = "Confirm Password") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(0.8f),
                            trailingIcon = {
                                IconButton(onClick = {
                                    confirmPasswordVisibility.value =
                                        !confirmPasswordVisibility.value
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.password_eye),
                                        contentDescription = "",
                                        tint = if (confirmPasswordVisibility.value) primaryColor else Color.Gray
                                    )
                                }
                            },
                            visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = {
                                signup(signupState, viewModel, context)
                            })

                        )



                        Spacer(modifier = Modifier.padding(10.dp))



                        Button(
                            onClick = {
                                signup(signupState, viewModel, context)
                            }, modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(50.dp)
                        ) {
                            Text(text = "Sign Up", fontSize = 20.sp)
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(
                            text = "Login Instead",
                            modifier = Modifier.clickable(onClick = {

                                navController.navigate("login") {
                                    launchSingleTop = true
                                }


                            }
                            )
                        )
                        Spacer(modifier = Modifier.padding(20.dp))

                    }

                }


            }
           if (signupUIState is UIState.Loading) {
               LoadingAnimation()
           }
        }
    }
}

private fun signup(
    signupState: SignupState,
    viewModel: SignupViewModel,
    context: Context
) {
    if (
        signupState.validEmail
        && signupState.validPassword
        && signupState.validRePassword
        && signupState.validUsername
    ) {
        viewModel.performSignUp(
            signupState.email,
            signupState.username,
            signupState.password
        )
    } else {
        Toast.makeText(
            context,
            "invalid data!",
            Toast.LENGTH_LONG
        ).show()
    }
}

@Composable
@Preview
fun pre(){
    CircularProgressBar(isActivated = true)
}




