package com.mhw.mhwapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MonsterType {
    FANGED_WYVERN("Fanged Wyvern"),
    BIRD_WYVERN("Bird Wyvern"),
    BRUTE_WYVERN("Brute Wyvern"),
    PISCINE_WYVERN("Piscine Wyvern"),
    FLYING_WYVERN("Flying Wyvern"),
    ELDER_DRAGON("Elder Dragon"),
    RELICT("Relict"),
    FANGED_BEAST("Fanged Beast");

    private final String value;

    MonsterType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
