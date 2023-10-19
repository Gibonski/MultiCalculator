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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight

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
    var leftNumber by rememberSaveable { mutableStateOf(0) }
    var rightNumber by rememberSaveable { mutableStateOf(0) }
    var operation by rememberSaveable { mutableStateOf("") }
    var complete by rememberSaveable { mutableStateOf(false) }
    if (complete && operation.isNotEmpty()) {
        var answer = 0
        when (operation) {
            "+" -> { answer = leftNumber + rightNumber  }
            "-" -> { answer = leftNumber - rightNumber}
            "*" -> { answer = leftNumber * rightNumber }
            "/" -> {
                if (rightNumber != 0) {answer = leftNumber / rightNumber }
                else { }
            }
        }
        complete = false
        operation = ""
        }
    else if (operation.isNotEmpty() && !complete) {
         displayText.value = rightNumber.toString()
    }
    else {
       displayText.value = leftNumber.toString()
    }
    fun equalsPress() {
         complete = true
    }
    fun operationPress(op: String) {
        if (!complete) {
            operation = op
        }
    }
    fun numberPress(btnNum: Int) {
       if (complete) {
            leftNumber = 0
            rightNumber = 0
            operation = ""
            complete = false
        }
        if (operation.isNotBlank() && !complete) {
            rightNumber = (rightNumber * 10) + btnNum
        } else {
            leftNumber = (leftNumber * 10) + btnNum
        }
    }
    Column(modifier = Modifier.background(Color.LightGray)) {
        Row {
            CalcDisplay(display = displayText)
        }
        Row {
            Column {for (i in 7 downTo 1 step 3) {
                CalcRow({ number -> numberPress(number) }, i, 3)
            }
                Row {
                    CalcNumericButton(0, { number -> numberPress(number) })
                    CalcEqualsButton { equalsPress() }
                }

            }
            Column {
                CalcOperationButton("+") { operationPress("+") }
                CalcOperationButton("-") { operationPress("-") }
                CalcOperationButton("*") { operationPress("*") }
                CalcOperationButton("/") { operationPress("/") }
            }
        }

    }

}
@Composable
fun CalcRow(onPress: (number: Int) -> Unit, startNum: Int, numButtons: Int) {
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)) {
        for (i in startNum until endNum) {
            CalcNumericButton(i, onPress)
        }
    }
}
@Composable
fun CalcDisplay(display: MutableState<String>){
    Text(text = display.value, modifier = Modifier
        .padding(5.dp)
        .height(50.dp)
        .fillMaxWidth() , fontWeight = FontWeight.Bold  )
}
@Composable

fun CalcNumericButton(number: Int, onPress: (number: Int) -> Unit) {
    ElevatedButton(
        modifier = Modifier.padding(4.dp),
        onClick = {
            onPress(number)
        }
    ) {
        Text(text = number.toString())
    }
}
@Composable
fun CalcOperationButton(operation: String, onPress:  (operation: String) -> Unit) {
    onPress(operation) // Invoke the onPress function with the operation.
    ElevatedButton(modifier = Modifier.padding(4.dp), onClick = {}) {
        Text(text = operation)
    }
}
@Composable
fun CalcEqualsButton(onPress: () -> Unit) {
    ElevatedButton(
        modifier = Modifier.padding(4.dp),
        onClick = { onPress() }
    ) {
        Text(text = "=")
    }
}


