fun main() {
    fun part1(input: List<Int>): Int {
        var res = 0
        for (i in 1 until input.size) {
            if (input[i] > input[i-1]) {
                res++
            }
        }
        return res
    }

    fun part2(input: List<Int>): Int {
        var res = 0
        for (i in 3 until input.size) {
            if (input[i]+input[i-1]+input[i-2] > input[i-1]+input[i-2]+input[i-3]) {
                res++
            }
        }
        return res
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readIntInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readIntInput("Day01")
    println(part1(input))
    println(part2(input))
}
