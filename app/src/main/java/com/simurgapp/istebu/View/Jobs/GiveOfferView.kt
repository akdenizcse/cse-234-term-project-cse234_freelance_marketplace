package com.simurgapp.istebu.View.Jobs

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.LocalAtm
import androidx.compose.material.icons.filled.Money
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.simurgapp.istebu.View.UIElements.FilledTonalButton
import com.simurgapp.istebu.View.UIElements.TextFieldOne
import com.simurgapp.istebu.ViewModel.JobsViewModel
import com.simurgapp.istebu.ui.theme.Orange200
import com.simurgapp.istebu.ui.theme.darkerOrange
import androidx.lifecycle.viewmodel.compose.viewModel
import com.simurgapp.istebu.Model.SharedPreferencesHelper
import com.simurgapp.istebu.View.UIElements.DatePicker
import com.simurgapp.istebu.View.UIElements.IconButtonOne
import java.util.Calendar

@Composable
fun GiveOfferView(navController: NavController, UID: String, viewModel: JobsViewModel = viewModel()) {

    val price = remember { mutableStateOf("") }
    val estimatedTime = remember { mutableStateOf("") }
    val comment = remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferencesHelper = SharedPreferencesHelper(context)
    val currentUserID = sharedPreferencesHelper.getUID()

    val selectedDate = remember { mutableStateOf("") }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth() // Make the Column take up 80% of the screen width
                .padding(bottom = 66.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldOne(
                labelText = "Price",
                leadingIconOne = Icons.Default.LocalAtm,
                colorOne = Orange200,
                colorTwo = darkerOrange,
                text = price
            )
            Spacer(modifier = Modifier.height(8.dp))


            Spacer(modifier = Modifier.height(8.dp))
            TextFieldOne(
                labelText = "Comment",
                leadingIconOne = Icons.Default.Comment,
                colorOne = Orange200,
                colorTwo = darkerOrange,
                text = comment
            )
            Spacer(modifier = Modifier.height(16.dp))
            DatePicker(
                onDateSelected = { date ->
                    selectedDate.value = date
                }
            )




            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                FilledTonalButton(onClick = {
                    if (currentUserID != null) {
                        viewModel.giveOffer(
                            UID,
                            currentUserID,
                            price = price.value.toInt(),
                            estimatedTime = selectedDate.value,
                            comment.value,
                            {navController.popBackStack()},
                            {Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()}
                        )
                    }
                }, text = "Give Offer")
            }
        }
    }
}