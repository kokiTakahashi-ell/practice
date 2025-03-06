package com.example.displayarticle

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.displayarticle.ui.theme.DisplayArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DisplayArticleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
//                    Article(
//                        head = getString(R.string.heading),
//                        content1 = getString(R.string.content1),
//                        content2 = getString(R.string.content1),
//                        modifier = Modifier
//                    )
                    TaskDone(
                        task1 = getString(R.string.task1),
                        task2 = getString(R.string.task2),
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Composable
fun Quadrant() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Quadrant 1", color = Color.White)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color.Green),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Quadrant 2", color = Color.White)
            }
        }
        Row(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color.Blue),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Quadrant 3", color = Color.White)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color.Yellow),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Quadrant 4", color = Color.Black)
            }
        }
    }
}

@Composable
fun TaskDone(task1: String, task2: String, modifier: Modifier = Modifier){
    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            val image = painterResource(id = R.drawable.ic_task_completed)
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
            )
            Text(
                text = task1,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .padding(top = 24.dp, bottom = 8.dp)
            )
            Text(
                text = task2,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = modifier
            )
        }
    }
}

@Composable
fun Article(head: String, content1: String, content2: String, modifier: Modifier = Modifier) {
    Column (modifier = Modifier) {
        val image = painterResource(id = R.drawable.bg_compose_background)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = head,
            fontSize = 24.sp,
            modifier = modifier
                .padding(16.dp)
        )
        Text(
            text = content1,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
        )
        Text(
            text = content2,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TestPreview() {
    DisplayArticleTheme {
//        Article(
//            head = "Jetpack Compose tutorial",
//            content1 = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
//            content2 = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app's UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI's construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name."
//        )
        TaskDone(
            task1 = "All tasks completed",
            task2 = "Nice work!"
        )
    }
}