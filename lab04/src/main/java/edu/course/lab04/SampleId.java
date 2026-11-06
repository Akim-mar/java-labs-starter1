package edu.course.lab04;

import java.util.Objects;

public final class SampleId {
    private final String datasetName;
    private final int number;

    public SampleId(String datasetName, int number) {
        if (datasetName == null || datasetName.isBlank()) {
            throw new IllegalArgumentException("datasetName must not be blank");
        }
        if (number < 0) {
            throw new IllegalArgumentException("number must be >= 0");
        }
        this.datasetName = datasetName;
        this.number = number;
    }

    public String getDatasetName() { return datasetName; }
    public int getNumber() { return number; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SampleId other = (SampleId) o;
        return number == other.number && datasetName.equals(other.datasetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datasetName, number);
    }

    @Override
    public String toString() {
        return datasetName + "#" + number;
    }
}