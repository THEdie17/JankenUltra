package com.example.jankenultra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class choseLevel : AppCompatActivity() {

    lateinit var level1 : Button
    lateinit var level2 : Button
    lateinit var level3 : Button
    lateinit var back : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chose_level)

        level1 = findViewById<Button>(R.id.level1)
        level1.setOnClickListener{
            val intent= Intent(this, GameLevel1::class.java)
            startActivity(intent)
        }

        level2 = findViewById<Button>(R.id.level2)
        level2.setOnClickListener{
            val intent= Intent(this, choseLevel::class.java)
            startActivity(intent)
        }

        level3 = findViewById<Button>(R.id.level3)
        level3.setOnClickListener{
            val intent= Intent(this, choseLevel::class.java)
            startActivity(intent)
        }

        back = findViewById<Button>(R.id.back)
        back.setOnClickListener{
            val intent= Intent(this, Menu::class.java)
            startActivity(intent)
        }
    }
}