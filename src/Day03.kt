fun main() {
    val patternMul = """mul\(([0-9]{1,3}),([0-9]{1,3})\)""".toRegex()

    fun part1(input: List<String>): Long {
        val longText = input.joinToString("")
        val matchResult = patternMul.findAll(longText)
        return matchResult.sumOf { it.groupValues[1].toLong() * it.groupValues[2].toLong() }
    }

    fun part2(input: List<String>): Long {
        val longText = input.joinToString("")
        var doMul = true
        var sum = 0L
        val matches = """$patternMul|do\(\)|don't\(\)""".toRegex().findAll(longText)
        matches.forEach {
            when(it.value) {
                "do()" -> doMul = true
                "don't()" -> doMul = false
                else -> if (doMul) sum +=  it.groupValues[1].toLong() * it.groupValues[2].toLong()
            }
        }
        return sum
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("xdo())&_mul(2,4)%don't()%mul(2,5)")) == 18L)
    check(part2(listOf("xdo())&_mul(2,4)%don't()%mul(2,5)")) == 8L)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161L)
    check(part2(testInput) == 48L)

    // Read the input from the `src/Day01.txt` file.
   val input = readInput("Day03")
    part1(input).println()
   part2(input).println()
}
