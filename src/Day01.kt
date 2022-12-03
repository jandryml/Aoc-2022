private fun getSumOfHighestElements(input: List<String>, elementCount: Int) =
    input.joinToString(";")
        .split(";;")
        .map { it.split(";").sumOf { calories -> calories.toInt() } }
        .sortedDescending()
        .take(elementCount)
        .sum()

private fun part1(input: List<String>) = getSumOfHighestElements(input, 1)
private fun part2(input: List<String>) = getSumOfHighestElements(input, 3)

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    println(part1(testInput))
    println(part2(testInput))
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
