private fun part1(input: List<String>): Int {
    return input.asSequence().map { it.chunked(it.length / 2).map { it.toCharArray() } }
        .map { it.reduce {acc, chars -> acc.intersect(chars.asIterable().toSet()).toCharArray() } }
        .map { it[0] }
        .map {
            if (it in 'A'..'Z')
                return@map it - 'A' + 27
            if (it in 'a'..'z')
                return@map it - 'a' + 1
            throw Exception("Invalid char")
        }.sum()
}

private fun part2(input: List<String>): Int {
    return input.size
}

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    println(part1(testInput))
    println(part2(testInput))
    check(part1(testInput) == 157)
//    check(part2(testInput) == 1)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
