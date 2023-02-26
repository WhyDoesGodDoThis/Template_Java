package core.components;

import core.utils.MathUtil;
import java.util.Objects;

/*
 *
 * 
 * @author aheitkamp
 */
public class Neuron {
    protected double[] weights;
    protected double bias;

    public Neuron(int numberOfInputs, double bias) {
        this.bias = bias;
        this.weights = new double[numberOfInputs];
    }

    public Neuron(int numberOfInputs, double bias, double[] weights) {
        this(numberOfInputs, bias);
        this.weights = weights;
    }

    public double process(double[] inputs) {
        double output = bias;
        for (int i = 0; i < weights.length; i++) {
            output += weights[i] * inputs[i];
        }
        MathUtil.sigmoid(output);

        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Neuron)) {
            return false;
        }
        Neuron neuron = (Neuron) o;
        return Objects.equals(weights, neuron.weights) && bias == neuron.bias;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weights, bias);
    }

    @Override
    public String toString() {
        return "{" +
                " weights='" + weights + "'" +
                ", bias='" + bias + "'" +
                "}";
    }

    @Override
    public Neuron clone() {
        return new Neuron(weights.length, bias, weights);
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }
}
