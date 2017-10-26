package pl.artcoder.playground.kata.game

object BowlingGame {

    @JvmStatic
    fun scoreFor(rolls: String): Int = totalScore(rolls.toCharArray())

    private fun totalScore(rolls: CharArray, index: Int = 0, score: Int = 0): Int {
        val MISS = '-'
        val STRIKE = 'X'
        val SPARE = '/'

        fun rollToScoreAt(index: Int): Int =
                when (rolls[index]) {
                    MISS -> 0
                    STRIKE -> 10
                    SPARE -> 10 - rolls[index - 1].charToInt()
                    else -> rolls[index].charToInt()
                }

        fun applyIf(condition: Boolean, fa: () -> Int): Int = if (condition) fa() else 0

        fun numberOfPreviousStrikes(): Int = rolls.take(index).filter { it == STRIKE }.count()

        fun strikeBonus(): Int = 10 +
                applyIf(index + numberOfPreviousStrikes() < 18,
                        { rollToScoreAt(index + 1) + rollToScoreAt(index + 2) }
                )

        fun spareBonus(): Int = 10 -
                rollToScoreAt(index - 1) +
                applyIf(index < 19, { rollToScoreAt(index + 1) })

        val remainingRolls = rolls
                .drop(index)
                .toCharArray()

        return when (remainingRolls.firstOrNull()) {
            MISS -> totalScore(rolls, index + 1, score)
            STRIKE -> totalScore(rolls, index + 1, score + strikeBonus())
            SPARE -> totalScore(rolls, index + 1, score + spareBonus())
            null -> score
            else -> totalScore(rolls, index + 1, score + remainingRolls.first().charToInt())
        }
    }

    private fun Char.charToInt() = this.toString().toInt()

}