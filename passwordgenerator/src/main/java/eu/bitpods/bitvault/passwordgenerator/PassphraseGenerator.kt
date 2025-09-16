package eu.bitpods.bitvault.passwordgenerator

import java.security.SecureRandom
import kotlin.random.Random

object PassphraseGenerator {

    private val random = SecureRandom()

    private const val DICE_ROLLS = 5

    fun generate(
        words: Int,
        numbers: Boolean
    ): String {
        var passphrase = ""
        val diceRoll = (0 until words).map {
            rollSequencialDice(DICE_ROLLS)
        }
        FiveDiceWords.list.split("\n").forEach { line ->
            val codeWord = line.split("\t")
            if (diceRoll.contains(codeWord[0].toInt())) {
                val word = if (numbers) {
                    codeWord[1].addRandomNumber()
                } else {
                    codeWord[1]
                }
                passphrase += if (passphrase.isEmpty()) {
                    word
                } else {
                    "-${word}"
                }
            }
        }
        return passphrase
    }

    private fun rollSequencialDice(times: Int): Int {
        return (0 until times).map { rollSingleDice() }.joinToString(separator = "").toInt()
    }

    private fun rollSingleDice(): Int {
        return random.nextInt(5) + 1
    }

    private fun String.addRandomNumber(): String {
        val number = Random.nextInt(9).toString()
        val indexToAdd = Random.nextInt(this.length)
        return this.map {
            it.toString()
        }.toMutableList().apply {
            add(indexToAdd, number)
        }.joinToString("")
    }
}