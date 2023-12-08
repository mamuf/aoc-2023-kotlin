fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val firstDigit = line.first { it.isDigit() }.digitToInt()
            val lastDigit = line.last { it.isDigit() }.digitToInt()
            firstDigit * 10 + lastDigit
        }
    }

    fun part2(input: List<String>): Int {

        return input.sumOf { line ->
            val firstDigitIdx = line.indexOfFirst { it.isDigit() }
            val lastDigitIdx = line.indexOfLast { it.isDigit() }

            val firstWordPool = if (firstDigitIdx < 0) line else line.substring(0..firstDigitIdx)
            val firstWord = Numbers.entries.map { it to firstWordPool.indexOf(it.name) }
                .sortedBy { it.second }
                .firstOrNull { it.second >= 0 }
                ?.first

            val lastWordPool = line.substring(lastDigitIdx.coerceAtLeast(0))
            val lastWordPoolReversed = lastWordPool.reversed()
            val lastWord = Numbers.entries.map { it to lastWordPoolReversed.indexOf(it.name.reversed()) }
                .sortedBy { it.second }
                .firstOrNull { it.second >= 0 }
                ?.first

            val firstDigit = firstWord?.number ?: line[firstDigitIdx].digitToInt()
            val lastDigit = lastWord?.number ?: line[lastDigitIdx].digitToInt()

            val lineNumber = firstDigit * 10 + lastDigit
//            println("line: $lineNumber")
            lineNumber
        }
    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInput("Day01_test_p1")) == 142)
    check(part2(readInput("Day01_test_p2")) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

enum class Numbers(val number: Int) {
    one(1),
    two(2),
    three(3),
    four(4),
    five(5),
    six(6),
    seven(7),
    eight(8),
    nine(9)
}
