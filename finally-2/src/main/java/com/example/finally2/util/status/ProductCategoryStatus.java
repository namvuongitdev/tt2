package com.example.finally2.util.status;

public enum ProductCategoryStatus {

    ACTIVE,
    STOPWORKING;

    public static ProductCategoryStatus fromValue(String value) {
        for (ProductCategoryStatus status : ProductCategoryStatus.values()) {
            if (status.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No status with value " + value);
    }
}
