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
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Display(text = "", modifier = Modifier.weight(1f))
        CalculatorButtons(modifier = Modifier.weight(3f))
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
fun CalculatorButtons(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        val buttonModifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .padding(4.dp)

        Row(modifier = Modifier.weight(1f)) {
            CalculatorButton("7", buttonModifier)
            CalculatorButton("8", buttonModifier)
            CalculatorButton("9", buttonModifier)
            CalculatorButton("/", buttonModifier)
        }
        Row(modifier = Modifier.weight(1f)) {
            CalculatorButton("4", buttonModifier)
            CalculatorButton("5", buttonModifier)
            CalculatorButton("6", buttonModifier)
            CalculatorButton("*", buttonModifier)
        }
        Row(modifier = Modifier.weight(1f)) {
            CalculatorButton("1", buttonModifier)
            CalculatorButton("2", buttonModifier)
            CalculatorButton("3", buttonModifier)
            CalculatorButton("-", buttonModifier)
        }
        Row(modifier = Modifier.weight(1f)) {
            CalculatorButton("0", buttonModifier)
            CalculatorButton("C", buttonModifier)
            CalculatorButton("=", buttonModifier)
            CalculatorButton("+", buttonModifier)
        }
    }
}

@Composable
fun CalculatorButton(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { /* No action */ },
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
