package com.example.jankenultra

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*



class Register : AppCompatActivity() {

    private lateinit var emailEt: EditText
    private lateinit var passEt: EditText
    private lateinit var nameEt: EditText
    private lateinit var dateTxt: TextView
    private lateinit var register: Button
    private lateinit var auth: FirebaseAuth
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

        val tf = Typeface.createFromAsset(assets,"fonts/edosz.ttf")

        dateTxt.typeface = tf
        register.typeface = tf

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
            var score = 0
            var uidString: String = user.uid
            var emailString: String = emailEt.text.toString()
            var passString: String = passEt.text.toString()
            var usernameString: String = nameEt.text.toString()
            var dateString: String = dateTxt.text.toString()

            var dadesJugador : HashMap<String,String> = HashMap<String, String>()

            dadesJugador.put ("Uid",uidString)
            dadesJugador.put ("Email",emailString)
            dadesJugador.put ("Password",passString)
            dadesJugador.put ("Nom",usernameString)
            dadesJugador.put ("Data",dateString)
            dadesJugador.put ("Puntuacio", score.toString())
            // Creem un punter a la base de dades i li donem un nom
            var database: FirebaseDatabase =FirebaseDatabase.getInstance("https://junkerultra-default-rtdb.europe-west1.firebasedatabase.app/")
            var reference: DatabaseReference = database.getReference("DATA_BASE_JUGADORS")
            if(reference!=null) {
                //crea un fill amb els valors de dadesJugador
                reference.child(uidString).setValue(dadesJugador)
                Toast.makeText(this, "USUARI BEN REGISTRAT",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "ERROR BD", Toast.LENGTH_SHORT).show()
            }
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        // FALTA FER
        } else {
            Toast.makeText(
                this, "ERROR CREATE USER ",Toast.LENGTH_SHORT).show()
        }
    }
}

