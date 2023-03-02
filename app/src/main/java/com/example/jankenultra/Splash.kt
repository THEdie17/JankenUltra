package com.example.jankenultra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Timer
import kotlin.concurrent.schedule

class Splash : AppCompatActivity() {
    private val duration: Long=10000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
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
    private fun jumpStart()
    {
        val intent=Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}


