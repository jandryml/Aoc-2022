enum class GameMove(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);
}

enum class GameResult(val score: Int) {
    PLAYER_WON(6),
    TIE(3),
    ELF_WON(0)
}

fun main() {

    fun mapElfMove(rawInput: String): GameMove {
        return when (rawInput) {
            "A" -> GameMove.ROCK
            "B" -> GameMove.PAPER
            "C" -> GameMove.SCISSORS
            else -> {
                throw Exception("Invalid state")
            }
        }
    }

    fun mapPlayerMove(rawInput: String): GameMove {
        return when (rawInput) {
            "X" -> GameMove.ROCK
            "Y" -> GameMove.PAPER
            "Z" -> GameMove.SCISSORS
            else -> {
                throw Exception("Invalid state")
            }
        }
    }

    fun mapExpectedResult(rawInput: String): GameResult {
        return when (rawInput) {
            "X" -> GameResult.ELF_WON
            "Y" -> GameResult.TIE
            "Z" -> GameResult.PLAYER_WON
            else -> {
                throw Exception("Invalid state")
            }
        }
    }

    fun resolveGameResult(elfMove: GameMove, playerMove: GameMove): GameResult {
        return when (playerMove) {
            elfMove -> GameResult.TIE
            GameMove.ROCK -> if (elfMove != GameMove.PAPER) GameResult.PLAYER_WON else GameResult.ELF_WON
            GameMove.PAPER -> if (elfMove != GameMove.SCISSORS) GameResult.PLAYER_WON else GameResult.ELF_WON
            GameMove.SCISSORS -> if (elfMove != GameMove.ROCK) GameResult.PLAYER_WON else GameResult.ELF_WON
        }
    }

    fun resolvePlayerMove(elfMove: GameMove, expectedResult: GameResult): GameMove {
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

    fun part1(input: List<String>): Int {
        var score = 0
        input.forEach {
            val moves = it.split(" ")
            val elfMove = mapElfMove(moves[0])
            val playerMove = mapPlayerMove(moves[1])

            val gameResult = resolveGameResult(elfMove, playerMove)

            score += playerMove.score + gameResult.score
        }
        return score
    }

    fun part2(input: List<String>): Int {
        var score = 0
        input.forEach {
            val moves = it.split(" ")
            val elfMove = mapElfMove(moves[0])
            val expectedResult = mapExpectedResult(moves[1])

            val playerMove = resolvePlayerMove(elfMove, expectedResult)

            score += expectedResult.score + playerMove.score
        }
        return score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println(part1(testInput))
    println(part2(testInput))
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
