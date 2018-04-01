package com.morhpt.nn

 class Activation(val func: (x: Double, i: Int, j: Int) -> Double, val dfunc: (y: Double, i: Int, j: Int) -> Double) {


    companion object {
        fun sigmoid() = Activation({ x, _, _ -> 1 / (1 + Math.exp(-x)) }, { y, _, _ -> y * (1 - y) })

        fun tanh() = Activation({ x, _, _ -> Math.tanh(x) }, { y, _, _ -> 1 - (y * y) })
    }
}