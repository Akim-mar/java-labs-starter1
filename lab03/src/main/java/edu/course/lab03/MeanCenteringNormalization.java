package edu.course.lab03;

public class MeanCenteringNormalization implements NormalizationStrategy {

    @Override
    public double[] normalize(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("values must not be null or empty");
        }
        double sum = 0;
        for (double value : values) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("values must be finite numbers");
            }
            sum += value;
        }
        double mean = sum / values.length;
        double[] result = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] - mean;
        }
        return result;
    }
}