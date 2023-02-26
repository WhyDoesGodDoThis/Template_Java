package core;

import core.components.NeuralNetwork;

public class Main {
        public static void main(String[] args) {
                double[][] in = {
                                { 1 },
                                { 2 },
                                { 3 },
                                { 4 },
                                { 5 }
                };
                double[] out = {
                                1,
                                1.75,
                                2.5,
                                3,
                                3.5
                };
                double[] post = {
                                6
                };
                NeuralNetwork network = new NeuralNetwork(1, 5, 1);
                network.train(in, out, 0.12, 100);
                System.out.println(network.predict(post));
        }
}