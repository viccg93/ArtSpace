package com.codelabs.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelabs.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppLayout(modifier = Modifier.padding(innerPadding).background(Color(0xFFF6F4E9)))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        AppLayout()
    }
}

@Composable
fun AppLayout(modifier:Modifier = Modifier){
    //app logic
    var state by remember { mutableStateOf(0) }
    val slides = populateList()

    val currentSlide = slides.get(state)



    Column(modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        AppBanner(modifier = Modifier.fillMaxWidth()
            .weight(0.3f)
            .background(Color.White)
        )
        PresentationCanvas(imageSlide = currentSlide, modifier = Modifier.weight(3f))
        Spacer(Modifier.height(40.dp))
        ButtonBar(
            onclickPrev = {if(state > 0){state--}else{ state = 4}},
            onclickPost = {if(state < 4){state++}else{state = 0 }},
            modifier = Modifier.fillMaxWidth().weight(1f))
    }
}

@Composable
fun ButtonBar(
    onclickPrev: () -> Unit,
    onclickPost: () -> Unit,
    modifier: Modifier = Modifier){
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween){
        ImageControlButton(
            label = "Previa",
            onClick = onclickPrev,
            modifier = Modifier
            .padding(horizontal = 20.dp)
            .weight(1f)
        )

        ImageControlButton(
            label = "Siguiente",
            onClick = onclickPost,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
        )

    }
}

@Composable
fun ImageControlButton(modifier:Modifier = Modifier, label: String, onClick: () -> Unit){
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonColors(
            containerColor = Color(0xFF0E1428),
            contentColor = Color.White,
            disabledContentColor = Color.White,
            disabledContainerColor = Color.Gray
        )
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PresentationCanvas(imageSlide: ImageSlide, modifier:Modifier = Modifier){
    Column (modifier = modifier) {
        Image(
            painter = painterResource(imageSlide.id),
            contentDescription = null,
            modifier = Modifier.weight(2f),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        ImageDescription(
            title = imageSlide.title,
            author = imageSlide.author,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
        )
    }
}

@Composable
fun ImageDescription (title: String, author: String, modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Text(
            text = "Titulo: $title",
            color = Color.Black,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp
        )
        Text(
            text = author,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AppBanner (modifier:Modifier = Modifier){
    val iconsSet = Icons.Filled
    Row (modifier = modifier, horizontalArrangement = Arrangement.Start) {
        Icon(
            iconsSet.Menu,
            contentDescription = "three bar stacked, touch this to get a menu displayed",
            Modifier.padding(10.dp).size(40.dp),
            tint = Color.Black
        )
        Text(
            text = "ArtSpace",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
        )
    }
}

class ImageSlide(@DrawableRes val id: Int, val title: String, val author: String) {
}

fun populateList ():  ArrayList<ImageSlide>{
    var slides = ArrayList<ImageSlide>()
    slides.add(ImageSlide(R.drawable.img_1, "Photograps","BP Miller"))
    slides.add(ImageSlide(R.drawable.img_2, "A nice trip","Ivan Diaz"))
    slides.add(ImageSlide(R.drawable.img_3, "Camera, action!","Vika Strawberrika"))
    slides.add(ImageSlide(R.drawable.img_4, "Sunset","Clara Metivier"))
    slides.add(ImageSlide(R.drawable.img_5, "White horse","Nachelle Nocom"))
    return slides

}