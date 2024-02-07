package com.mhw.mhwapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MonsterSize {
    SMALL("small"),
    LARGE("large");

    private final String value;

    MonsterSize(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
