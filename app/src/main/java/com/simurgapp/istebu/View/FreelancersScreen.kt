package com.simurgapp.istebu.View

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.simurgapp.istebu.View.UIElements.GenericListView
import com.simurgapp.istebu.View.UIElements.PicTextItem

@Composable
fun FreelancersScreen() {
    val sampleItems = List(100) { "Item #$it" }
    GenericListView(sampleItems) { item ->
        PicTextItem()
    }
}
