fun main() {
    fun part1(input: List<String>): Int {
        var response = 0
        val addition = mapOf(")" to 3, "]" to 57, "}" to 1197, ">" to 25137)
        input.forEach {

            val retour = test_line(it)
            if (retour.first) {
                response += addition[retour.second] ?: 0
            }
        }
        return  response
    }

    fun part2(input: List<String>): Long {
        var compteur:Long = 0
        val addition = mapOf(")" to 1, "]" to 2, "}" to 3, ">" to 4)
        var liste_comp = mutableListOf<Long>()
        input.forEach {

            val retour = test_line(it)
            if (!retour.first) {
                compteur = 0
                for (i in 0 until retour.second.length) {
                    compteur *= 5
                    compteur += addition[retour.second[i].toString()] ?:  0
                }
                liste_comp.add(compteur)
            }
        }
        liste_comp.sort()
        return liste_comp[liste_comp.size / 2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
