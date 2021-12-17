class Stamp(val lineIndex: Int, val operand: Int)

class Hole(val stamps: List<Stamp>)

class Board(val labels: List<Int>) {
    val lines = IntArray(10) { 0 }

    val holes = mutableMapOf<Int, Hole>()

    var lastPoke = -1

    val poked = mutableSetOf<Int>()

    init {
        for (i in 0 until 25) {
            val r = i / 5
            val c = i % 5
            val n = labels[i]
            holes[n] = Hole(buildList {
                add(Stamp(lineIndex = r, operand = 1 shl c))
                add(Stamp(lineIndex = 5 + c, operand = 1 shl r))
            })
        }
    }

    fun poke(n: Int) {
        val hole = holes[n]
        if (hole != null) {
            for (stamp in hole.stamps) {
                lines[stamp.lineIndex] = lines[stamp.lineIndex] or stamp.operand
            }
        }
        poked += n
        lastPoke = n
    }

    fun menang(): Boolean {
        return lines.any { it == 31 }
    }

    fun hitung(): Int {
        return (labels.toSet() - poked).sum() * lastPoke
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val lines = input.filter { it.isNotEmpty() }
        val pokelist = lines[0]
        val boards = buildList {
            for (i in 1 until lines.size step 5) {
                add(Board(lines[i + 0].trim().split(Regex(" +")).map { it.toInt() } +
                        lines[i + 1].trim().split(Regex(" +")).map { it.toInt() } +
                        lines[i + 2].trim().split(Regex(" +")).map { it.toInt() } +
                        lines[i + 3].trim().split(Regex(" +")).map { it.toInt() } +
                        lines[i + 4].trim().split(Regex(" +")).map { it.toInt() }))
            }
        }

        for (poke in pokelist.split(",").map { it.toInt() }) {
            for (board in boards) {
                board.poke(poke)
                if (board.menang()) {
                    return board.hitung()
                }
            }
        }
        error("nobody won")
    }

    fun part2(input: List<String>): Int {
        val lines = input.filter { it.isNotEmpty() }
        val pokelist = lines[0]
        val boards = buildList {
            for (i in 1 until lines.size step 5) {
                add(Board(lines[i + 0].trim().split(Regex(" +")).map { it.toInt() } +
                        lines[i + 1].trim().split(Regex(" +")).map { it.toInt() } +
                        lines[i + 2].trim().split(Regex(" +")).map { it.toInt() } +
                        lines[i + 3].trim().split(Regex(" +")).map { it.toInt() } +
                        lines[i + 4].trim().split(Regex(" +")).map { it.toInt() }))
            }
        }

        var lastHitung = 0
        val alreadyWon = mutableSetOf<Board>()
        for (poke in pokelist.split(",").map { it.toInt() }) {
            for (board in boards) {
                if (board in alreadyWon) continue
                board.poke(poke)
                if (board.menang()) {
                    lastHitung = board.hitung()
                    alreadyWon += board
                }
            }
        }
        return lastHitung
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
