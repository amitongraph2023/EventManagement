package com.eventManagement.model;

public enum EventType {
    PHYSICAL_EVENT,
    ONLINE_EVENT;

    public static EventType fromValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        switch (value) {
            case "Physical Event":
                return PHYSICAL_EVENT;
            case "Online Event":
                return ONLINE_EVENT;
            default:
                throw new IllegalArgumentException("Invalid value: " + value);
        }
    }
}

