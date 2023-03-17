package com.example.jankenultra

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class GameLevel1 : AppCompatActivity() {

    private lateinit var rock : Button
    private lateinit var paper : Button
    private lateinit var scissors : Button
    private lateinit var exit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamelevel1)

        exit = findViewById<Button>(R.id.button)
        exit.setOnClickListener{
            val intent= Intent(this, choseLevel::class.java)
            startActivity(intent)
        }
    }
}