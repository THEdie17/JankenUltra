package com.example.jankenultra

import android.content.Intent
import android.graphics.Typeface
import android.media.SoundPool
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
    //Efectos de sonido
    private lateinit var soundPool: SoundPool
    private var soundId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_level)
        val tf = Typeface.createFromAsset(assets, "fonts/edosz.ttf")

        //Efectos de sonido
        soundPool = SoundPool.Builder().setMaxStreams(1).build()
        soundId = soundPool.load(this, R.raw.menu, 1)

        levels = findViewById(R.id.textView4)
        level1 = findViewById(R.id.level1)
        level1.setOnClickListener {
            playSound()
            val intent = Intent(this, GameLevel1::class.java)
            startActivity(intent)
        }

        level2 = findViewById(R.id.level2)
        level2.setOnClickListener {
            playSound()
            val intent = Intent(this, GameLevel2::class.java)
            startActivity(intent)
        }

        level3 = findViewById(R.id.level3)
        level3.setOnClickListener {
            playSound()
            val intent = Intent(this, ChooseLevel::class.java)
            startActivity(intent)
        }

        back = findViewById(R.id.back)
        back.setOnClickListener {
            playSound()
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
        levels.typeface = tf
        back.typeface = tf
        level3.typeface = tf
        level2.typeface = tf
        level1.typeface = tf
    }

    private fun playSound() {
        soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
    }
}