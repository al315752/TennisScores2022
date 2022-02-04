package com.example.tennisscores2022

import com.example.tennisscores2022.model.Model
import es.uji.jvilar.tennisscores.ScoreType

class Presenter(val view : MainActivity) {
    val model : Model = Model()
    var setsA : String = "___"
    var setsB : String = "___"
    var pointsA : String = "00"
    var pointsB : String = "00"

    fun onAPointScore(){
        model.pointAScore()
        getStringSetResults()
        updateView()
    }

    fun onBPointScore() {
        model.pointBScore()
        getStringSetResults()
        updateView()
    }

    fun onResetRequested(){
        model.reset()
        getStringSetResults()
        updateView()
    }

    private fun getStringSetResults(){
        val setNumber : Int = if (view.fiveSets.isChecked) 5 else 3

        setsA = ""
        setsB = ""

        for (item in model.playerA.setResults){
            setsA += item.toString()
            println("sets A -> $item")
        }
        while (setsA.length < setNumber){
            setsA += "_"
        }

        for (item in model.playerB.setResults){
            setsB += item.toString()
            println("sets B -> $item")
        }
        while (setsB.length < setNumber){
            setsB += "_"
        }

        if (model.playerA.setResults.size == setNumber || model.playerB.setResults.size == setNumber){
            view.buttonA.isEnabled = false
            view.buttonB.isEnabled = false
        }
    }

    private fun updateView(){
        getPoints()
        view.displayScores(model.playerA.sets.toString(), model.playerB.sets.toString())
        view.displaySets(setsA, setsB)
        view.displayGames(model.playerA.games.toString(), model.playerB.games.toString())
        view.displayPoints(pointsA, pointsB)
    }

    private fun getPoints(){
        pointsA = if (model.playerA.type == ScoreType.Normal){
            if (model.playerA.points == 0) "00"
            else model.playerA.points.toString()
        } else if (model.playerA.type == ScoreType.Deuce) "40"
        else if (model.playerA.type == ScoreType.Advantage) "Ad"
        else "__"

        pointsB = if (model.playerB.type == ScoreType.Normal){
            if (model.playerB.points == 0) "00"
            else model.playerB.points.toString()
        } else if (model.playerB.type == ScoreType.Deuce) "40"
        else if (model.playerB.type == ScoreType.Advantage) "Ad"
        else "__"
    }
}