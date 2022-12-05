private infix fun IntRange.isSubrange(other: IntRange) = (this - other).isEmpty()
private infix fun IntRange.isOverlapping(other: IntRange) = this.intersect(other).isNotEmpty()

private fun part1(input: List<String>): Int {
    return transferToRanges(input)
        .map { (a, b) -> a isSubrange b || b isSubrange a }
        .count { it }
}

private fun part2(input: List<String>): Int {
    return transferToRanges(input)
        .map { (a, b) -> a isOverlapping b }
        .count { it }
}

private fun transferToRanges(input: List<String>) = input.map {
    it.split(",").map { rawRange ->
        rawRange.split("-").let { (a, b) -> IntRange(a.toInt(), b.toInt()) }
    }
}

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    println(part1(testInput))
    println(part2(testInput))
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
