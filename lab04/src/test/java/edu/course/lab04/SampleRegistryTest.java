package edu.course.lab04;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SampleRegistryTest {

    private static DataSample sample(String dataset, int n, String label) {
        return new DataSample(new SampleId(dataset, n), label, "camera", 3, SampleStatus.RAW);
    }

    @Test
    void equalKeyFindsStoredSample() {
        SampleRegistry registry = new SampleRegistry();
        registry.put(sample("cats", 1, "cat"));
        DataSample found = registry.get(new SampleId("cats", 1)); // другой объект, равный ключ
        assertNotNull(found);
        assertEquals("cat", found.getLabel());
    }

    @Test
    void putWithEqualKeyReplacesValue() {
        SampleRegistry registry = new SampleRegistry();
        registry.put(sample("cats", 1, "old"));
        registry.put(sample("cats", 1, "new"));
        assertEquals(1, registry.size());
        assertEquals("new", registry.get(new SampleId("cats", 1)).getLabel());
    }

    @Test
    void missingKeyReturnsNull() {
        SampleRegistry registry = new SampleRegistry();
        assertNull(registry.get(new SampleId("cats", 99)));
        assertFalse(registry.contains(new SampleId("cats", 99)));
    }

    @Test
    void removeWorksByLogicalEquality() {
        SampleRegistry registry = new SampleRegistry();
        registry.put(sample("cats", 1, "cat"));
        assertTrue(registry.remove(new SampleId("cats", 1)));
        assertFalse(registry.remove(new SampleId("cats", 1)));
        assertEquals(0, registry.size());
    }

    @Test
    void nullArgumentsAreRejected() {
        SampleRegistry registry = new SampleRegistry();
        assertThrows(IllegalArgumentException.class, () -> registry.put(null));
        assertThrows(IllegalArgumentException.class, () -> registry.get(null));
    }
}