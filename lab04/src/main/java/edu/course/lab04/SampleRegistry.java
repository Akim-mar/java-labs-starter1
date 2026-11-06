package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;

public class SampleRegistry {
    private final Map<SampleId, DataSample> samples = new HashMap<>();

    public void put(DataSample sample) {
        if (sample == null) throw new IllegalArgumentException("sample must not be null");
        samples.put(sample.getId(), sample);
    }

    // Возвращает null, если записи нет (это зафиксировано в тестах и README)
    public DataSample get(SampleId id) {
        if (id == null) throw new IllegalArgumentException("id must not be null");
        return samples.get(id);
    }

    public boolean contains(SampleId id) {
        if (id == null) throw new IllegalArgumentException("id must not be null");
        return samples.containsKey(id);
    }

    public boolean remove(SampleId id) {
        if (id == null) throw new IllegalArgumentException("id must not be null");
        return samples.remove(id) != null;
    }

    public int size() { return samples.size(); }
}