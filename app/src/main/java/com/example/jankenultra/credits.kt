package com.example.jankenultra

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.text.TextUtils.replace
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.jankenultra.R.*
import java.util.*

class credits: AppCompatActivity(){

    lateinit var button: Button
    lateinit var transaction: FragmentTransaction
    lateinit var timer:Timer
    lateinit var timer2:CountDownTimer

    private lateinit var fragment1:Fragment
    private lateinit var fragment2:Fragment


    var handler: Handler = Handler()
    var runnable: Runnable? = null
    var delay = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_credits)


        fragment1 = CreditFragment1_Logo()
        fragment2 = creditFragment2_author()

        supportFragmentManager.commit {
            replace<CreditFragment1_Logo>(R.id.frame)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        var timer = Timer()

        button = findViewById<Button>(id.buttonreturn)
        button.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        timer.scheduleAtFixedRate(TimeTask(), 0L, 3000L)

        "supportFragmentManager.beginTransaction().add(id.frame, fragment1).commit()"

    }

    private inner class TimeTask:TimerTask(){

        var fragment_ = 2
        override fun run() {
            if (fragment_ == 1) {
                supportFragmentManager.beginTransaction().add(R.id.frame, fragment1).commit()
                fragment_ = 2
            }else{
                supportFragmentManager.beginTransaction().add(R.id.frame, fragment2).commit()
                fragment_ = 1
            }
        }
    }
}
