package com.example.jankenultra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jankenultra.adapter.JugadorAdapters
import com.google.firebase.database.*

private lateinit var back: Button
class ScoreList : AppCompatActivity() {
    val jugadors = mutableListOf<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_list)
        consulta()

        back = findViewById(R.id.button2)
        back.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
    }

    private fun initReciclerView(){
        val reciclerView = findViewById<RecyclerView>(R.id.ReciclerOne)
        reciclerView.layoutManager = LinearLayoutManager(this)
        reciclerView.adapter = JugadorAdapters(jugadors)
    }

    private fun consulta(){
        val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://junkerultra-default-rtdb.europe-west1.firebasedatabase.app/")
        val bdreference: DatabaseReference = database.getReference("DATA_BASE_JUGADORS")
        bdreference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dr in snapshot.children){
                    var nomjugador = dr.child("Nom").value.toString()
                    var scorejugador= dr.child("Puntuacio").value.toString()
                    var j1:Player = Player(nomjugador,scorejugador)
                    jugadors.add(j1)
                }
                initReciclerView()

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error","Base de datos cancelada")
            }
        })
    }
}