package com.example.jakubaniola.patienttag.TransportObjects;

public enum Sex {
    MALE("Male"),
    FEMALE("Female"),
    UNKNOWN("Unknown");

    private String name;

    Sex(final String name) {
        this.name = name;
    }

    public String getString() {
        return name;
    }
}
