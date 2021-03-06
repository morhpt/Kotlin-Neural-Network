package com.morhpt.nn

/**
 * This class creates a [NeuralNetwork] object
 *
 * @init sets default learning rate and activation function
 */
class NeuralNetwork {

    private var learning_rate = 0.1

    private var input_nodes: Int
    private var hidden_nodes: Int
    private var output_nodes: Int

    private  var weights_ih: Matrix
    private  var weights_ho: Matrix

    private  var bias_h: Matrix
    private  var bias_o: Matrix

    private var activation_function = Activation.sigmoid()

    /**
     * A constructor for [NeuralNetwork] class
     *
     * @param a input nodes
     * @param b hidden nodes
     * @param c output nodes
     * @constructor creates a [NeuralNetwork] object from [a], [b] and [c]
     */
    constructor(a: Int, b: Int, c: Int) {
        input_nodes = a
        hidden_nodes = b
        output_nodes = c

        weights_ih = Matrix(hidden_nodes, input_nodes)
        weights_ho = Matrix(output_nodes, hidden_nodes)
        weights_ih.randomize()
        weights_ho.randomize()

        bias_h = Matrix(hidden_nodes, 1)
        bias_o = Matrix(output_nodes, 1)
        bias_h.randomize()
        bias_o.randomize()
    }

    /**
     * A constructor for [NeuralNetwork] class
     *
     * @param a [NeuralNetwork] object
     * @constructor creates a NeuralNetwork object from [a] itself
     */
    constructor(a: NeuralNetwork) {
        input_nodes = a.input_nodes
        hidden_nodes = a.hidden_nodes
        output_nodes = a.output_nodes

        weights_ih = a.weights_ih.copy()
        weights_ho = a.weights_ho.copy()

        bias_h = a.bias_h.copy()
        bias_o = a.bias_o.copy()
    }

    init {
        setLearningRate()
        setActivationFunction()
    }

    /**
     * This function predicts the output
     *
     * @param i_array input that will be guessed
     * @return guessed [Double] array
     */
    fun predict(i_array: Array<Number>): Array<Double> {

        val input_array = Array(i_array.size, { i -> i_array[i].toString().toDouble() })

        val inputs = Matrix.fromArray(input_array)

        val hidden = Matrix.multiply(weights_ih, inputs)

        hidden.add(bias_h)
        hidden.map(activation_function.func)

        val output = Matrix.multiply(weights_ho, hidden)

        output.add(bias_o)
        output.map(activation_function.func)

        return output.toArray()
    }

    /**
     * This function sets learning rate
     *
     * @default sets learning rate default from [learning_rate]
     * @param lr the learning rate
     */
    fun setLearningRate(lr: Double = learning_rate) {
        learning_rate = lr
    }

    /**
     * This function sets activation function
     *
     * @default sets activation function default from [activation_function]
     * @param func the activation function
     */
    fun setActivationFunction(func: Activation = activation_function) {
        activation_function = func
    }

    /**
     * This function train itself from provided inputs and outputs
     *
     * @param i_array inputs as [Array] of numbers]
     * @param t_array outputs as [Array] of numbers]
     */
    fun train(i_array: Array<Number>, t_array: Array<Number>) {

        val input_array = Array(i_array.size, { i -> i_array[i].toString().toDouble() })
        val target_array = Array(t_array.size, { i -> t_array[i].toString().toDouble() })

        val inputs = Matrix.fromArray(input_array)
        val hidden = Matrix.multiply(weights_ih, inputs)

        hidden.add(bias_h)
        hidden.map(activation_function.func)

        val outputs = Matrix.multiply(weights_ho, hidden)

        outputs.add(bias_o)
        outputs.map(activation_function.func)

        val targets = Matrix.fromArray(target_array)
        val output_errors = Matrix.subtract(targets, outputs)
        val gradients = Matrix.map(outputs, activation_function.dfunc)

        gradients.multiply(output_errors)
        gradients.multiply(learning_rate)

        val hidden_T = Matrix.transpose(hidden)
        val weight_ho_deltas = Matrix.multiply(gradients, hidden_T)

        weights_ho.add(weight_ho_deltas)
        bias_o.add(gradients)

        val who_t = Matrix.transpose(weights_ho)
        val hidden_errors = Matrix.multiply(who_t, output_errors)
        val hidden_gradient = Matrix.map(hidden, activation_function.dfunc)

        hidden_gradient.multiply(hidden_errors)
        hidden_gradient.multiply(learning_rate)

        val inputs_T = Matrix.transpose(inputs)
        val weights_ih_deltas = Matrix.multiply(hidden_gradient, inputs_T)

        weights_ih.add(weights_ih_deltas)
        bias_h.add(hidden_gradient)
    }

    /**
     * This function copies itself
     *
     * @return [NeuralNetwork] object
     */
    fun copy() = NeuralNetwork(this)

    companion object {
        /**
         * This function allows access to [Matrix] class
         *
         * @param rows rows of the [Matrix]
         * @param cols columns of the [Matrix]
         * @return [Matrix] object created from [cols] and [rows]
         */
        fun matrix(rows: Int, cols: Int) = Matrix(rows, cols)
    }
}