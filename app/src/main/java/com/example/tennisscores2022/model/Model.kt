package com.example.tennisscores2022.model

import es.uji.jvilar.tennisscores.TennisScore

class Model {
    val playerA = TennisScore()
    val playerB = TennisScore()

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
}