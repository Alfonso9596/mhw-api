package com.mhw.mhwapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum QuestCategory {
    ARENA("arena"),
    ASSIGNED("assigned"),
    CHALLENGE("challenge"),
    EVENT("optional"),
    SPECIAL("special");

    private final String value;

    QuestCategory(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
