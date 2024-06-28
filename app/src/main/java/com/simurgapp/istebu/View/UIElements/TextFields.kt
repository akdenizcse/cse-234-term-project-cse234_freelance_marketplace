package com.simurgapp.istebu.View.UIElements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldOne(labelText: String, leadingIconOne: ImageVector?, colorOne: Color, colorTwo: Color, text : MutableState<String> , isPassword : Boolean = false) {

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
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None ,
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