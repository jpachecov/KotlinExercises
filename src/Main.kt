import kotlin.math.min

fun Array<Int>.print() {
    for (element in this) {
        print("$element ")
    }
}

fun Int.blockArea(): Int {
   if (this == 0) return 0
   return 2 * (2 * this + 1)
}

fun Array<Array<Int>>.commonFacesAround(i: Int, j: Int): Int {
    val reference = this[i][j]
    val right = this[i][j + 1]
    val left = this[i][j - 1]
    val top = this[i + 1][j]
    val bottom = this [i - 1][j]
    return min(reference, right) + min(reference, left) + min(reference, top) + min(reference, bottom)
}

fun Array<Array<Int>>.printAll() {
    this.forEach {
        it.print()
        println()
    }
    println()
}

fun main(args : Array<String>) {

    val rows = 3
    val columns = 3
    val A = arrayOf(
        arrayOf(1, 3, 4),
        arrayOf(2, 2, 3),
        arrayOf(1, 2, 4)
    )

//    val rows = 3
//    val columns = 3
//    val A = arrayOf(
//        arrayOf(1, 0, 1),
//        arrayOf(0, 1, 0),
//        arrayOf(1, 0, 1)
//    )

//    val rows = 1
//    val columns = 1
//    val A = arrayOf(
//        arrayOf(1)
//    )

    val aPlus = Array(rows + 2){ Array(columns + 2){ 0 }}
    for (i in 0 until rows) {
        for (j in 0 until columns) {
            aPlus[i + 1][j + 1] = A[i][j];
        }
    }

    var surfaceArea = 0
    for (i in 0 until rows) {
        for (j in 0 until columns) {
            val height = aPlus[i + 1][j + 1]
            val area = height.blockArea();
            val commonFaces = aPlus.commonFacesAround(i + 1, j + 1)
            surfaceArea += area - commonFaces
        }
    }
    println()
    A.printAll();
    aPlus.printAll();
    println(surfaceArea)

}