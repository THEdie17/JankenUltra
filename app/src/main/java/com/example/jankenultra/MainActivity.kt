package com.example.jankenultra

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var user: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //assigna valor a user
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser
        val loginBtn = findViewById<Button>(R.id.LOGINBTN)
        val registerBtn = findViewById<Button>(R.id.REGISTERBTN)

        val tf = Typeface.createFromAsset(assets,"fonts/janken.ttf")
        loginBtn.typeface = tf
        registerBtn.typeface = tf

        loginBtn.setOnClickListener {
            Toast.makeText(this, "Click Login Button", Toast.LENGTH_LONG).show()
            jumpLogin()
        }
        registerBtn.setOnClickListener {
            Toast.makeText(this, "Click Register Button", Toast.LENGTH_LONG).show()
            jumpRegister()
        }
    }

    private fun jumpRegister() {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }

    private fun jumpLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        usuariLogejat()
        super.onStart()
    }

    private fun usuariLogejat() {
        if (user != null) {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
            finish()
        }


    }
}
