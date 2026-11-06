package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    @Test
    void noDiscountReturnsOriginalPrice() {
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        assertEquals(100.0, calculator.finalPrice(100.0), 1e-9);
    }

    @Test
    void percentDiscountReducesPrice() {
        PriceCalculator calculator = new PriceCalculator(new PercentDiscount(20));
        assertEquals(80.0, calculator.finalPrice(100.0), 1e-9);
    }

    @Test
    void policyCanBeReplacedWithoutChangingCalculator() {
        PriceCalculator withoutDiscount = new PriceCalculator(new NoDiscount());
        PriceCalculator withDiscount = new PriceCalculator(new PercentDiscount(50));
        assertEquals(200.0, withoutDiscount.finalPrice(200.0), 1e-9);
        assertEquals(100.0, withDiscount.finalPrice(200.0), 1e-9);
    }

    @Test
    void negativePriceIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new NoDiscount().apply(-1));
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(10).apply(-1));
    }

    @Test
    void percentOutOfRangeIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(-0.1));
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(100.1));
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(Double.NaN));
    }

    @Test
    void borderPercentsAreAllowed() {
        assertEquals(50.0, new PercentDiscount(0).apply(50.0), 1e-9);
        assertEquals(0.0, new PercentDiscount(100).apply(50.0), 1e-9);
    }

    @Test
    void nullPolicyIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new PriceCalculator(null));
    }
}