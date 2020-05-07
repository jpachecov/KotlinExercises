import java.io.File



fun produceExerciseInserts() {
    val lines = arrayListOf<String>()
    File("inputsExercises/input-exercises.txt").forEachLine {
        lines.add(it)
    }

    val codigoDoc = lines[0]
    val series = lines[1]
    val reps = lines[2]
    val groupId = 7
    val data = lines.subList(3, lines.size)
    val inserts = data.map {
        "insert into exercise (groupId, body, type, repetitions, series)\n" +
                "    value (${groupId},\n" +
                "           '${it}',\n" +
                "           'main',\n" +
                "           '${reps}',\n" +
                "           '${series}'); \n\n"
    }.reduce{ acc, string -> acc + string}

    val fileName = "insertsExercises/inserts-exercise-doc-${codigoDoc}-${groupId}.sql"
    File(fileName).writeText("# Plan $codigoDoc \n\n".plus(inserts))
}


fun produceDietInserts() {
    for (k in 1 until 13) {

        println("D${k}")

        val lines = arrayListOf<String>()
        File("inputsDiets/input-diet-D${k}").forEachLine {
            lines.add(it)
        }

        val codigoDoc = lines[0]
        val dietId = k
        val markers = mapOf("DESAYUNO" to 0, "COLACION" to 1, "COMIDA" to 2, "COLACION2" to 3, "CENA" to 4, "COLACION3" to 5)
        val data = lines.subList(1, lines.size)
        val inserts = arrayListOf<String>()

        for (i in 0 until data.size) {
            if (markers.containsKey(data[i].trim())) {
                val section = markers.get(data[i].trim())
                println(data[i].trim())

                var j = i +1
                val options = arrayListOf<String>()
                while (j < data.size && !markers.keys.contains(data[j].trim())) {
                    options.add(data[j])
                    j += 1
                }

                for (z in 0 until options.size) {

                    val idx = options[z].indexOf(": ")
                    if (idx > -1) {
                        val rest = options[z].subSequence(idx + 2, options[z].length)
                        val choice = z + 1
                        val body = rest
                        println("option: ${z}, :${rest}")
                        inserts.add(
                            "INSERT INTO food(dietId, section, choice, body)\n" +
                            "       VALUES (${dietId},\n" +
                            "              ${section},\n" +
                            "              ${choice},\n" +
                            "              '${body}'\n" +
                            "              ); \n\n"
                        )
                    }
                }
            }
        }

        val insertString = inserts.reduce{ acc, string -> acc + string}
        val fileName = "insertsDiets/inserts-diets-doc-${codigoDoc}.sql"
        File(fileName).writeText("# Plan $codigoDoc \n\n".plus(insertString))

    }
}

fun main() {
//    produceExerciseInserts()
    produceDietInserts()
}