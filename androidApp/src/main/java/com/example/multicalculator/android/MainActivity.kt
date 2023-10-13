package com.example.multicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.multicalculator.Greeting
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ElevatedButton
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingView(Greeting().greet())
                }
            }
        }
    }
}
@Composable
fun CalcView(){

}
fun CalcRow(){

}
fun CalcDisplay(){

}
fun CalcNumericButton(){

}
fun CalcOperationButton(){

}
@Composable
fun CalcEqualsButton(){
    val expanded = mutableStateOf("display")
    ElevatedButton(
        onClick = {0}
    ) {
        Text("=")
    }
}


@Composable
fun GreetingView(text: String) {
    Text(text = text)
}


@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
