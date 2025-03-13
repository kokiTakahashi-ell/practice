package com.example.workspace

import android.graphics.fonts.FontStyle
import android.graphics.fonts.FontStyle.FONT_WEIGHT_BOLD
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workspace.ui.theme.WorkSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){

                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp (){
    var status by remember { mutableStateOf("0") }
    Surface(modifier = Modifier) when (status){
        1->{
            ArtSpaceUI(
                resId = R.drawable.art_space,
                title = R.string.art_space,
                description = R.string.art_space_description,
                onValueChange = { status = it }
            )
        }
    }
}

@Composable
fun ArtSpaceUI(
    @DrawableRes resId: Int,
    @StringRes title: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
){
    val image = painterResource(id = resId)
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .padding(16.dp)
    )
    Column (
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp)
    ){
        Text(
            text = stringResource(id = title),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(id = description),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Button(
            onClick = {/*TODO*/},
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
        ) {
            Text("Previous")
        }
        Button(
            onClick = { /* Handle Next button click */ },
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
        ) {
            Text("Next")
        }
    }
}

//val styledText = buildAnnotatedString {
//
//    // 青色の"Hello"
//    withStyle(SpanStyle(color = Color.Blue)) {
//        append("Hello")
//    }
//
//    // デフォルトスタイルの空白
//    append(" ")
//
//    // 緑色の"Android"
//    withStyle(SpanStyle(color = Color.Green)) {
//        append("Android")
//    }
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkSpaceTheme {
        ArtSpaceUI()
    }
}