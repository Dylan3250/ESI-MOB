package blackjack.model

data class Card(val value: Value, val color: Color) {
    internal val score = value.score
    internal fun isAce() = this.value == Value.ACE
}