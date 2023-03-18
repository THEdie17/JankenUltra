package com.example.jankenultra

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlin.random.Random

class GameLevel1 : AppCompatActivity() {

    private lateinit var rock : Button
    private lateinit var paper : Button
    private lateinit var scissors : Button
    private lateinit var exit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamelevel1)

        val arr = Array(3) { "Rock";"Paper";"Scissors" }
        var userChoice = -1
        var userWins = 0
        var machineChoice = -1
        var MachineWins = 0
        var victory = false



        exit = findViewById<Button>(R.id.button)
        exit.setOnClickListener{
            val intent= Intent(this, choseLevel::class.java)
            startActivity(intent)
        }

        rock = findViewById<Button>(R.id.buttonRock)
        rock.setOnClickListener{
            userChoice = 0
        }

        paper = findViewById<Button>(R.id.buttonPaper)
        paper.setOnClickListener{
            userChoice = 1
        }

        scissors = findViewById<Button>(R.id.buttonScissors)
        scissors.setOnClickListener{
            userChoice = 2
        }
        if (userChoice > -1){
            machineChoice = Random.nextInt(0, 3)

            "Rock0 paper1 scissor2"
            if (userChoice == 0 && machineChoice == 2){
                userWins += 1
                Toast.makeText(this,"Round Win", Toast.LENGTH_SHORT).show()
            }else if (userChoice == 0 && machineChoice == 1){
                MachineWins += 1
                Toast.makeText(this,"Round Lose", Toast.LENGTH_SHORT).show()
            }

            if (userChoice == 1 && machineChoice == 0){
                userWins += 1
                Toast.makeText(this,"Round Win", Toast.LENGTH_SHORT).show()
            }else if (userChoice == 1 && machineChoice == 2){
                MachineWins += 1
                Toast.makeText(this,"Round Lose", Toast.LENGTH_SHORT).show()
            }
            if (userChoice == 1 && machineChoice == 0){
                userWins += 1
                Toast.makeText(this,"Round Win", Toast.LENGTH_SHORT).show()
            }else if (userChoice == 1 && machineChoice == 2){
                MachineWins += 1
                Toast.makeText(this,"Round Lose", Toast.LENGTH_SHORT).show()
            }
        }

    }
}