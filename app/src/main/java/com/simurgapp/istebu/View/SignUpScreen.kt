package com.simurgapp.istebu.View

import LoginsigninViewModel
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.Orange500
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen() {
    var emailText = remember { mutableStateOf("") }
    var passwordText = remember { mutableStateOf("") }
    var passwordText2 = remember { mutableStateOf("") }
    val viewModel = LoginsigninViewModel()
    val coroutineScope = rememberCoroutineScope()


Box( modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center)
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SignLoginTextField("Email", Icons.Default.Email, Orange200, Orange500, emailText)
        Spacer(modifier = Modifier.padding(8.dp))
        SignLoginTextField("Password", Icons.Default.Lock, Orange200, Orange500, passwordText)
        Spacer(modifier = Modifier.padding(8.dp))
        SignLoginTextField(
            "Password Again",
            Icons.Default.Lock,
            Orange200,
            Orange500,
            passwordText2
        )
        Spacer(modifier = Modifier.padding(16.dp))

        FilledTonalButtonExample(onClick = {
            coroutineScope.launch {
                var result = viewModel.signUp(emailText.value, passwordText.value)
                println(result)
            }
        }, text = "Sign Up")

    }
}
}