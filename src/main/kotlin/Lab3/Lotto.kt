package com.example.Lab3

class Lotto(private val lottoRange: IntRange = 1..40, private val n: Int = 7) {
    private val secretNumbers: List<Int> = pickNDistinct(lottoRange, n)!!

    fun pickNDistinct(range: IntRange, n: Int): List<Int>? {
        return if (n <= range.count()) range.shuffled().take(n) else null
    }

    fun numDistinct(list: List<Int>): Int {
        return list.toSet().size
    }

    fun numCommon(list1: List<Int>, list2: List<Int>): Int {
        return list1.intersect(list2.toSet()).size
    }

    fun isLegalLottoGuess(guess: List<Int>, range: IntRange = lottoRange, count: Int = n): Boolean {
        return guess.size == count && numDistinct(guess) == count && guess.all { it in range }
    }

    fun checkGuess(guess: List<Int>, secret: List<Int> = secretNumbers): Int {
        return if (isLegalLottoGuess(guess)) numCommon(guess, secret) else 0
    }
}

fun readNDistinct(low: Int, high: Int, n: Int): List<Int> {
    while (true) {
        print("Give $n numbers from $low to $high, separated by commas: ")
        val input = readLine()
        val guess = input?.split(",")
            ?.map { it.trim().toIntOrNull() }
            ?.filterNotNull() ?: emptyList()

        if (guess.size == n && guess.distinct().size == n && guess.all { it in low..high }) {
            return guess
        } else {
            println("Invalid input. Please ensure you enter $n distinct numbers within the range.")
        }
    }
}

fun playLotto() {
    val lotto = Lotto()
    do {
        val guess = readNDistinct(1, 40, 7)
        val correctGuesses = lotto.checkGuess(guess)
        println("Lotto numbers: ${lotto.pickNDistinct(1..40, 7)}, you got $correctGuesses correct")
        print("More? (Y/N): ")
    } while (readLine()?.uppercase() == "Y")
}

fun findLotto(lotto: Lotto): Pair<Int, List<Int>> {
    var attempts = 0
    var guess: List<Int>
    var correctGuesses: Int

    do {
        attempts++
        guess = lotto.pickNDistinct(1..40, 7)!!
        correctGuesses = lotto.checkGuess(guess)
    } while (correctGuesses < 7)

    return Pair(attempts, guess)
}

fun main() {
    playLotto()
}