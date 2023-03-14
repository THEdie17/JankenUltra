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

class credits: AppCompatActivity(){

    lateinit var button: Button
    lateinit var transaction: FragmentTransaction
    private lateinit var fragment1:Fragment
    private lateinit var fragment2:Fragment
    lateinit var timer:CountDownTimer
    lateinit var timer2:CountDownTimer


    var handler: Handler = Handler()
    var runnable: Runnable? = null
    var delay = 3000
    var fragment_ = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_credits)

        fragment1 = CreditFragment1_Logo()
        fragment2 = creditFragment2_author()

        button = findViewById<Button>(id.buttonreturn)
        button.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
        "supportFragmentManager.beginTransaction().add(R.id.frame, fragment1).commit()"
        """ 
           handler.postDelayed(Runnable {
        handler.postDelayed(runnable!!, delay.toLong())
        if (fragment_ == 1) {
            supportFragmentManager.beginTransaction().add(R.id.frame, fragment1).commit()
            fragment_ = 2
        }else{
            supportFragmentManager.beginTransaction().add(R.id.frame, fragment2).commit()
            fragment_ = 1
        }
       }.also { runnable = it }, delay.toLong())
        """

        timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                supportFragmentManager.beginTransaction().add(R.id.frame, fragment1).commit()
                timer.cancel()
                timer2.start()
            }
        }.start()

        timer2 = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                supportFragmentManager.beginTransaction().add(R.id.frame, fragment2).commit()
                timer2.cancel()
                timer.start()
            }

        }

    }
}