import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Long {
         var (leftList, rightList) = input.map {
             it.split(' ')
                 .filter { it.isNotBlank() }
                 .map { it.toLong() }
         }
             .map { Pair(it[0], it[1]) }
             .unzip()
        leftList = leftList.sorted()
        rightList = rightList.sorted()
        return leftList.zip(rightList).sumOf { abs(it.first - it.second) }
    }

    fun part2(input: List<String>): Long {
        val (leftList, rightList) = input.map {
            it.split(' ')
                .filter { it.isNotBlank() }
                .map { it.toLong() }
        }
            .map { Pair(it[0], it[1]) }
            .unzip()
        val countTotalMap =  leftList.associateWith { 0L }.toMutableMap()
        val countNumCacheMap = leftList.associateWith { 0L }.toMutableMap()
        leftList.forEach { numToCount ->
            if (countNumCacheMap.containsKey(numToCount)) {
                val count =  rightList.filter { it == numToCount }.size.toLong()
                countNumCacheMap[numToCount] = count
            }
            countTotalMap[numToCount] = countTotalMap[numToCount]!! + countNumCacheMap[numToCount]!!
        }
        return countTotalMap.entries.sumOf { it.key * it.value }
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("3   4")) == 1L)
     check(part2(listOf("3   4")) == 0L)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11L)
    check(part2(testInput) == 31L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
