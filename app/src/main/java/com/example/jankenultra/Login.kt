package com.example.jankenultra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.text.SimpleDateFormat
import java.util.*

lateinit var correoLogin : EditText
lateinit var passLogin : EditText
lateinit var login : Button


class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Despleguem les variables que farem servir
        correoLogin = findViewById<EditText>(R.id.emailLogin)
        passLogin = findViewById<EditText>(R.id.passLogin)
        auth = FirebaseAuth.getInstance()
        login = findViewById<Button>(R.id.login)
        login.setOnClickListener(){
            //Abans de fer el registre validem les dades
            var email:String = correoLogin.text.toString()
            var passw:String = passLogin.text.toString()
            // validació del correu
            // si no es de tipus correu
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                correoLogin.error = "Invalid Mail"
            }
            else if (passw.length<6) {
                passLogin.error = "Password less than 6 chars"
            }else{
                // aquí farem LOGIN al jugador
                LogindeJugador(email, passw)
            }
        }
    }

    private fun LogindeJugador(email: String, passw: String) {
        auth.signInWithEmailAndPassword(email,passw)
        Log.d("Buenas Tardes", "Connected")
        finish()
    }
}