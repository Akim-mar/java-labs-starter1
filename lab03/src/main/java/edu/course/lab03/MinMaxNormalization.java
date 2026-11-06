package edu.course.lab03;

public class MinMaxNormalization implements NormalizationStrategy {

    @Override
    public double[] normalize(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("values must not be null or empty");
        }
        double min = values[0];
        double max = values[0];
        for (double value : values) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("values must be finite numbers");
            }
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        double range = max - min;
        double[] result = new double[values.length]; // новый массив: вход не меняется
        if (range == 0) {
            return result; // все значения равны: делить на ноль нельзя, возвращаем нули
        }
        for (int i = 0; i < values.length; i++) {
            result[i] = (values[i] - min) / range;
        }
        return result;
    }
}