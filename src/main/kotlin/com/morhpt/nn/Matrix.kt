package com.morhpt.nn

class Matrix(val rows: Int, val cols: Int) {
    private inline fun <reified T> matrix2d(height: Int, width: Int, init: (Int, Int) -> Array<T>) = Array<Array<T>>(height, { row -> init(row, width) })

    var data = matrix2d(rows, cols, { _: Int, width: Int -> Array(width) { 0.0 } })

    fun randomize(): Matrix = this@Matrix.data.map { _, _, _ -> Math.random() * 2 - 1 }

    fun toArray(): Array<Double> {
        val array = Array<Double>(rows * cols, { 0.0 })

        for (x in 0 until rows)
            for (y in 0 until cols)
                array[x + y] = data[x][y]

        return array
    }

    fun copy(): Matrix {
        val m = Matrix(rows, cols)
        m.data = data.copyOf()
        return m
    }

    fun add(n: Any): Matrix? {
        if (n is Matrix) {
            if (rows != n.rows || cols != n.cols) {
                Throwable("Columns and Rows of A must match Columns and Rows of B.")
                return null
            }

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
        fun fromArray(arr: Array<Double>): Matrix = map(Matrix(arr.size, 1)) { _, i, _ -> arr[i] }

        fun transpose(matrix: Matrix): Matrix = Matrix(matrix.cols, matrix.rows).map { _, i, j -> matrix.data[j][i] }

        fun subtract(a: Matrix?, b: Matrix?): Matrix {
            if (a == null || b == null)
                throw NullPointerException("Matrix cannot be null")

            if (a.cols != b.rows)
                throw Exception("Columns of A must match rows of B.")

            return map(a) { _, i, j -> a.data[i][j] - b.data[i][j] }
        }

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

        fun map(matrix: Matrix, combine: (value: Double, i: Int, j: Int) -> Double): Matrix {
            for (x in 0 until matrix.rows)
                for (y in 0 until matrix.cols)
                    matrix.data[x][y] = combine(matrix.data[x][y], x, y)
            return matrix
        }
    }

    fun <T> Array<T>.map(combine: (value: Double, i: Int, j: Int) -> Double): Matrix {
        for (x in 0 until rows)
            for (y in 0 until cols)
                data[x][y] = combine(data[x][y], x, y)
        return this@Matrix
    }

    fun map(combine: (value: Double, i: Int, j: Int) -> Double): Matrix {
        for (x in 0 until rows)
            for (y in 0 until cols)
                data[x][y] = combine(data[x][y], x, y)
        return this@Matrix
    }
}
