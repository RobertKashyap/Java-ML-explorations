import static org.junit.Assert.*;

import org.junit.Test;

import com.javaml.perceptrons.PlanarPerceptron;

public class PlanarPerceptronTest {

    @Test
    public void testCalculateOutput() {
        float[] weights = { 0.5f, 0.5f, 0.5f };

        int output1 = PlanarPerceptron.calculateOutput(weights, 0.5f, 0.5f);
        assertEquals(1, output1);

        int output2 = PlanarPerceptron.calculateOutput(weights, -0.521978f, -3.193277f);
        assertEquals(-1, output2);

        int output3 = PlanarPerceptron.calculateOutput(weights, 0f, 0f);
        assertEquals(1, output3);
    }

    @Test
        public void testTrain() {
        // Train model
        PlanarPerceptron.main(null);

        // Assert weights trained
        float[] weights = PlanarPerceptron.getWeights();
        assertNotEquals(0f, weights[0]);
        assertNotEquals(0f, weights[1]);
        assertNotEquals(0f, weights[2]);

        // Assert low error
        float error = PlanarPerceptron.getGlobalError();
        assertTrue(error < 0.1f);
    }

}
