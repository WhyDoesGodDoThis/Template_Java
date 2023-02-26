package core.components;

import java.util.Arrays;

public class NeuralNetwork {
    private Neuron[] inputLayer;
    private Neuron[] hiddenLayer;
    private Neuron outputNeuron;

    public NeuralNetwork(int inputSize, int hiddenSize, int outputSize) {
        inputLayer = new Neuron[inputSize];
        for (int i = 0; i < inputSize; i++) {
            inputLayer[i] = new Neuron(1, 0);
        }

        hiddenLayer = new Neuron[hiddenSize];
        for (int i = 0; i < hiddenSize; i++) {
            hiddenLayer[i] = new Neuron(inputSize, 0);
        }

        outputNeuron = new Neuron(hiddenSize, 0);
    }

    public double[] predict(double[] inputs) {
        double[] hiddenOutputs = new double[hiddenLayer.length];
        for (int i = 0; i < hiddenLayer.length; i++) {
            hiddenOutputs[i] = hiddenLayer[i].process(inputs);
        }

        return new double[] { outputNeuron.process(hiddenOutputs) };
    }

    public void train(double[] inputs, double expectedOutput, double learningRate) {
        double[] hiddenOutputs = new double[hiddenLayer.length];
        for (int i = 0; i < hiddenLayer.length; i++) {
            hiddenOutputs[i] = hiddenLayer[i].process(inputs);
        }

        double predictedOutput = outputNeuron.process(hiddenOutputs);

        double outputError = expectedOutput - predictedOutput;
        double[] hiddenErrors = new double[hiddenLayer.length];
        for (int i = 0; i < hiddenLayer.length; i++) {
            hiddenErrors[i] = outputNeuron.weights[i] * outputError * predictedOutput * (1 - predictedOutput);
            double[] inputErrors = new double[inputLayer.length];
            for (int j = 0; j < inputLayer.length; j++) {
                inputErrors[j] = hiddenLayer[i].weights[j] * hiddenErrors[i] * hiddenOutputs[i]
                        * (1 - hiddenOutputs[i]);
                hiddenLayer[i].weights[j] += learningRate * hiddenErrors[i] * hiddenOutputs[i];
                inputLayer[j].weights[0] += learningRate * inputErrors[j] * inputs[j];
            }
        }

        outputNeuron.setBias(
                outputNeuron.getBias() +
                        (learningRate * outputError * predictedOutput * (1 - predictedOutput)));
        for (int i = 0; i < hiddenLayer.length; i++) {
            hiddenLayer[i].bias += learningRate * hiddenErrors[i] * hiddenOutputs[i] * (1 - hiddenOutputs[i]);
        }
    }

    public void train(double[][] inputs, double[] expectedOutputs, double learningRate, int epochs) {
        for (int i = 0; i < epochs; i++) {
            for (int j = 0; j < inputs.length; j++) {
                train(inputs[j], expectedOutputs[j], learningRate);
            }
        }
    }

    @Override
    public String toString() {
        return "NeuralNetwork{" +
                "inputLayer=" + Arrays.toString(inputLayer) +
                ", hiddenLayer=" + Arrays.toString(hiddenLayer) +
                ", outputNeuron=" + outputNeuron +
                '}';
    }
}