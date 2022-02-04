package blackjack.model

import blackjack.dto.ScoreDto

interface Model {
    val bank: Bank
    val winner: Player

    fun addName(name: String)
    fun start()
    fun canHit(): Boolean
    fun hit()
    fun stop()
    fun getAllScores(): List<ScoreDto>
    fun saveScores()
}
