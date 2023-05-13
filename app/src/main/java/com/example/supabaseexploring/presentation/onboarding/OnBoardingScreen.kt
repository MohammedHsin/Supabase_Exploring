package com.example.supabaseexploring.presentation.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
@Preview(showBackground = true)
fun OnBoardingScreen(){
    var state by remember {
        mutableStateOf(true)
    }


    Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center) {

        Column {


        Row() {


            AnimatedVisibility(visible = state) {
                Column(modifier = Modifier.background(Color.Red)) {
                    Text(text = "Welcome to screen one !!")
                    TextField(value = "this is text one", onValueChange = {})
                }
            }

            AnimatedVisibility(visible = !state) {
                Column(modifier = Modifier.background(Color.Blue)) {
                    Text(text = "This is the second screen ")
                    TextField(value = "this is text tow", onValueChange = {})

                }
            }

        }





            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { state = !state }) {
                Text(text = "switch")
            }
        }
    }
}