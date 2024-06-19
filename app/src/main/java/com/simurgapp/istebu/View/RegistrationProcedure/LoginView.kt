package com.simurgapp.istebu.View.RegistrationProcedure

import LoginsigninViewModel
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.simurgapp.istebu.Model.SharedPreferencesHelper
import com.simurgapp.istebu.View.UIElements.CircleImage
import com.simurgapp.istebu.View.UIElements.FilledTonalButton
import com.simurgapp.istebu.View.UIElements.TextFieldOne
import com.simurgapp.istebu.ViewModel.BackViewModel
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.Orange500


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
    val emailText = remember { mutableStateOf("a@b.com") }
    val passwordText = remember { mutableStateOf("123456") }
    val iBack by backViewModel.isBack.collectAsState()
    val context = LocalContext.current
    val sharedPreferencesHelper = remember { SharedPreferencesHelper(context) }
    val viewModel = LoginsigninViewModel(sharedPreferencesHelper)
    val signInState by viewModel.signInState.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    var firstSign = remember {
        mutableStateOf(true)
    }



    if ( isLoggedIn && firstSign.value) {
        firstSign.value = false
        navController.navigate("careerFieldsView/freelancers", builder = {
            popUpTo("login") {
                inclusive = true
            }

        })
    }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextFieldOne("Email", Icons.Default.Email, Orange200, Orange500, emailText)
        Spacer(modifier = Modifier.padding(8.dp))
        TextFieldOne("Password", Icons.Default.Lock, Orange200, Orange500, passwordText ,true)
        Spacer(modifier = Modifier.height(48.dp))

        FilledTonalButton(onClick = {

            viewModel.signIn(emailText.value, passwordText.value , onError ={
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })


        }, text = "Log in" )
        Spacer(modifier = Modifier.height(16.dp))
        FilledTonalButton(onClick = {
            backViewModel.increment()
            println(iBack)
            navController.navigate("signup"){


            }
            

        }, text = "Sign up" )
        Spacer(modifier = Modifier.height(16.dp))
        CircleImage(imageUrl = "https://banner2.cleanpng.com/20180413/rfe/kisspng-google-logo-google-cloud-platform-gboard-google-pa-4-5ad0e95b57ec99.9189954815236406673602.jpg")

    }
}

