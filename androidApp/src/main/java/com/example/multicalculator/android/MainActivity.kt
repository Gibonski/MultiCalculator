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
    // Create the state variables using rememberSaveable
    var leftNumber by rememberSaveable { mutableStateOf(0) }
    var rightNumber by rememberSaveable { mutableStateOf(0) }
    var operation by rememberSaveable { mutableStateOf("") }
    var complete by rememberSaveable { mutableStateOf(false) }
    // Check if complete is true and operation is not an empty string
    if (complete && operation.isNotEmpty()) {

        // Create a mutable variable named answer and assign a value of 0.
        var answer = 0

        // Create a when statement and use the operation variable.
        when (operation) {
            "+" -> {
                // Inside the when statement, use the strings "+', "-", "*", and "/" to assign the outcome of the operation to the answer variable.
                answer = leftNumber + rightNumber
            }
            "-" -> {
                answer = leftNumber - rightNumber
            }
            "*" -> {
                answer = leftNumber * rightNumber
            }
            "/" -> {
                if (rightNumber != 0) {
                    answer = leftNumber / rightNumber
                } else {
                    // Handle division by zero error if needed
                }
            }
        }
        // Perform some action when the condition is met
        // For example, you can clear the complete and operation variables
        // and perform some calculation or update the display text.
        complete = false
        operation = ""
        // Perform any other actions you need when this condition is met.
    }
    // Add an else if statement for the condition where operation is not empty and complete is false.
    else if (operation.isNotEmpty() && !complete) {
        // Set the displayText variable to the rightNumber value.
        displayText.value = rightNumber.toString()
    }
    else {
        // Create an else part and assign the displayText variable to the leftNumber value.
        displayText.value = leftNumber.toString()
    }
    fun equalsPress() {
        // In the equalsPress function, set the complete variable to true.
        complete = true
    }

    fun operationPress(op: String) {
        // In the operationPress function, create a parameter named op of type string.
        // Set the operator variable to the op parameter if the complete variable is false.
        if (!complete) {
            operation = op
        }
    }
    fun numberPress(btnNum: Int) {
        // In the numberPress function, add a parameter named btnNum and make it an integer.

        // Declare an if statement that will execute if the complete variable is true.
        if (complete) {
            // Inside this if statement, set all of the variables to their defaults: leftNumber, rightNumber, operation, and complete.
            leftNumber = 0
            rightNumber = 0
            operation = ""
            complete = false
        }

        // Right under the if statement, do the following:
        if (operation.isNotBlank() && !complete) {
            // 1. If the operation variable is not blank and the complete variable is not true, multiply the rightNumber by 10 and add the btnNum variable. Store the result into the rightNumber variable.
            rightNumber = (rightNumber * 10) + btnNum
        } else {
            // 2. If the operation variable is blank and the complete variable is not true, multiply the leftNumber by 10 and add the btnNum variable. Store the result into the leftNumber variable.
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


