package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FeatureProcessorTest {

    @Test
    void sameProcessorClassWorksWithDifferentStrategies() {
        double[] vector = {2.0, 4.0, 6.0};
        FeatureProcessor minMax = new FeatureProcessor(new MinMaxNormalization());
        FeatureProcessor centered = new FeatureProcessor(new MeanCenteringNormalization());
        assertArrayEquals(new double[]{0.0, 0.5, 1.0}, minMax.process(vector), 1e-9);
        assertArrayEquals(new double[]{-2.0, 0.0, 2.0}, centered.process(vector), 1e-9);
    }

    @Test
    void newStrategyNeedsNoChangesInProcessor() {
        // новая реализация (здесь лямбда) подключается без правок FeatureProcessor
        FeatureProcessor processor = new FeatureProcessor(values -> new double[]{values.length});
        assertArrayEquals(new double[]{3.0}, processor.process(new double[]{1, 2, 3}), 0.0);
    }

    @Test
    void nullStrategyIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new FeatureProcessor(null));
    }
}