package blackjack.model

import blackjack.dto.ScoreDto
import blackjack.repository.Repository
import java.lang.IllegalStateException

class Game : Model {
    companion object {
        const val MAX = 21
        const val MIN_IA = 17
    }

    private val deck = Deck()
    lateinit var player: Player

    override val bank: Bank = Bank()
    override val winner: Player
        get() = when {
            player.bust() -> bank
            bank.bust() -> player
            player.score < bank.score -> bank
            else -> player
        }

    init {
        deck.shuffle()
    }

    override fun addName(name: String) {
        if (name.isBlank()) {
            throw IllegalArgumentException("Le nom ne peut pas être vide")
        }
        player = Player(name)
    }

    override fun start() {
        player.clear()
        bank.clear()
        hit()
        hit()
    }

    override fun canHit(): Boolean = !player.bust()

    override fun hit() {
        if (!canHit()) {
            throw IllegalStateException("Vous avez dépassé la limite du jeu")
        }
        player.add(deck.hit())
    }

    override fun stop() {
        while(bank.score < MIN_IA) {
            bank.add(deck.hit())
        }
    }

    override fun getAllScores() = Repository.getAll()
    override fun saveScores() { Repository.add(ScoreDto(bank.score, player.score)) }
}
