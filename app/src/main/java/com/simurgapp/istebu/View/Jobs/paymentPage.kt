package com.simurgapp.istebu.View.Jobs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.darkColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simurgapp.istebu.View.UIElements.FilledTonalButton
import com.simurgapp.istebu.View.UIElements.TextFieldOne
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.darkerOrange

@Composable
fun paymentPage() {
    var creditCardNumber = remember { mutableStateOf("") }
    var date = remember { mutableStateOf("") }
    var cvv = remember { mutableStateOf("") }
    var name = remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        TextFieldOne(
            labelText = "Name",
            leadingIconOne = Icons.Default.Person,
            colorOne = Orange200,
            colorTwo = darkerOrange,
            text = name
        )

        TextFieldOne(
            labelText = "Credit Card Number",
            leadingIconOne = Icons.Default.CreditCard,
            colorOne = Orange200,
            colorTwo = darkerOrange,
            text = creditCardNumber
        )

        TextFieldOne(
            labelText = "Date",
            leadingIconOne = null,
            colorOne = Orange200,
            colorTwo = darkerOrange,
            text = date
        )

        TextFieldOne(
            labelText = "CVV",
            leadingIconOne = null,
            colorOne = Orange200,
            colorTwo = darkerOrange,
            text = cvv
        )

        FilledTonalButton(
            onClick = { /*TODO*/ },
            text = "Pay"
        )
    }
}
