package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinMaxNormalizationTest {

    @Test
    void scalesValuesToRangeZeroOne() {
        double[] result = new MinMaxNormalization().normalize(new double[]{1.0, 2.0, 3.0});
        assertArrayEquals(new double[]{0.0, 0.5, 1.0}, result, 1e-9);
    }

    @Test
    void constantVectorGivesZeros() {
        double[] result = new MinMaxNormalization().normalize(new double[]{5.0, 5.0, 5.0});
        assertArrayEquals(new double[]{0.0, 0.0, 0.0}, result, 1e-9);
    }

    @Test
    void inputArrayIsNotChanged() {
        double[] input = {10.0, 20.0, 30.0};
        new MinMaxNormalization().normalize(input);
        assertArrayEquals(new double[]{10.0, 20.0, 30.0}, input, 0.0);
    }

    @Test
    void emptyAndNullAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> new MinMaxNormalization().normalize(new double[]{}));
        assertThrows(IllegalArgumentException.class, () -> new MinMaxNormalization().normalize(null));
    }
}