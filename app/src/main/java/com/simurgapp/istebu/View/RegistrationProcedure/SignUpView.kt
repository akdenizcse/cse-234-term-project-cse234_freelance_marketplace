package com.simurgapp.istebu.View.RegistrationProcedure

import LoginsigninViewModel
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.simurgapp.istebu.Model.SharedPreferencesHelper
import com.simurgapp.istebu.View.UIElements.FilledTonalButton
import com.simurgapp.istebu.View.UIElements.TextFieldOne
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.Orange500


@Composable
fun SignUpScreen(navController: NavController) {
    val emailText = remember { mutableStateOf("") }
    val passwordText = remember { mutableStateOf("") }
    val passwordText2 = remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferencesHelper = remember { SharedPreferencesHelper(context) }
    val viewModel = LoginsigninViewModel(sharedPreferencesHelper)


Box( modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center)
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextFieldOne("Email", Icons.Default.Email, Orange200, Orange500, emailText)
        Spacer(modifier = Modifier.padding(8.dp))
        TextFieldOne("Password", Icons.Default.Lock, Orange200, Orange500, passwordText,true)
        Spacer(modifier = Modifier.padding(8.dp))
        TextFieldOne(
            "Password Again",
            Icons.Default.Lock,
            Orange200,
            Orange500,
            passwordText2,
            true
        )
        Spacer(modifier = Modifier.padding(16.dp))

        FilledTonalButton(onClick = {
            val result = viewModel.signUp(emailText.value, passwordText.value, onSuccess = {
               navController.popBackStack()
            }, onError = {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
            println(result)

        }, text = "Sign Up")

    }
}
}