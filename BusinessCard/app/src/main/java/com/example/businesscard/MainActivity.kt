package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme


//val MyAppIcons = Icons.Rounded
//SomeComposable(icon = MyAppIcons.Menu)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    BusinessApp()
                }
            }
        }
    }
}

@Composable
fun BusinessApp(modifier: Modifier = Modifier.background(Color.LightGray)){
    Box (
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        Logo(
            modifier = Modifier
                .align(Alignment.Center)
        )
        Information(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun Information (modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp, bottom = 64.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconTextRow(icon = Icons.Rounded.Phone, text = stringResource(R.string.phone))
        IconTextRow(icon = Icons.Rounded.Share, text = stringResource(R.string.website))
        IconTextRow(icon = Icons.Rounded.Email, text = stringResource(R.string.email))
    }
}
@Composable
fun IconTextRow(icon: ImageVector, text: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.padding(end = 8.dp),
            tint = Color(0xFF3ddc84)
        )
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}
@Composable
private fun Logo (modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val image = painterResource(id = R.drawable.android_logo)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .height(128.dp)
                .width(128.dp)
                .background(Color.Black)
                .padding(8.dp)
        )
        Text(
            text = stringResource(R.string.name),
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = modifier
                .padding(top = 24.dp, bottom = 8.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.title),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF3ddc84),
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    BusinessCardTheme {
//        Logo()
//        Information()
        BusinessApp()
    }
}