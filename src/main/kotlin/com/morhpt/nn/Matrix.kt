package com.morhpt.nn

/**
 * This class creates Matrix
 *
 * @param rows rows of the [Matrix]
 * @param cols coluumns of the [Matrix]
 * @constructor creates [Matrix.data]
 */
class Matrix(val rows: Int, val cols: Int) {

    private inline fun <reified T> matrix2d(height: Int, width: Int, init: (Int, Int) -> Array<T>) = Array<Array<T>>(height, { row -> init(row, width) })

    var data = matrix2d(rows, cols, { _: Int, width: Int -> Array(width) { 0.0 } })

    /**
     * This function creates random number between 1 and -1
     *
     * @return [Matrix] object with random numbers in it
     */
    fun randomize(): Matrix = this@Matrix.data.map { _, _, _ -> Math.random() * 2 - 1 }

    /**
     * This function converts [Matrix] object to [Array]
     *
     * @return array of the [Matrix] object
     */
    fun toArray(): Array<Double> {
        val array = Array<Double>(rows * cols, { 0.0 })

        for (x in 0 until rows)
            for (y in 0 until cols)
                array[x + y] = data[x][y]

        return array
    }

    /**
     * This function copies itself
     *
     * @return [Matrix] object
     */
    fun copy(): Matrix {
        val m = Matrix(rows, cols)
        m.data = data.copyOf()
        return m
    }

    /**
     * This function adds number / [Matrix]
     *
     * @param n number that will be added
     * @param n [Matrix] that will be added
     * @return new [Matrix] with the number / [Matrix] added
     */
    fun add(n: Any): Matrix? {
        if (n is Matrix) {
            if (rows != n.rows || cols != n.cols)
                throw Exception("Columns and Rows of A must match Columns and Rows of B.")

            return this@Matrix.data.map { e, i, j -> e + n.data[i][j] }
        }

        return when (n) {
            is Double -> this@Matrix.data.map { e, _, _ -> e + n }
            is Float -> this@Matrix.data.map { e, _, _ -> e + n }
            is Long -> this@Matrix.data.map { e, _, _ -> e + n }
            is Short -> this@Matrix.data.map { e, _, _ -> e + n }
            is Int -> this@Matrix.data.map { e, _, _ -> e + n }
            else -> this@Matrix.data.map { e, _, _ -> e + (n.toString().toInt()) }
        }
    }

    /**
     * This function multiplies number / [Matrix]
     *
     * @param n number that will be multiplied
     * @param n [Matrix] that will be multiplied
     * @return new [Matrix] with the number / [Matrix] multiplied
     */
    fun multiply(n: Any): Matrix {
        if (n is Matrix) {
            if (rows != n.rows || cols != n.cols)
                throw Exception("Columns and Rows of A must match Columns and Rows of B.")

            return this@Matrix.data.map { e, i, j -> e * n.data[i][j] }
        }

        return when (n) {
            is Double -> this@Matrix.data.map { e, _, _ -> e * n }
            is Float -> this@Matrix.data.map { e, _, _ -> e * n }
            is Long -> this@Matrix.data.map { e, _, _ -> e * n }
            is Short -> this@Matrix.data.map { e, _, _ -> e * n }
            is Int -> this@Matrix.data.map { e, _, _ -> e * n }
            else -> this@Matrix.data.map { e, _, _ -> e * (n.toString().toInt()) }
        }
    }

    companion object {
        /**
         * This function creates [Matrix] object from array
         *
         * @param arr array that will be turned into [Matrix]
         * @return new [Matrix] that created from [arr]
         */
        fun fromArray(arr: Array<Double>): Matrix = map(Matrix(arr.size, 1)) { _, i, _ -> arr[i] }

        /**
         * This function transpose the [Matrix]
         *
         * @param matrix the [Matrix] object that will be transposed
         * @return transposed [Matrix] object
         */
        fun transpose(matrix: Matrix): Matrix = Matrix(matrix.cols, matrix.rows).map { _, i, j -> matrix.data[j][i] }

        /**
         * This function subtracts the 2 [Matrix]s from each other
         *
         * @param a [Matrix] object
         * @param b [Matrix] object
         * @return subtracted [Matrix] object
         */
        fun subtract(a: Matrix?, b: Matrix?): Matrix {
            if (a == null || b == null)
                throw NullPointerException("Matrix cannot be null")

            if (a.rows != b.rows || a.cols != b.cols)
                throw Exception("Columns of A must match rows of B.")

            return map(a) { _, i, j -> a.data[i][j] - b.data[i][j] }
        }

        /**
         * This function multiplies the 2 [Matrix]s from each other
         *
         * @param a [Matrix] object
         * @param b [Matrix] object
         * @return multiplied [Matrix] object
         */
        fun multiply(a: Matrix?, b: Matrix?): Matrix {
            if (a == null || b == null)
                throw NullPointerException("Matrix cannot be null")

            if (a.cols != b.rows)
                throw Exception("Columns of A must match rows of B.")

            return Matrix(a.rows, b.cols).map { _, i, j ->
                var sum = 0.0

                for (k in 0 .. a.cols - 1)
                    sum += a.data[i][k] * b.data[k][j]
                sum
            }
        }

        /**
         * This function maps the [Matrix.data] arrays from given function
         *
         * @param matrix a [Matrix] object
         * @param combine the function that will be using to map
         * @return new [Matrix] object that mapped
         */
        fun map(matrix: Matrix, combine: (value: Double, i: Int, j: Int) -> Double): Matrix {
            for (x in 0 until matrix.rows)
                for (y in 0 until matrix.cols)
                    matrix.data[x][y] = combine(matrix.data[x][y], x, y)
            return matrix
        }
    }

    /**
     * This function maps array from given function
     *
     * @param combine the function that will be using to map
     * @return new [Matrix] object that mapped
     */
    fun <T> Array<T>.map(combine: (value: Double, i: Int, j: Int) -> Double): Matrix {
        for (x in 0 until rows)
            for (y in 0 until cols)
                data[x][y] = combine(data[x][y], x, y)
        return this@Matrix
    }

    /**
     * This function maps the [Matrix.data] arrays from given function
     *
     * @param combine the function that will be using to map
     * @return new [Matrix] object that mapped
     */
    fun map(combine: (value: Double, i: Int, j: Int) -> Double): Matrix {
        for (x in 0 until rows)
            for (y in 0 until cols)
                data[x][y] = combine(data[x][y], x, y)
        return this@Matrix
    }
}
