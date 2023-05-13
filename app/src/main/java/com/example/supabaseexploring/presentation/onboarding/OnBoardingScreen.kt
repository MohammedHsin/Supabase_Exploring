package com.example.supabaseexploring.presentation.onboarding

import androidx.compose.animation.*
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.End
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = hiltViewModel()
) {


    val state by viewModel.state.collectAsState()




    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
    ) {


        AnimatedVisibility(visible = state.page == 0) {
            Text(
                text = "Welcome to the club! Now let's continue building you profile ..",
                fontSize = 30.sp, textAlign = TextAlign.Center, modifier = Modifier
                    .padding(30.dp)
            )
        }

        AnimatedVisibility(visible = state.page == 1) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "What do they call you?", fontWeight = FontWeight.SemiBold,
                    fontSize = 40.sp, textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = state.name,
                    onValueChange = {
                        viewModel.onNameChange(it)
                    },
                    shape = RoundedCornerShape(14.dp),
                )
            }

        }


        AnimatedVisibility(visible = state.page == 2) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "How old are you?", fontWeight = FontWeight.SemiBold,
                    fontSize = 40.sp, textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = state.age.toString(),
                    onValueChange = { viewModel.onAgeChange((if (it != "") it else 0).toString().toInt())},
                    shape = RoundedCornerShape(14.dp)
                )
            }
        }



        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.End, modifier = Modifier
                .fillMaxWidth()
                .padding(end = 53.dp)
        ) {

            Button(
                onClick = { viewModel.onPageChange() }, shape = RoundedCornerShape(14.dp)
            ) {
                Text(text = "Next", fontSize = 18.sp)
            }

        }
    }


}

