package es.uji.jvilar.tennisscores

import android.os.Parcel
import android.os.Parcelable

enum class ScoreType {
    Normal, Deuce, Advantage, AdvantageOther
}

class TennisScore() : Parcelable {
    var type: ScoreType = ScoreType.Normal
        private set
    var points: Int = 0
        private set
    var games: Int = 0
        private set
    var sets: Int = 0
        private set
    private val _setResults = ArrayList<Int>()
    val setResults: List<Int> = _setResults

    constructor(parcel: Parcel) : this() {
        type = parcel.readType()
        points = parcel.readInt()
        games = parcel.readInt()
        sets = parcel.readInt()
        _setResults.addAll(parcel.createIntArray()!!.toList())
    }

    fun reset() {
        points = 0
        sets = 0
        games = 0
        _setResults.clear()
    }

    fun addPoint(other: TennisScore) {
        when(type) {
            ScoreType.Deuce -> {
                type = ScoreType.Advantage
                other.type = ScoreType.AdvantageOther
            }
            ScoreType.Advantage -> addGame(other)
            ScoreType.AdvantageOther -> {
                type = ScoreType.Deuce
                other.type = ScoreType.Deuce
            }
            ScoreType.Normal -> points = when (points) {
                0 -> 15
                15 -> 30
                30 -> {
                    if (other.points == 40) {
                        type = ScoreType.Deuce
                        other.type = ScoreType.Deuce
                    }
                    40
                }
                40 -> {
                    addGame(other)
                    0
                }
                else -> throw Exception("Impossible points: $points")
            }
        }
    }

    private fun clearPoints() {
        type = ScoreType.Normal
        points = 0
    }

    private fun clearGames() {
        clearPoints()
        _setResults.add(games)
        games = 0
    }

    private fun addGame(other: TennisScore) {
        clearPoints()
        other.clearPoints()
        games++
        if (games == 6)
            addSet(other)
    }

    private fun addSet(other: TennisScore) {
        clearGames()
        other.clearGames()
        sets++
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeType(type)
        parcel.writeInt(points)
        parcel.writeInt(games)
        parcel.writeInt(sets)
        parcel.writeIntArray(_setResults.toIntArray())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TennisScore> {
        override fun createFromParcel(parcel: Parcel): TennisScore {
            return TennisScore(parcel)
        }

        override fun newArray(size: Int): Array<TennisScore?> {
            return arrayOfNulls(size)
        }
    }
}

private fun Parcel.writeType(type: ScoreType) = writeInt(type.ordinal)

private fun Parcel.readType() = ScoreType.values()[readInt()]
