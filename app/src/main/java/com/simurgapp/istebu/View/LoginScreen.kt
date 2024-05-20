package com.simurgapp.istebu.View

import LoginsigninViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.simurgapp.istebu.ui.theme.IsteBuTheme
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.Orange500
import com.simurgapp.istebu.ui.theme.darkerOrange
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavHostController){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LogInContent(navController = navController)
    }
}


@Composable
 fun FilledTonalButtonExample(onClick: () -> Unit , text : String) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(Orange200, darkerOrange)
    )

    Box(
        modifier = Modifier
            .background(brush = gradient, shape = RoundedCornerShape(20.dp))
            .padding(1.dp),

    ) {
        FilledTonalButton(
            onClick = onClick,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(22.dp),
            modifier = Modifier
                .fillMaxWidth(0.40f)
        ) {
            Text(text , fontSize = 20.sp)
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SignLoginTextField(labelText: String, leadingIconOne: ImageVector?, colorOne:Color, colorTwo: Color , text : MutableState<String>) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val inputModifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth(0.66f)
        .border(
            BorderStroke(
                width = 4.dp,
                brush = Brush.horizontalGradient(colors = listOf(colorOne, colorTwo))
            ),
            shape = RoundedCornerShape(50.dp)
        )
        .padding(8.dp)
    var leadingIcons: (@Composable () -> Unit)? = null
    if (leadingIconOne != null) {
        leadingIcons = {
            Icon(imageVector = leadingIconOne, contentDescription = null)
        }
    }
    Column {
        // TextField
        TextField(value = text.value, onValueChange = {
            text.value = it
        },

            leadingIcon = leadingIcons,

            label = { Text(labelText) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            modifier = inputModifier,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            singleLine = true

        )


    }
}

@Composable
fun LogInContent(navController: NavHostController) {
    val viewModel :LoginsigninViewModel = LoginsigninViewModel()
    val signInState by viewModel.signInState.collectAsState()
    var emailText = remember { mutableStateOf("") }
    var passwordText = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()


    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        SignLoginTextField("Email", Icons.Default.Email, Orange200, Orange500, emailText)
        Spacer(modifier = Modifier.padding(8.dp))
        SignLoginTextField("Password", Icons.Default.Lock, Orange200, Orange500, passwordText)
        Text(text = emailText.value)
        Text(text = passwordText.value)

        FilledTonalButtonExample(onClick = {

            coroutineScope.launch { // Use the coroutine scope to launch a new coroutine
                var result = viewModel.signIn(emailText.value, passwordText.value)
                println("basıldı")
                println(result)

            }

        }, text = "Log in" )
        Spacer(modifier = Modifier.height(16.dp))
        FilledTonalButtonExample(onClick = {

            navController.navigate("signup"){


            }

        }, text = "Sign up" )

    }
}

