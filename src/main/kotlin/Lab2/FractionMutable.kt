package com.example.Lab2

class FractionMutable(var numerator: Int, var denominator: Int, var sign: Int = 1) {

    init {
        if (denominator == 0) throw IllegalArgumentException("Denominator cannot be zero")
        if (numerator < 0) {
            numerator = -numerator
            sign = -sign
        }
        if (denominator < 0) {
            denominator = -denominator
            sign = -sign
        }
        reduce()
    }

    private fun reduce() {
        val gcd = gcd(numerator, denominator)
        numerator /= gcd
        denominator /= gcd
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }

    override fun toString(): String {
        val num = if (sign < 0) -numerator else numerator
        return "$num/$denominator"
    }

    fun negate() {
        sign = -sign
    }

    fun add(other: FractionMutable) {
        val commonDenominator = denominator * other.denominator
        val newNumerator = sign * numerator * other.denominator + other.sign * other.numerator * denominator
        numerator = newNumerator
        denominator = commonDenominator
        sign = if (newNumerator < 0) -1 else 1
        numerator = kotlin.math.abs(newNumerator)
        reduce()
    }

    fun mult(other: FractionMutable) {
        numerator *= other.numerator
        denominator *= other.denominator
        sign *= other.sign
        reduce()
    }

    fun div(other: FractionMutable) {
        if (other.numerator == 0) throw IllegalArgumentException("Cannot divide by zero")
        numerator *= other.denominator
        denominator *= other.numerator
        sign *= other.sign
        reduce()
    }

    fun intPart(): Int {
        return (sign * numerator) / denominator
    }
}