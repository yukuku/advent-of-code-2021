fun main() {
    fun part1(input: List<String>): Int {
        var g = 0
        var e = 0
        for (i in 0 until input[0].length) {
            val satu = input.count { it[i] == '1' }
            g *= 2
            e *= 2
            if (satu > input.size / 2) {
                g += 1
            } else {
                e += 1
            }
        }
        return g * e
    }

    fun bc(most: Boolean, pos: Int, m: List<String>): List<String> {
        val satu = m.count { it[pos] == '1' }
        val bkeep = if (most) {
            when {
                satu > m.size / 2.0 -> '1'
                satu < m.size / 2.0 -> '0'
                else -> '1'
            }
        } else {
            when {
                satu < m.size / 2.0 -> '1'
                satu > m.size / 2.0 -> '0'
                else -> '0'
            }
        }
        return m.filter { it[pos] == bkeep }
    }

    fun part2(input: List<String>): Int {
        var o = ""
        var m = input.toList()
        for (pos in 0 until input[0].length) {
            m = bc(true, pos, m)
            if (m.size == 1) {
                o = m[0]
                break
            }
        }
        var c = ""
        m = input.toList()
        for (pos in 0 until input[0].length) {
            m = bc(false, pos, m)
            if (m.size == 1) {
                c = m[0]
                break
            }
        }
        return o.toInt(2) * c.toInt(2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
