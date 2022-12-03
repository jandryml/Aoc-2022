enum class Moves(val score: Int) {
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

    fun mapRawToTokens(rawInput: String): Moves {
        return when (rawInput) {
            "A", "X" -> Moves.ROCK
            "B", "Y" -> Moves.PAPER
            "C", "Z" -> Moves.SCISSORS
            else -> {
                throw Exception("Invalid state")
            }
        }
    }

    fun resolveIfPlayerWon(playerMove: Moves, elfMove: Moves): GameResult {
        return when (playerMove) {
            elfMove -> GameResult.TIE
            Moves.ROCK -> if (elfMove != Moves.PAPER) GameResult.PLAYER_WON else GameResult.ELF_WON
            Moves.PAPER -> if (elfMove != Moves.SCISSORS) GameResult.PLAYER_WON else GameResult.ELF_WON
            Moves.SCISSORS -> if (elfMove != Moves.ROCK) GameResult.PLAYER_WON else GameResult.ELF_WON
        }
    }

    fun part1(input: List<String>): Int {
        var score = 0
        input.forEach {
            val moves = it.split(" ").map(::mapRawToTokens)
            val elfMove = moves[0]
            val playerMove = moves[1]

            val gameResult = resolveIfPlayerWon(playerMove, elfMove)

            score += playerMove.score + gameResult.score
        }
        return score
    }

    fun part2(input: List<String>): Int {
        return input.size
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
