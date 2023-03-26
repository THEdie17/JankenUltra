package com.example.jankenultra

import android.content.Intent
import android.graphics.Typeface
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class Menu : AppCompatActivity() {
    //creem unes variables per comprovar ususari i authentificació
    private lateinit var closeSession: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var creditsBtn: Button
    private lateinit var scoresBtn: Button
    private lateinit var playBtn: Button
    private lateinit var myScore: TextView
    private lateinit var score: TextView
    private lateinit var uid: TextView
    private lateinit var emailPlayer: TextView
    private lateinit var usernamePlayer: TextView

    //Efectos de sonido
    private lateinit var soundPool: SoundPool
    private var soundId: Int = 0

    private var user: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val tf = Typeface.createFromAsset(assets, "fonts/edosz.ttf")
        val tf2 = Typeface.createFromAsset(assets, "fonts/retro.ttf")

        uid = findViewById(R.id.uid)
        emailPlayer = findViewById(R.id.correo)
        usernamePlayer = findViewById(R.id.nom)
        myScore = findViewById(R.id.miPuntuaciotxt)
        score = findViewById(R.id.puntuacio)

        //Efectos de sonido
        soundPool = SoundPool.Builder().setMaxStreams(1).build()
        soundId = soundPool.load(this, R.raw.menu, 1)

        val database: FirebaseDatabase =
            FirebaseDatabase.getInstance("https://junkerultra-default-rtdb.europe-west1.firebasedatabase.app/")
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser
        val myRef = user?.uid?.let { database.reference.child("DATA_BASE_JUGADORS").child(it) }
        myRef?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val email = dataSnapshot.child("Email").getValue(String::class.java)
                val name = dataSnapshot.child("Nom").getValue(String::class.java)
                val uidUser = dataSnapshot.child("Uid").getValue(String::class.java)
                val scoreDB = dataSnapshot.child("Puntuacio").getValue(String::class.java)
                // utilizar email y name según sea necesario
                uid.text = uidUser
                emailPlayer.text = email
                usernamePlayer.text = name
                score.text = scoreDB
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Firebase", "Error al leer los valores.", error.toException())
            }
        })

        creditsBtn = findViewById(R.id.CreditsBtn)
        scoresBtn = findViewById(R.id.PuntuacionsBtn)
        playBtn = findViewById(R.id.jugarBtn)
        closeSession = findViewById(R.id.tancarSessio)


        myScore.typeface = tf
        score.typeface = (tf)
        uid.typeface = (tf2)
        emailPlayer.typeface = (tf2)
        usernamePlayer.typeface = (tf2)

        //fem el mateix amb el text dels botons

        closeSession.typeface = (tf)
        creditsBtn.typeface = (tf)
        scoresBtn.typeface = (tf)
        playBtn.typeface = (tf)

        closeSession.setOnClickListener {
            closeTheSession()
            playSound()
        }

        creditsBtn.setOnClickListener {
            Toast.makeText(this, "com.example.jankenultra.Credits", Toast.LENGTH_SHORT).show()
            playSound()
            val intent = Intent(this, Credits::class.java)
            startActivity(intent)
        }

        playBtn.setOnClickListener {
            Toast.makeText(this, "PLAY", Toast.LENGTH_SHORT).show()
            playSound()
            val intent = Intent(this, ChooseLevel::class.java)
            startActivity(intent)
        }
        scoresBtn.setOnClickListener {
            Toast.makeText(this, "Scores", Toast.LENGTH_SHORT).show()
            playSound()
        }


    }

    private fun playSound() {
        soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
    }

    override fun onStart() {
        loggedUser()
        super.onStart()
    }

    private fun loggedUser() {
        if (user != null) {
            Toast.makeText(
                this, "Player logged in",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun closeTheSession() {
        auth.signOut() //tanca la sessió
        //va a la pantalla inicial
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}
