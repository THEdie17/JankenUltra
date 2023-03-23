package com.example.jankenultra

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

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


    var user: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val tf = Typeface.createFromAsset(assets,"fonts/edosz.ttf")

        myScore=findViewById(R.id.miPuntuaciotxt)
        score=findViewById(R.id.puntuacio)
        uid=findViewById(R.id.uid)
        emailPlayer=findViewById(R.id.correo)
        usernamePlayer=findViewById(R.id.nom)

        creditsBtn =findViewById<Button>(R.id.CreditsBtn)
        scoresBtn =findViewById<Button>(R.id.PuntuacionsBtn)
        playBtn =findViewById<Button>(R.id.jugarBtn)
        closeSession = findViewById<Button>(R.id.tancarSessio)


        myScore.typeface = tf
        score.typeface =(tf)
        uid.typeface =(tf)
        emailPlayer.typeface =(tf)
        usernamePlayer.typeface =(tf)

        //fem el mateix amb el text dels botons

        closeSession.typeface =(tf)
        creditsBtn.typeface =(tf)
        scoresBtn.typeface =(tf)
        playBtn.typeface =(tf)

        closeSession.setOnClickListener{
            closeTheSession()

        }

        auth= FirebaseAuth.getInstance()
        user =auth.currentUser
        creditsBtn.setOnClickListener{
            Toast.makeText(this,"Credits", Toast.LENGTH_SHORT).show()
            val intent= Intent(this, Credits::class.java)
            startActivity(intent)
        }

        playBtn.setOnClickListener{
            Toast.makeText(this,"PLAY", Toast.LENGTH_SHORT).show()
            val intent= Intent(this, ChooseLevel::class.java)
            startActivity(intent)
        }
        scoresBtn.setOnClickListener{
            Toast.makeText(this,"Scores", Toast.LENGTH_SHORT).show()
        }





    }
    override fun onStart() {
        loggedUser()
        super.onStart()
    }

    private fun loggedUser()
    {
        if (user !=null)
        {
            Toast.makeText(this,"Player logged in",
                Toast.LENGTH_SHORT).show()
        }
        else
        {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun closeTheSession() {
        auth.signOut() //tanca la sessió
        //va a la pantalla inicial
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }





}
