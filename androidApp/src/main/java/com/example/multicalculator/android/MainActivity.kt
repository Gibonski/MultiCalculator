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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

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
fun GreetingView(text: String) {
    Text(text = text)
}



@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
@Preview
@Composable
fun CalcView(){

}
@Composable
fun CalcRow(display: MutableState<String>, startNum: Int, numButtons: Int){

}
@Composable
fun CalcDisplay(display: MutableState<String>){
    Text(display.value, modifier = Modifier.padding(5.dp).height(50.dp))

}
@Composable
fun CalcNumericButton(number: Int, display: MutableState<String>){
    ElevatedButton(modifier = Modifier.padding(4.dp),
        onClick = {}
    ) {
        Text("")
    }
}
@Composable
fun CalcOperationButton(operation: MutableState<String>, display: MutableState<String>){
    ElevatedButton(modifier = Modifier.padding(4.dp),
        onClick = {}
    ) {
        Text("")
    }
}
@Composable
fun CalcEqualsButton(display: MutableState<String>){
    ElevatedButton(modifier = Modifier.padding(4.dp),
        onClick = {0}
    ) {
        Text("=")
    }
}

