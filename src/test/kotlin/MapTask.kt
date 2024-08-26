fun translateNumber(number: Char): String {
    return when (number) {
        '1' -> "one"
        '2' -> "two"
        '3' -> "three"
        '4' -> "four"
        '5' -> "five"
        '6' -> "six"
        '7' -> "seven"
        '8' -> "eight"
        '9' -> "nine"
        else -> "You are stupid as a rock"
    }
}

fun translateNumbers(numbers: String): String {
    return numbers.map { translateNumber(it) }
        .filter { it.isNotEmpty() }
        .joinToString("-")
}

fun main() {
    val numberSequence = "4221"
    val translated = translateNumbers(numberSequence)
    println(translated)
}
/*

val mymap = mapOf {
0 to "zero",
1 to "one",
2 to "two",
3 to "three",
}
fun tra(n: Int): String {

val gradesAndCredits = listOf(1 to 4, 5 to 4,c4 to 15)
gradesAndCredits.map { it.first * it.second }.sum.toDouble() / gradesAndCredits.map { it.second }.sum()
 */