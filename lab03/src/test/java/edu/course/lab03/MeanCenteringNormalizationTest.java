package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MeanCenteringNormalizationTest {

    @Test
    void subtractsMean() {
        double[] result = new MeanCenteringNormalization().normalize(new double[]{1.0, 2.0, 3.0});
        assertArrayEquals(new double[]{-1.0, 0.0, 1.0}, result, 1e-9);
    }

    @Test
    void meanOfResultIsZero() {
        double[] result = new MeanCenteringNormalization().normalize(new double[]{4.0, 10.0, 1.0, 7.0});
        double sum = 0;
        for (double value : result) {
            sum += value;
        }
        assertEquals(0.0, sum, 1e-9);
    }

    @Test
    void inputArrayIsNotChanged() {
        double[] input = {10.0, 20.0};
        new MeanCenteringNormalization().normalize(input);
        assertArrayEquals(new double[]{10.0, 20.0}, input, 0.0);
    }

    @Test
    void nanIsRejected() {
        assertThrows(IllegalArgumentException.class,
            () -> new MeanCenteringNormalization().normalize(new double[]{1.0, Double.NaN}));
    }
}