import kotlin.math.abs

fun main() {
    fun isSafe(levels: List<Int>): Boolean {
        var safe = true
        var levelStatus: Char? = null
        for (index in 0..levels.size - 2) {
            val differ = levels[index] - levels[index + 1]
            if (differ == 0 || abs(differ) > 3) {
                safe = false
                break
            }
            val currentStatus = if (differ < 0) '-' else '+'
            if (levelStatus != null && levelStatus != currentStatus) {
                safe = false
                break
            }
            levelStatus = currentStatus
        }
        return safe
    }


    fun isSafeWithProblemDampener(levels: List<Int>): Boolean {
        if (isSafe((levels))) {
            return true
        }

        var isSkippedSafe = false
        levels.indices.forEach {
            val skippedLevels = levels.toMutableList().apply { removeAt(it) }
            if (isSafe(skippedLevels)) {
                isSkippedSafe = true
                return@forEach
            }
        }
        if (isSkippedSafe) {
            return true
        }

        return false
    }

    fun part1(input: List<String>): Int {
        val levels = input.map { line ->
            line.split(' ')
                .filter { it.isNotBlank() }
                .map { it.toInt() }
        }
        return levels.map { isSafe(it) }.count { it }
    }

    fun part2(input: List<String>): Int {
        val levels = input.map { line ->
            line.split(' ')
                .filter { it.isNotBlank() }
                .map { it.toInt() }
        }

        return levels.map { isSafeWithProblemDampener(it) }.count { it }
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("7 6 4 2 1")) == 1)
    check(part2(listOf("7 6 4 2 1")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
