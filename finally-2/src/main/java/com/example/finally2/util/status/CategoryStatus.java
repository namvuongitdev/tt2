package com.example.finally2.util.status;

public enum CategoryStatus {

    ACTIVE,
    STOPWORKING;

    public static CategoryStatus fromValue(String value) {
        for (CategoryStatus status : CategoryStatus.values()) {
            if (status.equals(value)) {

                return status;
            }
        }
        throw new IllegalArgumentException("No status with value " + value);
    }
}
