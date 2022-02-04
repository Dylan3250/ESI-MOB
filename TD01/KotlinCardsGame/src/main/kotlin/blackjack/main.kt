package blackjack

import blackjack.controller.Controller
import blackjack.model.*

fun main(args: Array<String>) {
    val model = Game()
    val controller = Controller(model)
    controller.start()
}