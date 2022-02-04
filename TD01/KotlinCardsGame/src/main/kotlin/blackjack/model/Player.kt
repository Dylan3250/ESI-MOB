package blackjack.model

open class Player(val name: String = "UNKNOWN") {
    private val _hand: MutableList<Card> = mutableListOf()

    internal val hand: List<Card>
        get() = _hand

    internal var score: Int = 0
        get() {
            var field = 0
            with(_hand) {
                forEach { field += it.score }
                val aces = filter { it.isAce() }
                var index = 0
                while (field > Game.MAX && index < aces.size) {
                    field -= 10
                    index++
                }
            }
            return field
        }

    internal fun add(card: Card) { _hand.add(card) }

    internal fun clear() {
        _hand.clear()
    }

    internal fun bust() = score > Game.MAX
}