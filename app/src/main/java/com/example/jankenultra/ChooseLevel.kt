package com.example.jankenultra

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ChooseLevel : AppCompatActivity() {

    private lateinit var level1: Button
    private lateinit var level2: Button
    private lateinit var level3: Button
    private lateinit var back: Button
    private lateinit var levels: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_level)
        val tf = Typeface.createFromAsset(assets, "fonts/edosz.ttf")
        levels = findViewById(R.id.textView4)
        level1 = findViewById(R.id.level1)
        level1.setOnClickListener {
            val intent = Intent(this, GameLevel1::class.java)
            startActivity(intent)
        }

        level2 = findViewById(R.id.level2)
        level2.setOnClickListener {
            val intent = Intent(this, GameLevel2::class.java)
            startActivity(intent)
        }

        level3 = findViewById(R.id.level3)
        level3.setOnClickListener {
            val intent = Intent(this, ChooseLevel::class.java)
            startActivity(intent)
        }

        back = findViewById(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
        levels.typeface = tf
        back.typeface = tf
        level3.typeface = tf
        level2.typeface = tf
        level1.typeface = tf
    }
}