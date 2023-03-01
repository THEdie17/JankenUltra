package com.example.jankenultra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginBtn = findViewById<Button>(R.id.LOGINBTN)
        val registerBtn = findViewById<Button>(R.id.REGISTERBTN)
        loginBtn.setOnClickListener{
            Toast.makeText(this, "Click Login Button",Toast.LENGTH_LONG).show()
        }
        registerBtn.setOnClickListener{
            Toast.makeText(this, "Click Register Button",Toast.LENGTH_LONG).show()
            jumpRegister()
        }
    }

    private fun jumpRegister(){
        val intent= Intent(this, Register::class.java)
        startActivity(intent)
    }
}
