package com.example.jankenultra

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GameLevel1 : AppCompatActivity() {

    private lateinit var rock : ImageButton
    private lateinit var paper : ImageButton
    private lateinit var scissors : ImageButton
    private lateinit var exit : Button
    val arr = Array(3) { "Rock";"Paper";"Scissors" }
    var userChoice = -1
    var userWins = 0
    var machineChoice = -1
    var MachineWins = 0
    var hasFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamelevel1)



        exit = findViewById<Button>(R.id.button)
        exit.setOnClickListener{
            val intent= Intent(this, ChooseLevel::class.java)
            startActivity(intent)
        }

        rock = findViewById<ImageButton>(R.id.buttonRock)
        rock.setOnClickListener{
            userChoice = 0
            Toast.makeText(this," -"+userChoice, Toast.LENGTH_SHORT).show()
            comprobarButton()
        }

        paper = findViewById<ImageButton>(R.id.buttonPaper)
        paper.setOnClickListener{
            userChoice = 1
            Toast.makeText(this," -"+userChoice, Toast.LENGTH_SHORT).show()
            comprobarButton()
        }

        scissors = findViewById<ImageButton>(R.id.buttonScissors)
        scissors.setOnClickListener{
            userChoice = 2
            Toast.makeText(this," -"+userChoice, Toast.LENGTH_SHORT).show()
            comprobarButton()
        }
    }

    private fun comprobarButton() {
        machineChoice = Random.nextInt(0, 3)

        "Rock0 paper1 scissor2"
        if (userChoice == 0 && machineChoice == 2){
            userWins += 1
            Toast.makeText(this,"Round Win", Toast.LENGTH_SHORT).show()
        }else if (userChoice == 0 && machineChoice == 1){
            MachineWins += 1
            Toast.makeText(this,"Round Lose", Toast.LENGTH_SHORT).show()
        }else if (userChoice == 1 && machineChoice == 0){
            userWins += 1
            Toast.makeText(this,"Round Win", Toast.LENGTH_SHORT).show()
        }else if (userChoice == 1 && machineChoice == 2){
            MachineWins += 1
            Toast.makeText(this,"Round Lose", Toast.LENGTH_SHORT).show()
        }else if (userChoice == 2 && machineChoice == 1){
            userWins += 1
            Toast.makeText(this,"Round Win", Toast.LENGTH_SHORT).show()
        }else if (userChoice == 2 && machineChoice == 0){
            MachineWins += 1
            Toast.makeText(this,"Round Lose", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"machine: "+machineChoice, Toast.LENGTH_SHORT).show()
        }
        userChoice = -1

        if (userWins == 3){
            Toast.makeText(this,"VICTORY", Toast.LENGTH_SHORT).show()
            hasFinished = true
            val intent= Intent(this, Menu::class.java)
            startActivity(intent)
        }else if (MachineWins == 3){
            Toast.makeText(this,"LOSE", Toast.LENGTH_SHORT).show()
            hasFinished = true
            val intent= Intent(this, Menu::class.java)
            startActivity(intent)
        }

        Toast.makeText(this," wins "+userWins, Toast.LENGTH_SHORT).show()
    }

}