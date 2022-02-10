package com.example.tennisscores2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import com.example.tennisscores2022.model.Model

class MainActivity : AppCompatActivity(), Interface {

    lateinit var scoreA : TextView
    lateinit var scoreB : TextView
    lateinit var setsA : TextView
    lateinit var setsB : TextView
    lateinit var gamesA : TextView
    lateinit var gamesB : TextView
    lateinit var pointsA : TextView
    lateinit var pointsB : TextView

    lateinit var buttonA : Button
    lateinit var buttonB : Button

    lateinit var threeSets : RadioButton
    lateinit var fiveSets : RadioButton

    lateinit var model : Model
    lateinit var presenter : Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreA = findViewById(R.id.scoreA)
        scoreB = findViewById(R.id.scoreB)
        setsA = findViewById(R.id.setsA)
        setsB = findViewById(R.id.setsB)
        gamesA = findViewById(R.id.gamesA)
        gamesB = findViewById(R.id.gamesB)
        pointsA = findViewById(R.id.pointsA)
        pointsB = findViewById(R.id.pointsB)

        buttonA = findViewById(R.id.buttonA)
        buttonB = findViewById(R.id.buttonB)

        threeSets = findViewById(R.id.threeSets)
        fiveSets = findViewById(R.id.fiveSets)

        if (savedInstanceState != null){
            model = savedInstanceState.getParcelable<Model>("MODEL")!!
            fiveSets.isChecked = savedInstanceState.getBoolean("FIVE_SETS")
        } else {
            model = Model()
        }
        presenter = Presenter (this, model)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("MODEL", model)
        outState.putBoolean("FIVE_SETS", fiveSets.isChecked)
    }

    override var isMaxSetsEnabled: Boolean
        get() = threeSets.isEnabled && fiveSets.isEnabled
        set(value) {
            threeSets.isEnabled = value
            fiveSets.isEnabled = value
        }

    override fun displayScores(playerA : String, playerB : String){
        scoreA.text = playerA
        scoreB.text = playerB
    }

    override fun displaySetResults(playerA: String, playerB: String){
        setsA.text = playerA
        setsB.text = playerB
    }

    override fun displayGames(playerA: String, playerB: String){
        gamesA.text = playerA
        gamesB.text = playerB
    }

    override fun displayPoints(playerA: String, playerB: String){
        pointsA.text = playerA
        pointsB.text = playerB
    }

    fun pointAPressed(view: View){
        presenter.onAPointScore()
    }

    fun pointBPressed(view: View){
        presenter.onBPointScore()
    }

    fun resetButtonPressed(view: View){
        presenter.onResetRequested()
    }
}