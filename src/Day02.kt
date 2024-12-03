import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var safeCount = 0
        input.forEach { line ->
            val levels = line.split(' ')
                .filter { it.isNotBlank() }
                .map { it.toInt() }
            var safe = true
            var levelDiff: Char? = null
            for (index in 0..levels.size - 2) {
                val differ = levels[index] - levels[index + 1]
                if (differ == 0 || abs(differ) > 3) {
                    safe = false
                    break
                }
                val currentDiff = if (differ < 0) '-' else '+'
                if (levelDiff != null && levelDiff != currentDiff) {
                    safe = false
                    break
                }
                levelDiff = currentDiff
            }
            safeCount += if (safe) 1 else 0
        }
        return safeCount
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("7 6 4 2 1")) == 1)
    check(part2(listOf()) == 0)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
