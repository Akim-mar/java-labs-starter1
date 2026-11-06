package edu.course.lab04;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SampleIdTest {

    @Test
    void sameFieldsMeanEqualAndSameHash() {
        SampleId a = new SampleId("cats", 1);
        SampleId b = new SampleId("cats", 1);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void differentNumberOrDatasetMeansNotEqual() {
        assertNotEquals(new SampleId("cats", 1), new SampleId("cats", 2));
        assertNotEquals(new SampleId("cats", 1), new SampleId("dogs", 1));
    }

    @Test
    void equalsWithNullAndOtherTypeIsFalse() {
        SampleId a = new SampleId("cats", 1);
        assertFalse(a.equals(null));
        assertFalse(a.equals("cats#1"));
    }

    @Test
    void invalidArgumentsAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> new SampleId(null, 1));
        assertThrows(IllegalArgumentException.class, () -> new SampleId("", 1));
        assertThrows(IllegalArgumentException.class, () -> new SampleId("cats", -1));
    }
}