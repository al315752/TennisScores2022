package com.example.tennisscores2022

import com.example.tennisscores2022.model.Model
import es.uji.jvilar.tennisscores.ScoreType

class Presenter(val view : MainActivity, val model : Model) {
    init {
        updateView()
    }

    fun onAPointScore(){
        model.pointAScore()
        updateView()
    }

    fun onBPointScore() {
        model.pointBScore()
        updateView()
    }

    fun onResetRequested(){
        model.reset()
        updateView()
    }

    private fun updateView(){
        view.displayScores(model.setsA.toString(), model.setsB.toString())
        view.displaySetResults(setResultsToString(model.setResultsA), setResultsToString(model.setResultsB))
        view.displayGames(model.gamesA.toString(), model.gamesB.toString())
        view.displayPoints(pointsToString(model.pointsA, model.typeA), pointsToString(model.pointsB, model.typeB))
        view.isMaxSetsEnabled = model.atGameBeginning
    }

    private fun setResultsToString(setResults: List<Int>): String{
        val setNumber : Int = if (view.fiveSets.isChecked) 5 else 3
        var sets = ""

        for (item in setResults){
            sets += item.toString()
        }
        while (sets.length < setNumber){
            sets += "_"
        }

        if (model.isMatchEnded(setNumber)){
            view.buttonA.isEnabled = false
            view.buttonB.isEnabled = false
        } else {
            view.buttonA.isEnabled = true
            view.buttonB.isEnabled = true
        }

        return sets
    }

    private fun pointsToString(points: Int, type: ScoreType): String{
        return if (type == ScoreType.Normal){
            if (points == 0) "00"
            else points.toString()
        } else if (type == ScoreType.Deuce) "40"
        else if (type == ScoreType.Advantage) "Ad"
        else "__"
    }
}