package com.example.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {
    var displayText by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf("") }
    var value1 by remember { mutableStateOf("") }
    var value2 by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Display(text = displayText, modifier = Modifier.weight(1f))
        CalculatorButtons(
            modifier = Modifier.weight(3f),
            onButtonClick = { buttonText ->
                when (buttonText) {
                    in "0".."9" -> {
                        if (operator.isEmpty()) {
                            value1 += buttonText
                            displayText = value1
                        } else {
                            value2 += buttonText
                            displayText = value1 + operator + value2
                        }
                    }
                    "+", "-", "*", "/" -> {
                        if (value1.isNotEmpty()) {
                            operator = buttonText
                            displayText = value1 + operator
                        }
                    }
                    "=" -> {
                        if (value1.isNotEmpty() && value2.isNotEmpty()) {
                            val result = calculateResult(value1.toDouble(), value2.toDouble(), operator)
                            displayText = result.toString()
                            value1 = result.toString()
                            value2 = ""
                            operator = ""
                        }
                    }
                    "C" -> {
                        value1 = ""
                        value2 = ""
                        operator = ""
                        displayText = ""
                    }
                }
            }
        )
    }
}

@Composable
fun Display(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun CalculatorButtons(modifier: Modifier = Modifier, onButtonClick: (String) -> Unit) {
    Column(modifier = modifier.fillMaxWidth()) {
        val buttonModifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .padding(4.dp)
        Row(modifier = Modifier.weight(1f)) {
            CalculatorButton("7", buttonModifier, onButtonClick)
            CalculatorButton("8", buttonModifier, onButtonClick)
            CalculatorButton("9", buttonModifier, onButtonClick)
            CalculatorButton("/", buttonModifier, onButtonClick)
        }
        Row(modifier = Modifier.weight(1f)) {
            CalculatorButton("4", buttonModifier, onButtonClick)
            CalculatorButton("5", buttonModifier, onButtonClick)
            CalculatorButton("6", buttonModifier, onButtonClick)
            CalculatorButton("*", buttonModifier, onButtonClick)
        }
        Row(modifier = Modifier.weight(1f)) {
            CalculatorButton("1", buttonModifier, onButtonClick)
            CalculatorButton("2", buttonModifier, onButtonClick)
            CalculatorButton("3", buttonModifier, onButtonClick)
            CalculatorButton("-", buttonModifier, onButtonClick)
        }
        Row(modifier = Modifier.weight(1f)) {
            CalculatorButton("0", buttonModifier, onButtonClick)
            CalculatorButton("C", buttonModifier, onButtonClick)
            CalculatorButton("=", buttonModifier, onButtonClick)
            CalculatorButton("+", buttonModifier, onButtonClick)
        }
    }
}

@Composable
fun CalculatorButton(text: String, modifier: Modifier = Modifier, onButtonClick: (String) -> Unit) {
    Button(
        onClick = { onButtonClick(text) },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = 25.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorAppPreview() {
    CalculadoraTheme {
        CalculatorApp()
    }
}

fun calculateResult(value1: Double, value2: Double, operator: String): Double {
    return when (operator) {
        "+" -> value1 + value2
        "-" -> value1 - value2
        "*" -> value1 * value2
        "/" -> value1 / value2
        else -> 0.0
    }
}
