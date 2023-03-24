package com.example.jankenultra

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GameLevel1 : AppCompatActivity() {

    private lateinit var rock : ImageButton
    private lateinit var paper : ImageButton
    private lateinit var scissors : ImageButton
    private lateinit var exit : Button
    private lateinit var userChoiceImg : ImageView
    private lateinit var machineChoiceImg : ImageView
    private lateinit var scoreLevel : TextView
    private val arr = arrayOf("Rock", "Paper", "Scissors")
    private var userChoice = -1
    private var userWins = 0
    private var machineChoice = -1
    private var machineWins = 0
    private var hasFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamelevel1)
        scoreLevel = findViewById(R.id.scoreLevel1)
        userChoiceImg = findViewById(R.id.UserChoice)
        machineChoiceImg = findViewById(R.id.MachineChoice)
        exit = findViewById(R.id.button)
        exit.setOnClickListener{
            val intent= Intent(this, ChooseLevel::class.java)
            startActivity(intent)
        }

        rock = findViewById(R.id.buttonRock)
        rock.setOnClickListener{
            userChoice = 0
            checkButton()
        }

        paper = findViewById(R.id.buttonPaper)
        paper.setOnClickListener{
            userChoice = 1
            checkButton()
        }

        scissors = findViewById(R.id.buttonScissors)
        scissors.setOnClickListener{
            userChoice = 2
            checkButton()
        }
    }

    private fun checkButton() {
        machineChoice = Random.nextInt(0, 3)

        // Show the user's and machine's choices
        userChoiceImg.setImageResource(getDrawableResourceId(arr[userChoice]))
        machineChoiceImg.setImageResource(getDrawableResourceId(arr[machineChoice]))

        // Check the result of the round
        when (userChoice) {
            machineChoice -> Toast.makeText(this, "Round Draw", Toast.LENGTH_SHORT).show()
            0 -> if (machineChoice == 2) userWins++ else machineWins++
            1 -> if (machineChoice == 0) userWins++ else machineWins++
            2 -> if (machineChoice == 1) userWins++ else machineWins++
        }

        // Show the score
        Toast.makeText(this, " wins $userWins", Toast.LENGTH_SHORT).show()

        // Check if the game is over
        if (userWins == 3) {
            Toast.makeText(this,"VICTORY", Toast.LENGTH_SHORT).show()
            hasFinished = true
            val intent= Intent(this, Menu::class.java)
            startActivity(intent)
        } else if (machineWins == 3) {
            Toast.makeText(this,"LOSE", Toast.LENGTH_SHORT).show()
            hasFinished = true
            val intent= Intent(this, Menu::class.java)
            startActivity(intent)
        }

        // Reset userChoice
        userChoice = -1
    }

    @SuppressLint("DiscouragedApi")
    private fun getDrawableResourceId(name: String): Int {
        return resources.getIdentifier(name.lowercase(), "drawable", packageName)
    }

}
