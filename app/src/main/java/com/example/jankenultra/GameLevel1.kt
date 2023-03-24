package com.example.jankenultra

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class GameLevel1 : AppCompatActivity() {

    private lateinit var rock : ImageButton
    private lateinit var paper : ImageButton
    private lateinit var scissors : ImageButton
    private lateinit var exit : Button
    private lateinit var userChoiceImg : ImageView
    private lateinit var machineChoiceImg : ImageView
    private lateinit var scoreLevel1 : TextView
    private lateinit var textScore : TextView
    private val arr = arrayOf("Rock", "Paper", "Scissors")
    private var userChoice = -1
    private var userWins = 0
    private var machineChoice = -1
    private var machineWins = 0
    private var hasFinished = false
    private var scoreLevel = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamelevel1)
        val tf = Typeface.createFromAsset(assets, "fonts/edosz.ttf")
        scoreLevel1 = findViewById(R.id.scoreLevel1)
        textScore = findViewById(R.id.textScore)
        userChoiceImg = findViewById(R.id.UserChoice)
        machineChoiceImg = findViewById(R.id.MachineChoice)
        exit = findViewById(R.id.button)
        scoreLevel1.typeface = tf
        textScore.typeface = tf
        scoreLevel1.text = "0"
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
            continueGame()
            hasFinished = true
            AlertDialog.Builder(this)
                .setTitle("Congratulations!")
                .setMessage("You won the game!")
                .setPositiveButton("Continue") { _, _ ->}
                .setNegativeButton("Exit") { _, _ ->
                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)
                }
                .setCancelable(false)
                .show()
        } else if (machineWins == 3) {
            resetGame()
            hasFinished = true
            AlertDialog.Builder(this)
                .setTitle("Too bad loser!")
                .setMessage("The machine won the game!")
                .setPositiveButton("Continue") { _, _ ->}
                .setNegativeButton("Exit") { _, _ ->
                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)
                }
                .setCancelable(false)
                .show()
        }

        // Reset userChoice
        userChoice = -1
    }

    @SuppressLint("DiscouragedApi")
    private fun getDrawableResourceId(name: String): Int {
        return resources.getIdentifier(name.lowercase(), "drawable", packageName)
    }

    private fun resetGame() {
        userWins = 0
        machineWins = 0
        hasFinished = false
    }

    private fun continueGame() {
        userWins = 0
        machineWins = 0
        hasFinished = false
        updateScore()
    }

    private fun updateScore() {
        scoreLevel += 100
        scoreLevel1.text = scoreLevel.toString()

        var database: FirebaseDatabase =FirebaseDatabase.getInstance("https://junkerultra-default-rtdb.europe-west1.firebasedatabase.app/")
        var reference: DatabaseReference = database.getReference("DATA_BASE_JUGADORS")
        var email = Login.user_email

        val query = reference.orderByChild("Email").equalTo(email)

        query.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                reference.child("Puntuacio").value.toString()
                reference.child("Puntuacio").setValue(scoreLevel1.text)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        val nuevosDatos = mapOf(
            "Puntuacio" to scoreLevel.toString()
        )
    }
}