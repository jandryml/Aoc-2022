fun main() {
    fun part1(input: List<String>): Int {
        var max = 0
        var actualValue = 0
        input.forEach {
            if (it.isNotEmpty()) {
                actualValue += it.toInt()
            } else {
                if (actualValue > max) {
                    max = actualValue
                }
                actualValue = 0
            }
        }
        return max
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    println(part1(testInput))
    println(part2(testInput))
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
