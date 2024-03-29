package com.example.twitterAnalog.domain.constant;

public enum Sort {

    RANDOM("RAND()"),
    TIME_INSERT("phrase.time_insert DESC");

    private final String value;

    Sort(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
