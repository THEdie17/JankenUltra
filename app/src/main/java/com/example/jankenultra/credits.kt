package com.example.jankenultra

import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlin.Long

class Credits: AppCompatActivity(){

    lateinit var button: Button

    private lateinit var fragment1: Fragment
    private lateinit var fragment2:Fragment
    private var canGoBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
        val tf = Typeface.createFromAsset(assets,"fonts/edosz.ttf")

        fragment1 = CreditFragment1Logo()
        fragment2 = CreditFragment2Author()

        button = findViewById(R.id.buttonreturn)
        button.typeface = tf
        button.setOnClickListener {
            if (canGoBack){
                val intent = android.content.Intent(this, Menu::class.java)
                startActivity(intent)

            }
        }

        supportFragmentManager.beginTransaction().add(R.id.frame, fragment1).commit()
        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                supportFragmentManager.beginTransaction().add(R.id.frame, fragment2).commit()
                canGoBack = true
            }
        }.start()
    }
}
