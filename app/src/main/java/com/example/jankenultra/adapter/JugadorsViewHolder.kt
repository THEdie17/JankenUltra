package com.example.jankenultra.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jankenultra.R
import com.example.jankenultra.Player

class JugadorsViewHolder(view: View): RecyclerView.ViewHolder(view){
    val nomJugador= view.findViewById<TextView>(R.id.Nom_Jugador)
    val puntuacioJugador = view.findViewById<TextView>(R.id.Puntuacio_Jugador)

    fun render(JugadorModel: Player){
        nomJugador.text = JugadorModel.nom_jugador
        puntuacioJugador.text = JugadorModel.score_jugador
    }
}