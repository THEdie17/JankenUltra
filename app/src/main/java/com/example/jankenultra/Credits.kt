package com.example.jankenultra

import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import java.util.*
import kotlin.Long

class Credits: AppCompatActivity(){

    lateinit var button: Button
    private var timer= Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
        val tf = Typeface.createFromAsset(assets,"fonts/edosz.ttf")

        button = findViewById(R.id.buttonreturn)
        button.typeface = tf
        button.setOnClickListener {
            val intent = android.content.Intent(this, Menu::class.java)
            startActivity(intent)
        }
        supportFragmentManager.commit {
            replace<CreditFragment1Logo>(R.id.frame)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        timer.scheduleAtFixedRate(TimeTask(),0L,3000L)

    }
    private inner class TimeTask:TimerTask(){
        private var numeroFragment:Int=1;
        override fun run() {
            numeroFragment++
            if (numeroFragment>2){
                numeroFragment=1
            }

            if (numeroFragment==1) {
                supportFragmentManager.commit {
                    replace<CreditFragment1Logo>(R.id.frame)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
            }else{
                supportFragmentManager.commit {
                    replace<CreditFragment2Author>(R.id.frame)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
            }
        }
    }
}
