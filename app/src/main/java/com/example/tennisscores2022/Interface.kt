package com.example.tennisscores2022

interface Interface {
    var isMaxSetsEnabled: Boolean
    fun displayScores(playerA : String, playerB : String)
    fun displaySetResults(playerA: String, playerB: String)
    fun displayGames(playerA: String, playerB: String)
    fun displayPoints(playerA: String, playerB: String)
}