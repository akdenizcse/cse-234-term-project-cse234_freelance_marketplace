package com.simurgapp.istebu.View

import LoginsigninViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.simurgapp.istebu.Model.SharedPreferencesHelper
import com.simurgapp.istebu.ViewModel.BackViewModel
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.Orange500
import com.simurgapp.istebu.ui.theme.darkerOrange


@Composable
fun LoginScreen(navController: NavHostController , backViewModel: BackViewModel){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LogInContent(navController = navController, backViewModel)
    }
}




@Composable
fun LogInContent(navController: NavHostController, backViewModel: BackViewModel) {
    var emailText = remember { mutableStateOf("a@b.com") }
    var passwordText = remember { mutableStateOf("123456") }
    val iBack by backViewModel.isBack.collectAsState()
    val context = LocalContext.current
    val sharedPreferencesHelper = remember { SharedPreferencesHelper(context) }
    val viewModel :LoginsigninViewModel = LoginsigninViewModel(sharedPreferencesHelper)
    val signInState by viewModel.signInState.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()



    if (signInState?.isSuccess == true || isLoggedIn) {
        navController.navigate("home", builder = {
            popUpTo("login") {
                inclusive = true
            }

        })
        println("true ooduuuuuuuu")
    }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextField1("Email", Icons.Default.Email, Orange200, Orange500, emailText)
        Spacer(modifier = Modifier.padding(8.dp))
        TextField1("Password", Icons.Default.Lock, Orange200, Orange500, passwordText)
        Text(text = emailText.value)
        Text(text = passwordText.value)

        FilledTonalButton(onClick = {

            viewModel.signIn(emailText.value, passwordText.value)


        }, text = "Log in" )
        Spacer(modifier = Modifier.height(16.dp))
        FilledTonalButton(onClick = {
            println("basıldııııı")
            backViewModel.increment()
            println(iBack)
            navController.navigate("signup"){


            }

        }, text = "Sign up" )

    }
}

