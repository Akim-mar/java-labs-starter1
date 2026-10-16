package edu.course.lab02;

public class DataSample {
    private final String id;
    private final String label;
    private SampleStatus status;
    private final double[] features;

    // все private, три поля final, потому что после создания не меняются, а status меняется


    public DataSample(String id, String label, SampleStatus status, double[] features) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }
        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("label must not be blank");
        }
        if (status == null) {
            throw new IllegalArgumentException("status must not be null");
        }
        if (features == null || features.length == 0) {
            throw new IllegalArgumentException("features must not be null or empty");
        }
        for (double value : features) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("features must contain only finite numbers");
            }
        }
        this.id = id;
        this.label = label;
        this.status = status;
        this.features = features.clone();
    }

    public void changeStatus(SampleStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("status must not be null");
        }
        this.status = newStatus;
    }

    public boolean isReady() {
        return status == SampleStatus.READY;
    }

    public double meanFeature() {
        double sum = 0;
        for (double value : features) {
            sum += value;
        }
        return sum / features.length;
    }

    public String getId() { return id; }
    public String getLabel() { return label; }
    public SampleStatus getStatus() { return status; }
    public double[] getFeatures() { return features.clone(); }
}