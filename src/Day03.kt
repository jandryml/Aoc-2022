private fun part1(input: List<String>): Int {
    return findIntersectItemsAndCalculatePriority(
        input.asSequence()
            .map { it.chunked(it.length / 2).map { it.toCharArray() } }
    )
}

private fun part2(input: List<String>): Int {
    return findIntersectItemsAndCalculatePriority(
        input.chunked(3).asSequence()
            .map { it.map { it.toCharArray() } }
    )
}

private fun findIntersectItemsAndCalculatePriority(sequence: Sequence<List<CharArray>>): Int {
    return sequence.map { findIntersectItems(it) }
        .map { it[0] } // we know that only one common item is present
        .map {
            return@map mapCharToInt(it)
        }.sum()
}

private fun findIntersectItems(it: List<CharArray>) =
    it.reduce { acc, chars -> acc.intersect(chars.asIterable().toSet()).toCharArray() }

private fun mapCharToInt(character: Char): Int {
    if (character in 'A'..'Z')
        return character - 'A' + 27
    if (character in 'a'..'z')
        return character - 'a' + 1
    throw Exception("Invalid char")
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
