package com.morhpt.nn

class NeuralNetworkTest extends groovy.util.GroovyTestCase {
    void testPredict() {
        def nn = new NeuralNetwork(2, 4, 1)

        Number[] testData = [0, 1]

        assert nn.predict(testData) instanceof Double[]
        assert nn.predict(testData)[0] instanceof Double
    }

    class Ndata {
        Number[] inputs = []
        Number[] outputs = []

        Ndata(inputs, outputs) {
            this.inputs = inputs
            this.outputs = outputs
        }
    }

    void testTrain() {
        def nn = new NeuralNetwork(2, 4, 1)

        List<Ndata> testingData = [
                new Ndata([0,0], [0]),
                new Ndata([1,1], [0]),
                new Ndata([1,0], [1]),
                new Ndata([0,1], [1])
        ]

        Random rand = new Random()

        for (int i = 0; i < 50000; i++) {
            def randomNumber = rand.nextInt(3+1)
            def data = testingData[randomNumber]
            nn.train(data.inputs, data.outputs)
        }

        assert nn.predict(testingData[0].inputs)[0] < (0.2 as Double)
        assert nn.predict(testingData[1].inputs)[0] < (0.2 as Double)
        assert nn.predict(testingData[2].inputs)[0] > (0.8 as Double)
        assert nn.predict(testingData[3].inputs)[0] > (0.8 as Double)
    }
}
