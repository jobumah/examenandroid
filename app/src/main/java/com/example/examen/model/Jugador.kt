package com.example.examen.model

import com.google.firebase.firestore.Exclude

data class Jugador(
    @get:Exclude var id: String? = null,
    val nombre: String = "",
    val numero: Int = 0,
    val nacionalidad: String = "",
    val posicion: String = "",
    val imagen: String = ""
)
