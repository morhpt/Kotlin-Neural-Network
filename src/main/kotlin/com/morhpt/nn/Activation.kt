package com.morhpt.nn

/**
 * This class creates an activation and de-activation functions
 *
 * @property func the activation function.
 * @property dfunc the de-activation function.
 * @constructor creates activation and de-activation functions from [func] and [dfunc]
 */
class Activation(val func: (x: Double, i: Int, j: Int) -> Double, val dfunc: (y: Double, i: Int, j: Int) -> Double) {

    companion object {
        /**
         * @see [https://en.wikipedia.org/wiki/Sigmoid_function]
         * @return sigmoid activation function
         */
        fun sigmoid() = Activation({ x, _, _ -> 1 / (1 + Math.exp(-x)) }, { y, _, _ -> y * (1 - y) })

        /**
         * @see [https://en.wikipedia.org/wiki/Hyperbolic_function]
         * @return tanh activation function
         */
        fun tanh() = Activation({ x, _, _ -> Math.tanh(x) }, { y, _, _ -> 1 - (y * y) })
    }
}