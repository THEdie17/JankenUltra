package com.example.jankenultra

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Timer
import kotlin.concurrent.schedule

class Splash : AppCompatActivity() {
    private val duration: Long=10000
    lateinit var mp: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mp = MediaPlayer.create(this,R.raw.splashmusic)
        mp.setOnPreparedListener{ mp.start() }
        //amaguem la barra, pantalla a full
        supportActionBar?.hide()
        //cridem a la funció de canviar activitat
        changeActivity()
    }
    private fun changeActivity(){
        Timer().schedule(duration){
            jumpStart()
        }
    }
    private fun jumpStart() {
        val intent = Intent(this, MainActivity::class.java)
        mp.release()
        startActivity(intent)
    }
}


