package com.example.jankenultra

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlin.random.Random

@Suppress("DEPRECATION")
class GameLevel2 : AppCompatActivity() {

    private lateinit var rock : ImageButton
    private lateinit var paper : ImageButton
    private lateinit var scissors : ImageButton
    private lateinit var exit : Button
    private lateinit var userChoiceImg : ImageView
    private lateinit var machineChoiceImg : ImageView
    private lateinit var scoreLevel1 : TextView
    private lateinit var textScore : TextView
    private lateinit var auth: FirebaseAuth
    private var user: FirebaseUser? = null
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

        // Check if the game is over
        if (userWins == 5) {
            continueGame()
            hasFinished = true
            AlertDialog.Builder(this)
                .setTitle(R.string.congratulations)
                .setMessage(R.string.userWon)
                .setPositiveButton(R.string.continueBtn) { _, _ ->}
                .setNegativeButton(R.string.exitBtn) { _, _ ->
                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)
                }
                .setCancelable(false)
                .show()
        } else if (machineWins == 5) {
            resetGame()
            hasFinished = true


            AlertDialog.Builder(this)
                .setTitle(R.string.youlost)
                .setMessage(R.string.loserMessage)
                .setPositiveButton(R.string.continueBtn) { _, _ ->}
                .setNegativeButton(R.string.exitBtn) { _, _ ->
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
        scoreLevel += 500
        scoreLevel1.text = scoreLevel.toString()

        val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://junkerultra-default-rtdb.europe-west1.firebasedatabase.app/")
        auth= FirebaseAuth.getInstance()
        user = auth.currentUser
        val myRef = user?.uid?.let { database.reference.child("DATA_BASE_JUGADORS").child(it) }
        myRef?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val scoreDB = dataSnapshot.child("Puntuacio").getValue(String::class.java)

                // Update the score in the database
                val newScore = (scoreDB?.toInt() ?: 0) + 500
                myRef.child("Puntuacio").setValue(newScore.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Firebase", "Error al leer los valores.", error.toException())
            }
        })
    }

}