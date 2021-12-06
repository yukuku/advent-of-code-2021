fun main() {
    fun part1(input: List<String>): Int {
        var x = 0
        var y = 0
        for (c in input) {
            val (arah, n) = c.split(" ")
            when (arah) {
                "forward" -> x += n.toInt()
                "down" -> y += n.toInt()
                "up" -> y -= n.toInt()
            }
        }
        return x * y
    }

    fun part2(input: List<String>): Int {
        var x = 0
        var y = 0
        var aim = 0
        for (c in input) {
            val (arah, n) = c.split(" ")
            when (arah) {
                "forward" -> {
                    x += n.toInt()
                    y += aim * n.toInt()
                }
                "down" -> aim += n.toInt()
                "up" -> aim -= n.toInt()
            }
        }
        return x * y
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
