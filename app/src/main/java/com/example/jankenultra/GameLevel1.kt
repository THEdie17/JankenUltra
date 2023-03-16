package com.example.jankenultra

import android.R
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class RockPaperScissors : Activity(), View.OnClickListener {
    enum class Option {
        ROCK, PAPER, SCISSORS
    }

    enum class Result {
        WIN, LOSS, DRAW
    }

    private var userSelection: Option? = null
    private var gameResult: Result? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamelevel1)
        val buttonRock = findViewById<View>(R.id.buttonRock) as Button
        val buttonpaper = findViewById<View>(R.id.buttonPaper) as Button
        val buttonScissors = findViewById<View>(R.id.buttonScissors) as Button
        val buttonHome = findViewById<View>(R.id.imageButtonHome) as ImageButton

        // Set click listening event for all buttons.
        buttonRock.setOnClickListener(this)
        buttonpaper.setOnClickListener(this)
        buttonScissors.setOnClickListener(this)
        buttonHome.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_rock_paper_scissors, menu)
        return true
    }

    override fun onClick(v: View) {
        val imageView = findViewById<View>(R.id.imageViewAnswerUser) as ImageView
        var play = true
        when (v.id) {
            R.id.buttonRock -> {
                userSelection = Option.ROCK
                imageView.setImageResource(R.drawable.rock)
            }
            R.id.buttonPaper -> {
                userSelection = Option.PAPER
                imageView.setImageResource(R.drawable.paper)
            }
            R.id.buttonScissors -> {
                userSelection = Option.SCISSORS
                imageView.setImageResource(R.drawable.scissors)
            }
            R.id.imageButtonHome -> {
                startActivity(
                    Intent(
                        this@RockPaperScissors,
                        ChooseActivity::class.java
                    )
                ) // To go home.
                play = false
            }
        }
        if (play) {
            play()
            showResults()
        }
    }

    private fun showResults() {
        val builder = AlertDialog.Builder(this@RockPaperScissors)
        builder.setCancelable(false)
        builder.setPositiveButton(
            "OK"
        ) { dialog, which ->
            // Do nothing
        }

        // Sets the right message according to result.
        if (gameResult == Result.LOSS) {
            builder.setMessage("You Loose!")
        } else if (gameResult == Result.WIN) {
            builder.setMessage("You Win!")
        } else if (gameResult == Result.DRAW) {
            builder.setMessage("It's a draw!")
        }
        val alert = builder.create()
        alert.show()
    }

    private fun play() {
        // Generates a random play.
        val rand = (Math.random() * 10).toInt() % 3
        var androidSelection: Option? = null
        val imageView = findViewById<View>(R.id.ImageViewAnswerAndroid) as ImageView
        when (rand) {
            0 -> {
                androidSelection = Option.ROCK
                imageView.setImageResource(R.drawable.rock)
            }
            1 -> {
                androidSelection = Option.PAPER
                imageView.setImageResource(R.drawable.paper)
            }
            2 -> {
                androidSelection = Option.SCISSORS
                imageView.setImageResource(R.drawable.scissors)
            }
        }
        // Determine game result according to user selection and Android selection.
        gameResult = if (androidSelection == userSelection) {
            Result.DRAW
        } else if (androidSelection == Option.ROCK && userSelection == Option.SCISSORS) {
            Result.LOSS
        } else if (androidSelection == Option.PAPER && userSelection == Option.ROCK) {
            Result.LOSS
        } else if (androidSelection == Option.SCISSORS && userSelection == Option.PAPER) {
            Result.LOSS
        } else {
            Result.WIN
        }
    }
}