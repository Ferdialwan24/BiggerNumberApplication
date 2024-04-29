package com.example.biggerniggernumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.biggerniggernumber.R.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var points = 0
    private var rounds = 0
    private lateinit var leftBtn: Button
    private lateinit var rightBtn: Button
    private lateinit var pointsView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        leftBtn = findViewById(id.leftBtn)
        rightBtn = findViewById(id.rightBtn)
        pointsView = findViewById(id.points)

        assignNumbers()

        leftBtn.setOnClickListener {
            onLeftButtonClick()
        }

        rightBtn.setOnClickListener {
            onRightButtonClick()
        }
    }

    private fun random_number(): Int {
        return Random.nextInt(100)
    }

    private fun assignNumbers() {
        leftBtn.text = random_number().toString()
        rightBtn.text = random_number().toString()
    }

    private fun onLeftButtonClick() {
        val leftVal = leftBtn.text.toString().toInt()
        val rightVal = rightBtn.text.toString().toInt()

        if (leftVal > rightVal) points++
        updateGame()
    }

    private fun onRightButtonClick() {
        val leftVal = leftBtn.text.toString().toInt()
        val rightVal = rightBtn.text.toString().toInt()

        if (rightVal > leftVal) points++
        updateGame()
    }

    private fun updateGame() {
        pointsView.text = "Points: $points"
        rounds++

        if (rounds >= 10) {
            showScore()
            restartGame()
        } else {
            assignNumbers()
        }
    }

    private fun showScore() {
        // Tampilkan skor dengan AlertDialog atau Toast
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Game Over! Your score is: $points")
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, id ->
                dialog.dismiss()
            }
        val alert = dialogBuilder.create()
        alert.setTitle("Score")
        alert.show()
    }

    private fun restartGame() {
        points = 0
        rounds = 0
        assignNumbers()
        pointsView.text = "Points: $points"
    }
}