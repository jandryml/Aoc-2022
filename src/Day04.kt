private fun IntRange.isSubrange(other: IntRange) = (this - other).isEmpty()

private fun part1(input: List<String>): Int {
    return input.map {
        it.split(",").map { it.split("-") }.map { (a, b) -> IntRange(a.toInt(), b.toInt()) }
    }.map { (a, b) -> a.isSubrange(b) || b.isSubrange(a) }.count { it }
}

private fun part2(input: List<String>): Int {
    return input.size
}

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    println(part1(testInput))
    println(part2(testInput))
    check(part1(testInput) == 2)
    // check(part2(testInput) == 1)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
