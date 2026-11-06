package edu.course.lab03;

public class PercentDiscount implements DiscountPolicy {
    private final double percent;

    public PercentDiscount(double percent) {
        // запись через !(...) отсекает и NaN: любое сравнение с NaN ложно
        if (!(percent >= 0 && percent <= 100)) {
            throw new IllegalArgumentException("percent must be in range 0..100");
        }
        this.percent = percent;
    }

    @Override
    public double apply(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("price must not be negative");
        }
        return price * (100 - percent) / 100;
    }
}