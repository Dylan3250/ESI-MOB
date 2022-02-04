package blackjack.model

import java.lang.IllegalArgumentException

internal class Deck {
    private val cards = mutableListOf<Card>().apply {
        Color.values().forEach { color ->
            Value.values().forEach { value ->
                add(Card(value, color))
            }
        }
    }

    // init {
    //     println("DEBUG - Deck init() - Instanciation des cartes")
    //     for (color in Color.values()) {
    //         for (value in Value.values()) {
    //             cards.add(Card(value, color))
    //         }
    //     }
    // }

    internal fun shuffle() {
        cards.shuffle()
    }

    internal fun hit(): Card = cards.removeFirstOrNull()
        ?: throw IllegalArgumentException("Le deck est vide")

    // internal fun hit() : Card {
    //     if (cards.isEmpty()) {
    //         throw IllegalArgumentException("Le deck est vide")
    //     }
    //     return cards.removeFirst()
    // }

}