package blackjack.controller

import blackjack.model.Game
import blackjack.view.*
import java.lang.Exception

class Controller(private val model: Game) {

    init {
        initialize()
        val name = askName()
        model.addName(name)
    }

    fun start() {
        do {
            playMatch()
        } while (displayAskAgain())
        displayOver()
        displayScores(model.getAllScores())
    }

    private fun playMatch() {
        fun playMove(hitChoice: Boolean) {
            try {
                if (hitChoice) {
                    model.hit()
                    displayPlayer(model.player)
                } else {
                    model.stop()
                }
            } catch (exception: Exception) {
                displayError(exception.message)
            }
        }

        try {
            model.start()
            var hitChoice = true
            displayPlayer(model.player)
            while (hitChoice && model.canHit()) {
                hitChoice = displayHitChoice()
                playMove(hitChoice)
            }
            displayPlayer(model.bank)
            displayWinner(model.winner)
            model.saveScores()
        } catch (exception: Exception) {
            displayError("Le jeu ne peut pas démarrer")
            displayError(exception.message)
        }
    }
}