package com.example.examen.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examen.viewmodel.JugadoresViewModel

@Composable
fun NuevoJugadorScreen(
    onNavigateBack: () -> Unit,
    viewModel: JugadoresViewModel = viewModel()
) {
    var nombre by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var posicion by remember { mutableStateOf("") }
    var nacionalidad by remember { mutableStateOf("") }
    var urlImagen by remember { mutableStateOf("") }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Nuevo jugador",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            )

            OutlinedTextField(
                value = numero,
                onValueChange = { numero = it },
                label = { Text("Número") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            )

            OutlinedTextField(
                value = posicion,
                onValueChange = { posicion = it },
                label = { Text("Posición") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            )

            OutlinedTextField(
                value = nacionalidad,
                onValueChange = { nacionalidad = it },
                label = { Text("Nacionalidad") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            )

            OutlinedTextField(
                value = urlImagen,
                onValueChange = { urlImagen = it },
                label = { Text("URL Imagen") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        if (nombre.isNotBlank() && numero.isNotBlank()) {
                            viewModel.addJugador(
                                nombre = nombre,
                                numero = numero.toIntOrNull() ?: 0,
                                posicion = posicion,
                                nacionalidad = nacionalidad,
                                imagen = urlImagen
                            )
                            onNavigateBack()
                        }
                    },
                    modifier = Modifier.weight(1f).height(50.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF27D21F))
                ) {
                    Text("Agregar", color = Color.White)
                }

                Button(
                    onClick = onNavigateBack,
                    modifier = Modifier.weight(1f).height(50.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) {
                    Text("Cancelar", color = Color.White)
                }
            }
        }
    }
}

