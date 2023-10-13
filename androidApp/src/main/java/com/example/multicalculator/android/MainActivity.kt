package com.example.multicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
//import com.example.multicalculator.Greeting
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
//import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
//import androidx.compose.material3.Button
import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {       CalcView()         }
            }

        }
    }
}


@Preview
@Composable
fun CalcView(){
    val displayText = remember {mutableStateOf("0")}

    Column(modifier = Modifier.background(Color.LightGray)) {
        Row {
            CalcDisplay(display = displayText)
                    }
        Row {
            Column {for (i in 7 downTo 1 step 3) {
                CalcRow(displayText, i, 3)

            }
                Row {
                    CalcNumericButton(0, displayText)
                    CalcEqualsButton(displayText)
                }

            }
            Column {

                CalcOperationButton("+", displayText)
                CalcOperationButton("-", displayText)
                CalcOperationButton("*", displayText)
                CalcOperationButton("/", displayText)


            }
        }

    }

}
@Composable
fun CalcRow(display: MutableState<String>, startNum: Int, numButtons: Int){
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)) {
        for (i in startNum until endNum) {
            CalcNumericButton(i, display)
        }

    }
}
@Composable
fun CalcDisplay(display: MutableState<String>){
    Text(text = display.value, modifier = Modifier
        .padding(5.dp)
        .height(50.dp)
        .fillMaxWidth()   )


}
@Composable
fun CalcNumericButton(number: Int, display: MutableState<String>){
    ElevatedButton(modifier = Modifier.padding(4.dp),
        onClick = {
            display.value += number.toString()}
    ) {
        Text(text = number.toString())
    }
}
@Composable
fun CalcOperationButton(operation: String, display: MutableState<String>){
    display.value = operation
    ElevatedButton(modifier = Modifier.padding(4.dp),
        onClick = {}
    ) {
        Text( text = operation)
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

