package com.example.jankenultra

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.jankenultra.R.*
import java.lang.String
import java.util.*
import kotlin.Long

class Credits: AppCompatActivity(){

    lateinit var button: Button

    private lateinit var fragment1: Fragment
    private lateinit var fragment2:Fragment
    private var canGoBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_credits)


        fragment1 = CreditFragment1_Logo()
        fragment2 = CreditFragment2_Author()


        button = findViewById<Button>(id.buttonreturn)
        button.setOnClickListener {
            if (canGoBack == true){
                val intent = android.content.Intent(this, com.example.jankenultra.Menu::class.java)
                startActivity(intent)

            }
        }

        supportFragmentManager.beginTransaction().add(id.frame, fragment1).commit()
        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                supportFragmentManager.beginTransaction().add(id.frame, fragment2).commit()
                canGoBack = true
            }
        }.start()
    }
}
