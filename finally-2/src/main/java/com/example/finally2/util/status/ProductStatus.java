package com.example.finally2.util.status;

public enum ProductStatus {

    ACTIVE,
    STOPWORKING;

    public static ProductStatus fromValue(String value) {
        for (ProductStatus status : ProductStatus.values()) {
            if (status.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No status with value " + value);
    }
}
