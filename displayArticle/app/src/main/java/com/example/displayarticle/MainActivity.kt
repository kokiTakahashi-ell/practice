package com.example.displayarticle

import android.graphics.Typeface.BOLD
import android.graphics.Typeface.defaultFromStyle
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.displayarticle.ui.theme.DisplayArticleTheme
import androidx.compose.ui.res.stringResource

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
//                    Quadrant(
//                        first1 = getString(R.string.first1),
//                        first2 = getString(R.string.first2),
//                        second1 = getString(R.string.second1),
//                        second2 = getString(R.string.second2),
//                        third1 = getString(R.string.third1),
//                        third2 = getString(R.string.third2),
//                        fourth1 = getString(R.string.fourth1),
//                        fourth2 = getString(R.string.fourth2),
//                        modifier = Modifier
//                    )
//                    ComposeQuadrantApp()
                    Quadrant_Test(
                        first1 = getString(R.string.first1),
                        first2 = getString(R.string.first2),
                        second1 = getString(R.string.second1),
                        second2 = getString(R.string.second2),
                        third1 = getString(R.string.third1),
                        third2 = getString(R.string.third2),
                        fourth1 = getString(R.string.fourth1),
                        fourth2 = getString(R.string.fourth2),
                        modifier = Modifier
                    )
//                    Article(
//                        head = getString(R.string.heading),
//                        content1 = getString(R.string.content1),
//                        content2 = getString(R.string.content1),
//                        modifier = Modifier
//                    )
//                    TaskDone(
//                        task1 = getString(R.string.task1),
//                        task2 = getString(R.string.task2),
//                        modifier = Modifier
//                    )
                }
            }
        }
    }
}

@Composable
fun ComposeQuadrantApp() {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                title = stringResource(R.string.first1),
                description = stringResource(R.string.first2),
                backgroundColor = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                title = stringResource(R.string.second1),
                description = stringResource(R.string.second2),
                backgroundColor = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                title = stringResource(R.string.third1),
                description = stringResource(R.string.third2),
                backgroundColor = Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)
            )
            ComposableInfoCard(
                title = stringResource(R.string.fourth1),
                description = stringResource(R.string.fourth2),
                backgroundColor = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ComposableInfoCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun Quadrant_Test(first1: String, first2: String, second1: String, second2: String, third1: String, third2: String, fourth1: String, fourth2: String, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.weight(1f)) {
            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFEADDFF)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                Text(
                    text = first1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = first2,
                    textAlign = TextAlign.Justify,
                    fontSize = TextUnit.Unspecified,
                )
            }
            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFD0BCFF)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                Text(
                    text = second1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = second2,
                    textAlign = TextAlign.Justify,
                    fontSize = TextUnit.Unspecified,
                )
            }
        }
        Row(modifier = Modifier.weight(1f)) {
            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFB69DF8)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                Text(
                    text = third1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = third2,
                    textAlign = TextAlign.Justify,
                    fontSize = TextUnit.Unspecified,
                )
            }
            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFF6EDFF)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                Text(
                    text = fourth1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = fourth2,
                    textAlign = TextAlign.Justify,
                    fontSize = TextUnit.Unspecified,
                )
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
fun Quadrant(first1: String, first2: String, second1: String, second2: String, third1: String, third2: String, fourth1: String, fourth2: String, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color(0xFFEADDFF)),
                contentAlignment = Alignment.Center
            ) {
                Column (
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ){
                    Text(
                        text = first1,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                    )
                    Text(
                        text = first2,
                        textAlign = TextAlign.Justify,
                        fontSize = TextUnit.Unspecified,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color(0xFFD0BCFF)),
                contentAlignment = Alignment.Center
            ) {
                Column (
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ){
                    Text(
                        text = second1,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                    )
                    Text(
                        text = second2,
                        textAlign = TextAlign.Justify,
                        fontSize = TextUnit.Unspecified,
                    )
                }
            }
        }
        Row(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color(0xFFB69DF8)),
                contentAlignment = Alignment.Center
            ) {
                Column (
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ){
                    Text(
                        text = third1,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                    )
                    Text(
                        text = third2,
                        textAlign = TextAlign.Justify,
                        fontSize = TextUnit.Unspecified,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color(0xFFF6EDFF)),
                contentAlignment = Alignment.Center
            ) {
                Column (
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ){
                    Text(
                        text = fourth1,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                    )
                    Text(
                        text = fourth2,
                        textAlign = TextAlign.Justify,
                        fontSize = TextUnit.Unspecified,
                    )
                }
            }
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
//            modifier = Modifier.fillMaxWidth()
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
//                .fillMaxWidth(),
        )
        Text(
            text = content2,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(16.dp)
//                .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TestPreview() {
    DisplayArticleTheme {
        Article(
            head = "Jetpack Compose tutorial",
            content1 = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
            content2 = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app's UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI's construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name."
        )
//        TaskDone(
//            task1 = "All tasks completed",
//            task2 = "Nice work!"
//        )
//        Quadrant(
//            first1 = "Text composable",
//            first2 = "Displays text and follows the recommended Material Design guidelines.",
//            second1 = "Image composable",
//            second2 = "Creates a composable that lays out and draws a given Painter class object.",
//            third1 = "Row composable",
//            third2 = "A layout composable that places its children in a horizontal sequence.",
//            fourth1 = "Column composable",
//            fourth2 = "A layout composable that places its children in a vertical sequence."
//        )
    }
}