package com.example.examen.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.examen.ui.screens.LoginScreen
import com.example.examen.ui.screens.PantHome
import com.example.examen.ui.screens.NuevoJugadorScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun GestionNavegacion(auth: FirebaseAuth) {
    val pilaNavegacion = rememberNavBackStack(Routes.Login)

    NavDisplay(
        backStack = pilaNavegacion,
        onBack = { pilaNavegacion.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Routes.Login -> NavEntry(key) {
                    LoginScreen(
                        auth = auth,
                        onLoginSuccess = {
                            pilaNavegacion.add(Routes.Home)
                        }
                    )
                }
                is Routes.Home -> NavEntry(key) {
                    PantHome(
                        onNavigateToAdd = {
                            pilaNavegacion.add(Routes.NuevoJugador)
                        },
                        onLogout = {
                            auth.signOut()
                            // Limpiamos la pila y volvemos al login siguiendo tu estructura
                            while (pilaNavegacion.size > 1) {
                                pilaNavegacion.removeLastOrNull()
                            }
                            pilaNavegacion.add(Routes.Login)
                            pilaNavegacion.removeAt(0)
                        }
                    )
                }
                is Routes.NuevoJugador -> NavEntry(key) {
                    NuevoJugadorScreen(
                        onNavigateBack = {
                            pilaNavegacion.removeLastOrNull()
                        }
                    )
                }
                else -> NavEntry(Routes.Error) {
                    Text("Error: Ruta no encontrada")
                }
            }
        }
    )
}
