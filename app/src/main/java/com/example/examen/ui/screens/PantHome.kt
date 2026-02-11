package com.example.examen.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.examen.model.Jugador
import com.example.examen.viewmodel.JugadoresViewModel

@Composable
fun PantHome(
    onNavigateToAdd: () -> Unit,
    onLogout: () -> Unit,
    viewModel: JugadoresViewModel = viewModel()
) {
    val jugadores by viewModel.jugadores.collectAsState()

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .statusBarsPadding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Plantilla temporada 25/26",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onLogout) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Cerrar sesión"
                    )
                }
            }
        },
        bottomBar = {
            Button(
                onClick = onNavigateToAdd,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF27D21F)),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Agregar Jugador", color = Color.White, fontSize = 16.sp)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp) // Evita que el último elemento quede oculto
        ) {
            items(jugadores, key = { it.id ?: "" }) { jugador ->
                JugadorCard(
                    jugador = jugador,
                    onDelete = { jugador.id?.let { viewModel.eliminarJugador(it) } }
                )
            }
        }
    }
}

@Composable
fun JugadorCard(jugador: Jugador, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2FCEE)),
        shape = MaterialTheme.shapes.large
    ) {
        Column {
            AsyncImage(
                model = jugador.imagen,
                contentDescription = "Foto de ${jugador.nombre}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = jugador.numero.toString(),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color =(Color.White) ,
                    modifier = Modifier.padding(end = 16.dp)
                        .background(Color(0xFF27D21F), shape = CircleShape)
                        .padding(16.dp)

                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = jugador.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(text = "${jugador.nacionalidad}")
                    Text(text = "${jugador.posicion}", fontSize = 14.sp, color = Color.Gray)
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar jugador",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}
