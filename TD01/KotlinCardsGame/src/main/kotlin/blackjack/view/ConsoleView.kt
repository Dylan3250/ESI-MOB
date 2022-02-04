package blackjack.view

import blackjack.dto.ScoreDto
import blackjack.model.Card
import blackjack.model.Player


fun initialize() {
    println("Bienvenue au Black Jack !")
}

fun displayOver() {
    println("La partie est terminée !")
}

fun askName(): String {
    print("Quel est votre nom ? : ")
    return readLine()!!.trim()
}

fun askBoolean(question: String): Boolean {
    tailrec fun ask(): Boolean {
        print("$question (oui/non) : ")
        return readLine()!!.let {
            when {
                it.equals("oui", ignoreCase = true) -> true
                it.equals("non", ignoreCase = true) -> false
                else -> {
                    println("Veuillez répondre par \"Oui\" ou \"Non\"")
                    return ask()
                }
            }
        }
    }

    return ask()
    // while (true) {
    //     print("$question : ")
    //     val userEntry = readLine()!!.trim()
    //     when {
    //         "oui".equals(userEntry, ignoreCase = true) -> return true
    //         "non".equals(userEntry, ignoreCase = true) -> return false
    //         else -> println("Veuillez répondre par \"Oui\" ou \"Non\"")
    //     }
    // }
}

fun displayHitChoice() = askBoolean("Voulez-vous une carte supplémentaire ?")
fun displayAskAgain() = askBoolean("Voulez-vous rejouer ?")

fun displayHand(hand: List<Card>) {
    if (hand.isEmpty()) {
        println("\t\tAucune carte dans la main !")
    } else {
        for ((index, card) in hand.withIndex()) {
            println("\t\tCarte numéro $index : ${card.value} de ${card.color}")
        }
    }
}

fun displayPlayer(player: Player) {
    println("${player.name} - ${player.score}")
    println()
}

fun displayWinner(player: Player) {
    println("Le vainqueur est : ")
    displayPlayer(player)
}

fun displayScores(scores: List<ScoreDto>) {
    println("Liste des scores")
    for (element in scores) {
        println("${element.bank}\t${element.player}")
    }
}

fun displayError(message: String?) {
    println(message)
}
