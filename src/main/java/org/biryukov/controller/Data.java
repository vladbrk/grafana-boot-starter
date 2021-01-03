package org.biryukov.controller;

public class Data <V> {

    public Data(String name, V value) {
        this.name = name;
        this.value = value;
    }

    private String name;
    private V value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
