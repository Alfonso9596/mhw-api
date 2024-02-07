package com.mhw.mhwapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum QuestType {
    ASSIGNMENT("assignment"),
    CAPTURE("capture"),
    DELIVER("deliver"),
    HUNT("hunt");

    private final String value;

    QuestType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
