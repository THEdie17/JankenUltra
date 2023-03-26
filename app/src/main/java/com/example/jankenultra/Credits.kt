package com.example.jankenultra

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class Credits : AppCompatActivity() {

    lateinit var button: Button
    private val handler = Handler(Looper.getMainLooper())
    private var numeroFragment = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
        val tf = Typeface.createFromAsset(assets, "fonts/edosz.ttf")

        button = findViewById(R.id.buttonreturn)
        button.typeface = tf
        button.setOnClickListener {
            val intent = android.content.Intent(this, Menu::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager.commit {
            replace<CreditFragment1Logo>(R.id.frame)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        handler.postDelayed(timeTask, 3000L)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(timeTask)
    }

    private val timeTask = object : Runnable {
        override fun run() {
            numeroFragment++
            if (numeroFragment > 2) {
                numeroFragment = 1
            }

            if (numeroFragment == 1) {
                supportFragmentManager.commit {
                    replace<CreditFragment1Logo>(R.id.frame)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
            } else {
                supportFragmentManager.commit {
                    replace<CreditFragment2Author>(R.id.frame)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
            }

            handler.postDelayed(this, 3000L)
        }
    }
}
