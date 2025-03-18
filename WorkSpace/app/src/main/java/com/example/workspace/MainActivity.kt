package com.example.workspace

//import android.graphics.fonts.FontStyle
import android.graphics.fonts.FontStyle.FONT_WEIGHT_BOLD
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workspace.ui.theme.WorkSpaceTheme
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.max

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
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var status by remember { mutableIntStateOf(1) }
    Surface(modifier = Modifier.fillMaxSize()) {
        when (status) {
            1 -> {
                ArtSpaceUI(
                    resId = R.drawable.oranie,
                    title = R.string.art_title_oran,
                    description = R.string.art_author_oran,
                    onValueChange = { status = it },
                    value = status,
                    year = R.string.art_year_oran,
                )
            }
            2 -> {
                ArtSpaceUI(
                    resId = R.drawable.joseph,
                    title = R.string.art_title_joseph,
                    description = R.string.art_author_joseph,
                    year = R.string.art_year_joseph,
                    onValueChange = { status = it },
                    value = status
                )
            }
            3 -> {
                ArtSpaceUI(
                    resId = R.drawable.fransowa,
                    title = R.string.art_title_fran,
                    description = R.string.art_author_fran,
                    onValueChange = { status = it },
                    value = status,
                    year = R.string.art_year_fran,
                )
            }
            4 -> {
                ArtSpaceUI(
                    resId = R.drawable.okayama,
                    title = R.string.art_title_okayama,
                    description = R.string.art_author_okayama,
                    onValueChange = { status = it },
                    value = status,
                    year = R.string.art_year_okayama,
                )
            }
            else -> {
                ArtSpaceUI(
                    resId = R.drawable.yohan,
                    title = R.string.art_title_yohan,
                    description = R.string.art_author_yohan,
                    onValueChange = { status = it },
                    value = status,
                    year = R.string.art_year_yohan,
                )
            }
        }
    }
}

@Composable
fun ArtSpaceUI(
    @DrawableRes resId: Int,
    @StringRes title: Int,
    @StringRes description: Int,
    @StringRes year: Int,
    modifier: Modifier = Modifier,
    value: Int,
    onValueChange: (Int) -> Unit
){
    val image = painterResource(id = resId)
    val styledText = buildAnnotatedString {
        // 青色の"Hello"
        withStyle(SpanStyle(color = Color.Blue)) {
            append(stringResource(id = description))
        }

        // デフォルトスタイルの空白
        append(" ")

        // 緑色の"Android"
        withStyle(SpanStyle(color = Color.Green, fontStyle = FontStyle.Italic)) {
            append(stringResource(id = year))
        }
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,

    ){
//        Image(
//            painter = image,
//            contentDescription = null,
//            modifier = modifier
//                .fillMaxWidth()
//                .border(4.dp, Color.Gray)
//                .padding(16.dp)
//                .weight(0.5f)
//        )
        Box(
            modifier = Modifier
                .weight(0.5f)
                .padding(16.dp)
                .shadow(8.dp, shape = RoundedCornerShape(8.dp), clip = false) // Add shadow for 3D effect
                .border(4.dp, Color.Gray) // Mat border
                .padding(8.dp) // Space between the image and the mat border
                .widthIn(max = 400.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
//                    .aspectRatio(1f) // Maintain aspect ratio
                    .padding(start = 8.dp, end = 8.dp)
                    .widthIn(max = 400.dp)
                    .heightIn(max = 400.dp)
            )
        }
        Column (
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
                .border(1.dp, Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = stringResource(id = title),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
            )
            Text(
                text = styledText,
                modifier = Modifier.padding(8.dp),
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
                onClick = {
                    if(value >= 2)onValueChange(value - 1)
                    else onValueChange(5)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .widthIn(max = 200.dp, min = 120.dp)
//                    .weight(1f),
            ) {
                Text("Previous")
            }
            Button(
                onClick = {
                    if(value <= 4)onValueChange(value + 1)
                    else onValueChange(1)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .widthIn(max = 200.dp, min = 120.dp)
//                    .weight(1f),
            ) {
                Text("Next")
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkSpaceTheme {
//        ArtSpaceApp()
        ArtSpaceUI(
            resId = R.drawable.oranie,
            title = R.string.art_title_oran,
            description = R.string.art_author_oran,
            year = R.string.art_year_oran,
            onValueChange = { },
            value = 1
        )
    }
}