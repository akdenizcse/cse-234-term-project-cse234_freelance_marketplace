package com.simurgapp.istebu.View.UIElements

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
@Composable

fun <T> GenericListView (list : List<T> ,itemContent: @Composable (T) -> Unit){
     LazyColumn(
         modifier = Modifier.fillMaxSize()
     ) {
        items(list) { item ->
            itemContent(item)
        }

        }
     }


@Composable
fun CircleImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    val painter: Painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            transformations(CircleCropTransformation())
        }
    )

    Box(
        modifier = modifier.size(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(80.dp)
                .padding(4.dp)
                .clip(CircleShape)
                    ,
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )
    }
}

@Composable
fun PicTextItem() {
Row {
    CircleImage(imageUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fpixlr.com%2Fimage-generator%2F&psig=AOvVaw1z1JasaKX0ApoOLMoVV8wx&ust=1716590292766000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCLjr06brpIYDFQAAAAAdAAAAABAE")
    Spacer(modifier = Modifier.width(16.dp))
    Column {
        Text("Title")
        Text("Subtitle")
    }
}
}

@Preview
@Composable
fun PicTextItemPreview() {
PicTextItem()
}

