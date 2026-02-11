package com.example.examen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.examen.model.Jugador
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JugadoresViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val jugadoresCollection = db.collection("Jugadores")

    private val _jugadores = MutableStateFlow<List<Jugador>>(emptyList())
    val jugadores: StateFlow<List<Jugador>> = _jugadores

    init {
        getJugadores()
    }

    private fun getJugadores() {
        jugadoresCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("Firebase", "Error al obtener jugadores: ${error.message}", error)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val lista = snapshot.documents.mapNotNull { doc ->
                    val jugador = doc.toObject(Jugador::class.java)
                    jugador?.id = doc.id
                    jugador
                }
                _jugadores.value = lista
            }
        }
    }

    fun addJugador(nombre: String, numero: Int, posicion: String, nacionalidad: String, imagen: String) {
        val jugador = Jugador(
            nombre = nombre,
            numero = numero,
            posicion = posicion,
            nacionalidad = nacionalidad,
            imagen = imagen
        )
        jugadoresCollection.add(jugador)
            .addOnSuccessListener {
                Log.d("Firebase", "Jugador añadido")
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Error al añadir: ${e.message}")
            }
    }
    
    fun eliminarJugador(id: String) {
        jugadoresCollection.document(id).delete()
            .addOnSuccessListener {
                Log.d("Firebase", "Jugador eliminado")
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Error al eliminar: ${e.message}")
            }
    }
}
