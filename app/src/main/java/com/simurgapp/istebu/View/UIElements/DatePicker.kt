package com.simurgapp.istebu.View.UIElements


import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simurgapp.istebu.ui.theme.darkerOrange
import java.util.*
@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    val month = remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    val day = remember { mutableStateOf(calendar.get(Calendar.DAY_OF_MONTH)) }
    val formattedDate = remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }

    // Initialize DatePickerDialog
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _: DatePicker, selectedYear, selectedMonth, selectedDayOfMonth ->
                year.value = selectedYear
                month.value = selectedMonth
                day.value = selectedDayOfMonth
                formattedDate.value = "${day.value}/${month.value + 1}/${year.value}"
                onDateSelected(formattedDate.value)
                showDialog.value = false // Dismiss dialog after date selection
            },
            year.value,
            month.value,
            day.value
        )
    }

    Box(
        modifier = modifier
            .clickable { showDialog.value = true }
            .border(4.dp, darkerOrange, RoundedCornerShape(24.dp))
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { showDialog.value = true },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(Icons.Default.CalendarMonth, contentDescription = "Select Date")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (formattedDate.value.isEmpty()) "Estimeted Date" else formattedDate.value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    // Show dialog when showDialog is true
    if (showDialog.value) {
        DisposableEffect(Unit) {
            datePickerDialog.show()
            onDispose {
                datePickerDialog.dismiss()
            }
        }
    }
}