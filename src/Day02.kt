import java.security.InvalidParameterException

private enum class GameMove(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);
}

private enum class GameResult(val score: Int) {
    PLAYER_WON(6),
    TIE(3),
    ELF_WON(0)
}

private fun mapElfMove(rawInput: String): GameMove {
    return when (rawInput) {
        "A" -> GameMove.ROCK
        "B" -> GameMove.PAPER
        "C" -> GameMove.SCISSORS
        else -> {
            throw InvalidParameterException()
        }
    }
}

private fun mapPlayerMove(rawInput: String): GameMove {
    return when (rawInput) {
        "X" -> GameMove.ROCK
        "Y" -> GameMove.PAPER
        "Z" -> GameMove.SCISSORS
        else -> {
            throw InvalidParameterException()
        }
    }
}

private fun mapExpectedResult(rawInput: String): GameResult {
    return when (rawInput) {
        "X" -> GameResult.ELF_WON
        "Y" -> GameResult.TIE
        "Z" -> GameResult.PLAYER_WON
        else -> {
            throw InvalidParameterException()
        }
    }
}

private fun mapPlayerWin(havePlayerWon: Boolean) = if (havePlayerWon) GameResult.PLAYER_WON else GameResult.ELF_WON

private fun resolveGameResult(elfMove: GameMove, playerMove: GameMove): GameResult {
    return when (playerMove) {
        elfMove -> GameResult.TIE
        GameMove.ROCK -> mapPlayerWin(elfMove != GameMove.PAPER)
        GameMove.PAPER -> mapPlayerWin(elfMove != GameMove.SCISSORS)
        GameMove.SCISSORS -> mapPlayerWin(elfMove != GameMove.ROCK)
    }
}

private fun resolvePlayerMove(elfMove: GameMove, expectedResult: GameResult): GameMove {
    return when (expectedResult) {
        GameResult.PLAYER_WON -> when (elfMove) {
            GameMove.PAPER -> GameMove.SCISSORS
            GameMove.ROCK -> GameMove.PAPER
            GameMove.SCISSORS -> GameMove.ROCK
        }
        GameResult.ELF_WON -> when (elfMove) {
            GameMove.PAPER -> GameMove.ROCK
            GameMove.ROCK -> GameMove.SCISSORS
            GameMove.SCISSORS -> GameMove.PAPER
        }
        GameResult.TIE -> elfMove
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
        val playerMove = mapPlayerMove(it[1])
        val gameResult = resolveGameResult(mapElfMove(it[0]), playerMove)

        playerMove.score + gameResult.score
    }
}

private fun part2(input: List<String>): Int {
    return calculateScore(input) {
        val expectedResult = mapExpectedResult(it[1])
        val playerMove = resolvePlayerMove(mapElfMove(it[0]), expectedResult)

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
