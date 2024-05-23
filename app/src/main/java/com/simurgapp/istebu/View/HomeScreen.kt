package com.simurgapp.istebu.View

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.simurgapp.istebu.View.UIElements.IconButtonOne
import com.simurgapp.istebu.View.UIElements.TextFieldOne
import com.simurgapp.istebu.ui.theme.IsteBuTheme

@Composable
fun HomeScreen() {
    var searchText = remember {
        mutableStateOf("")
    }
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
  Column {
      Row {
          TextFieldOne(labelText = "Ara", leadingIconOne = null, colorOne = Color.Black, colorTwo = Color.Red, text = searchText )

IconButtonOne(onClick = { /*TODO*/ }, icon = Icons.Filled.Search, contentDescription = "Search Button", buttonSize = 64 , iconSize = 48)      }


  }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    IsteBuTheme {
        HomeScreen()
    }
}