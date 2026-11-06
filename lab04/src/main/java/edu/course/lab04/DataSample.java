package edu.course.lab04;

public final class DataSample {
    private final SampleId id;
    private final String label;
    private final String source;
    private final int featureCount;
    private final SampleStatus status;

    public DataSample(SampleId id, String label, String source, int featureCount, SampleStatus status) {
        if (id == null) throw new IllegalArgumentException("id must not be null");
        if (label == null || label.isBlank()) throw new IllegalArgumentException("label must not be blank");
        if (source == null || source.isBlank()) throw new IllegalArgumentException("source must not be blank");
        if (featureCount <= 0) throw new IllegalArgumentException("featureCount must be positive");
        if (status == null) throw new IllegalArgumentException("status must not be null");
        this.id = id;
        this.label = label;
        this.source = source;
        this.featureCount = featureCount;
        this.status = status;
    }

    public SampleId getId() { return id; }
    public String getLabel() { return label; }
    public String getSource() { return source; }
    public int getFeatureCount() { return featureCount; }
    public SampleStatus getStatus() { return status; }
}