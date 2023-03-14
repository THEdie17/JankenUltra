package com.example.jankenultra

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_credits)

        fragment1 = CreditFragment1_Logo()
        fragment2 = creditFragment2_author()

        supportFragmentManager.beginTransaction().add(R.id.frame,fragment1).commit()


        button = findViewById<Button>(id.buttonreturn)
        button.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

    }
}