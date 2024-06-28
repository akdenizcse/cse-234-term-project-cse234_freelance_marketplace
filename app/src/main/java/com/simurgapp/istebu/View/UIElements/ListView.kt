package com.simurgapp.istebu.View.UIElements

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simurgapp.istebu.Model.tempData

@Composable

fun <T> GenericListView (list : List<T> ,itemContent: @Composable (T , Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(list) {  item ->
            val index = list.indexOf(item)
            itemContent(item, index)

        }
    }

}
@Composable
fun CircleImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    size: Int = 100
) {

    val painter: Painter = rememberImagePainter(
        data = if (imageUrl == "") tempData().images.random() else imageUrl,
        builder = {
            transformations(CircleCropTransformation())
        }
    )
    val imageSize = size*0.8

    Box(
        modifier = modifier.size(size.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(imageSize.dp)
                .padding(4.dp)
                .clip(CircleShape)
            ,
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )
    }
}

@Composable
fun PicTextItem(sire: String, title: String, subtitle: String , imageUrl: String,onClick: () -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth().clickable( onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {

        CircleImage(imageUrl = imageUrl, size = 100)
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(title , fontSize = 24.sp, fontWeight = FontWeight.Bold)
            if (subtitle != "") Text(subtitle)

        }
    }
}