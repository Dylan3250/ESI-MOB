package blackjack.dto

data class ScoreDto(val bank: Int, val player: Int) {
    override fun toString(): String = "$bank $player"
}
