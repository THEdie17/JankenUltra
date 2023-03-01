package com.example.jankenultra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.text.SimpleDateFormat
import java.util.*

lateinit var emailEt: EditText
lateinit var passEt: EditText
lateinit var nameEt: EditText
lateinit var dateTxt: TextView
lateinit var register: Button
lateinit var auth: FirebaseAuth


class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        emailEt = findViewById<EditText>(R.id.emailEt)
        passEt = findViewById<EditText>(R.id.passEt)
        nameEt = findViewById<EditText>(R.id.nameEt)
        dateTxt = findViewById<TextView>(R.id.dateTxt)
        register = findViewById<Button>(R.id.register)
        auth = FirebaseAuth.getInstance()

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateInstance() //or use getDateInstance()
        val formattedDate = formatter.format(date)
        //ara la mostrem al TextView
        dateTxt.text = formattedDate

        register.setOnClickListener {
            //Abans de fer el registre validem les dades
            val email: String = emailEt.text.toString()
            val pass: String = passEt.text.toString()
            // validació del correu
            // si no es de tipus correu
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEt.error = "Invalid Mail"
            } else if (pass.length < 6) {
                passEt.error = "Password less than 6 chars"
            } else {
                registerPlayer(email, pass)
            }

        }
    }


    private fun registerPlayer(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(
                        this, "createUserWithEmail:success", Toast.LENGTH_SHORT
                    ).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(
                        baseContext, "Authentication failed .", Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        //hi ha un interrogant perquè podria ser null
        if (user != null) {
            var puntuacio: Int = 0
            var uidString: String = user.uid
            var correoString: String = emailEt.text.toString()
            var passString: String = passEt.text.toString()
            var nombreString: String = nameEt.text.toString()
            var fechaString: String = dateTxt.text.toString()
            //AQUI GUARDA EL CONTINGUT A LA BASE DE DADES
// FALTA FER
        } else {
            Toast.makeText(
                this, "ERROR CREATE USER ",Toast.LENGTH_SHORT).show()
        }
    }


}

