package com.javaml.perceptrons;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlanarPerceptron {
    private static final float LEARNING_RATE = 0.1f;
    private static final int MAX_ITERATION = 100;

    float[] x = new float[208];
    float[] y = new float[208];
    static float[] weights = new float[3];
    float localError;
    static float globalError;
    int[] outputs = new int[208];
    int patternCount = 0;
    int i;
    int p;
    int iteration;
    int output;

    /**
     * Generates a random float between 0 and 1.
     */
    private static float randomFloat() {
        return (float) Math.random();
    }

    /**
     * Calculates the output value (-1 or 1) for the given input coordinates (x, y)
     * using the provided weight values. It computes the weighted sum of the inputs
     * and thresholds it to -1 or 1. This implements the sign function for
     * calculating the output of a perceptron node.
     */
    public static int calculateOutput(float[] weights, float x, float y) {
        float sum = x * weights[0] + y * weights[1] + weights[2];
        return (sum >= 0) ? 1 : -1;
    }

    /**
     * Trains a planar perceptron model on the provided 2D input data.
     * Initializes random weights, trains the model for a maximum number of
     * iterations
     * or until global error is 0, and prints the final decision boundary equation.
     */
    public static void main(String[] args) {

        float[] x = new float[208];
        float[] y = new float[208];
        float[] weights = new float[3];
        float localError;
        float globalError;
        int[] outputs = new int[208];
        int patternCount = 0;
        int i;
        int p;
        int iteration;
        int output;

        try (Scanner scanner = new Scanner(new File("approaches/src/main/java/com/javaml/perceptrons/test1.txt"))) {
            i = 0;
            while (scanner.hasNextFloat()) {
                x[i] = scanner.nextFloat();
                y[i] = scanner.nextFloat();
                outputs[i] = scanner.nextInt();
                if (outputs[i] == 0) {
                    outputs[i] = -1;
                }
                i++;
            }
            patternCount = i;
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open file.");
            System.exit(1);
        }

        weights[0] = randomFloat();
        weights[1] = randomFloat();
        weights[2] = randomFloat();

        iteration = 0;
        do {
            iteration++;
            globalError = 0;
            for (p = 0; p < patternCount; p++) {
                output = calculateOutput(weights, x[p], y[p]);

                localError = outputs[p] - output;
                weights[0] += LEARNING_RATE * localError * x[p];
                weights[1] += LEARNING_RATE * localError * y[p];
                weights[2] += LEARNING_RATE * localError;

                globalError += (localError * localError);
            }

            System.out.printf("Iteration %d : RMSE = %.4f\n", iteration, Math.sqrt(globalError / patternCount));
        } while (globalError != 0 && iteration <= MAX_ITERATION);

        System.out.printf("\nDecision boundary (line) equation: %.2f*x + %.2f*y + %.2f = 0\n", weights[0], weights[1],
                weights[2]);
    }

    public static float[] getWeights() {
        return weights;
    }

    public static float getGlobalError() {
        return globalError;
    }

}
/*
 * credits of test1.txt:
 * https://www.kaggle.com/c/digit-recognizer/data &
 * https://github.com/RichardKnop/ansi-c-perceptron
 */