import java.security.InvalidParameterException

private enum class GameMove(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    fun evaluateGameResult(otherPlayerMove: GameMove): GameResult = when {
        this == otherPlayerMove -> GameResult.TIE
        this == ROCK && otherPlayerMove == SCISSORS -> GameResult.WIN
        this == SCISSORS && otherPlayerMove == PAPER -> GameResult.WIN
        this == PAPER && otherPlayerMove == ROCK -> GameResult.WIN
        else -> GameResult.LOSE
    }
}

private enum class GameResult(val score: Int) {
    WIN(6),
    TIE(3),
    LOSE(0);
}

private fun mapGameMove(rawInput: String): GameMove {
    return when (rawInput) {
        "A", "X" -> GameMove.ROCK
        "B", "Y" -> GameMove.PAPER
        "C", "Z" -> GameMove.SCISSORS
        else -> {
            throw InvalidParameterException()
        }
    }
}

private fun mapExpectedResult(rawInput: String): GameResult {
    return when (rawInput) {
        "X" -> GameResult.LOSE
        "Y" -> GameResult.TIE
        "Z" -> GameResult.WIN
        else -> {
            throw InvalidParameterException()
        }
    }
}

private fun resolvePlayerMove(otherPlayerMove: GameMove, expectedResult: GameResult): GameMove {
    return when (expectedResult) {
        GameResult.WIN -> when (otherPlayerMove) {
            GameMove.PAPER -> GameMove.SCISSORS
            GameMove.ROCK -> GameMove.PAPER
            GameMove.SCISSORS -> GameMove.ROCK
        }
        GameResult.LOSE -> when (otherPlayerMove) {
            GameMove.PAPER -> GameMove.ROCK
            GameMove.ROCK -> GameMove.SCISSORS
            GameMove.SCISSORS -> GameMove.PAPER
        }
        GameResult.TIE -> otherPlayerMove
    }
}

private fun calculateScore(input: List<String>, resolveRoundScore: (List<String>) -> Int): Int {
    var score = 0
    input.forEach {
        score += resolveRoundScore(it.split(" "))
    }
    return score
}

private fun part1(input: List<String>): Int {
    return calculateScore(input) {
        val playerMove = mapGameMove(it[1])
        val gameResult = playerMove.evaluateGameResult(mapGameMove(it[0]))

        playerMove.score + gameResult.score
    }
}

private fun part2(input: List<String>): Int {
    return calculateScore(input) {
        val expectedResult = mapExpectedResult(it[1])
        val playerMove = resolvePlayerMove(mapGameMove(it[0]), expectedResult)

        expectedResult.score + playerMove.score
    }
}

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println(part1(testInput))
    println(part2(testInput))
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
