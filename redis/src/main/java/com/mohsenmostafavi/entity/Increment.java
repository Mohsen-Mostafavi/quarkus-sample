package com.mohsenmostafavi.entity;

public class Increment {
    private String key;
    private int value;

    public Increment(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public Increment() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
