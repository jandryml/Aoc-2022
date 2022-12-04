private fun part1(input: List<String>): Int {
    return input.asSequence()
        .map { it.chunked(it.length / 2) }
        .map { (a, b) -> a.find { it in b } }
        .sumOf { it?.toPriority() ?: 0 }
}

private fun part2(input: List<String>): Int {
    return input.chunked(3)
        .map { (a, b, c) -> a.find { it in b && it in c } }
        .sumOf { it?.toPriority() ?: 0 }
}

private fun Char.toPriority() =
    when (this) {
        in 'A'..'Z' -> this - 'A' + 27
        in 'a'..'z' -> this - 'a' + 1
        else -> throw Exception("Invalid char")
    }

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    println(part1(testInput))
    println(part2(testInput))
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
