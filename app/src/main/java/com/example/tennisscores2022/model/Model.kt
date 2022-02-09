package com.example.tennisscores2022.model

import es.uji.jvilar.tennisscores.TennisScore

class Model {
    private val playerA = TennisScore()
    private val playerB = TennisScore()

    val typeA get() = playerA.type
    val typeB get() = playerB.type
    val pointsA get() = playerA.points
    val pointsB get() = playerB.points
    val setsA get() = playerA.sets
    val setsB get() = playerB.sets
    val gamesA get() = playerA.games
    val gamesB get() = playerB.games
    val setResultsA get() = playerA.setResults
    val setResultsB get() = playerB.setResults

    fun pointAScore() {
        playerA.addPoint(playerB)
    }

    fun pointBScore() {
        playerB.addPoint(playerA)
    }

    fun reset() {
        playerA.reset()
        playerB.reset()
    }

    fun isMatchEnded(setNumber: Int): Boolean{
        return setsA > setNumber / 2 || setsB > setNumber / 2
    }
}