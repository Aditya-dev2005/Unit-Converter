package eu.tutorials.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Unitconverter(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Unitconverter(modifier: Modifier = Modifier) {
    var inputvalue by remember { mutableStateOf("") }
    var outputvalue by remember { mutableStateOf("") }
    var inputunit by remember { mutableStateOf("Meter") }
    var outputunit by remember { mutableStateOf("Meter") }
    var iexpanded by remember { mutableStateOf(false) }
    var oexpanded by remember { mutableStateOf(false) }
    val conversionfactor = remember { mutableStateOf(1.00) }
    val oconversionfactor = remember { mutableStateOf(1.00) }

    fun convertunits() {
        val inputvaluetodouble = inputvalue.toDoubleOrNull() ?: 0.0
        val result = (inputvaluetodouble * conversionfactor.value * 100.00 / oconversionfactor.value).roundToInt() / 100.0
        outputvalue = result.toString()
    }

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFF6A11CB), Color(0xFF2575FC))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Unit Converter",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 32.sp
            )
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = inputvalue,
            onValueChange = {
                inputvalue = it
                convertunits()
            },
            label = { Text("Enter Value", color = Color.White) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.LightGray,
                cursorColor = Color.White,
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(Modifier.height(16.dp))
        Row {
            // Input Unit Dropdown
            Box {
                Button(
                    onClick = { iexpanded = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2575FC),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(inputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "ArrowDown")
                }
                DropdownMenu(expanded = iexpanded, onDismissRequest = { iexpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeter") },
                        onClick = {
                            iexpanded = false
                            inputunit = "Centimeter"
                            conversionfactor.value = 0.01
                            convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = {
                            iexpanded = false
                            inputunit = "Meter"
                            conversionfactor.value = 1.0
                            convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iexpanded = false
                            inputunit = "Feet"
                            conversionfactor.value = 0.3048
                            convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeter") },
                        onClick = {
                            iexpanded = false
                            inputunit = "Millimeter"
                            conversionfactor.value = 0.001
                            convertunits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Output Unit Dropdown
            Box {
                Button(
                    onClick = { oexpanded = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2575FC),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(outputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "ArrowDown")
                }
                DropdownMenu(expanded = oexpanded, onDismissRequest = { oexpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeter") },
                        onClick = {
                            oexpanded = false
                            outputunit = "Centimeter"
                            oconversionfactor.value = 0.01
                            convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = {
                            oexpanded = false
                            outputunit = "Meter"
                            oconversionfactor.value = 1.0
                            convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            oexpanded = false
                            outputunit = "Feet"
                            oconversionfactor.value = 0.3048
                            convertunits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeter") },
                        onClick = {
                            oexpanded = false
                            outputunit = "Millimeter"
                            oconversionfactor.value = 0.001
                            convertunits()
                        }
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        // Result text
        Text(
            "Result : $outputvalue $outputunit",
            style = MaterialTheme.typography.headlineMedium.copy(
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme {
        Unitconverter()
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    Unitconverter()
}
