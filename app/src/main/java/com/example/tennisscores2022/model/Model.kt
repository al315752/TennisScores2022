package com.example.tennisscores2022.model

import android.os.Parcel
import android.os.Parcelable
import es.uji.jvilar.tennisscores.TennisScore

class Model() : Parcelable {
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
    val atGameBeginning :Boolean get() = (playerA.points == 0 && playerA.games == 0 && playerA.sets == 0) && (playerB.points == 0 && playerB.games == 0 && playerB.sets == 0)

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Model> {
        override fun createFromParcel(parcel: Parcel): Model {
            return Model(parcel)
        }

        override fun newArray(size: Int): Array<Model?> {
            return arrayOfNulls(size)
        }
    }

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