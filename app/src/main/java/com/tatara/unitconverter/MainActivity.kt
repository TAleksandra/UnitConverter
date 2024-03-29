package com.tatara.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tatara.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val iConversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result =
            ((inputValueDouble * iConversionFactor.value * 100 / oConversionFactor.value) / 100)
        outputValue = result.toString()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnits()
        }, label = {
            Text(text = "Enter value")
        })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { iExpanded = !iExpanded }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop Down Menu")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = !iExpanded }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            iExpanded = !iExpanded
                            inputUnit = "Centimeters"
                            iConversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        iExpanded = !iExpanded
                        inputUnit = "Meters"
                        iConversionFactor.value = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        iExpanded = !iExpanded
                        inputUnit = "Feet"
                        iConversionFactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }, onClick = {
                        iExpanded = !iExpanded
                        inputUnit = "Milimeters"
                        iConversionFactor.value = 0.001
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded = !oExpanded }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop Down Menu")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = !oExpanded }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            oExpanded = !oExpanded
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        oExpanded = !oExpanded
                        outputUnit = "Meters"
                        oConversionFactor.value = 1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpanded = !oExpanded
                        outputUnit = "Feet"
                        oConversionFactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }, onClick = {
                        oExpanded = !oExpanded
                        outputUnit = "Milimeters"
                        oConversionFactor.value = 0.001
                        convertUnits()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}