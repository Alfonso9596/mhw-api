package com.mhw.mhwapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MonsterType {
    FANGED_WYVERN("Fanged Wyvern", "Reisszahn-Wyvern"),
    BIRD_WYVERN("Bird Wyvern", "Vogel-Wyvern"),
    BRUTE_WYVERN("Brute Wyvern", "Kampf-Wyvern"),
    PISCINE_WYVERN("Piscine Wyvern", "Fisch-Wyvern"),
    FLYING_WYVERN("Flying Wyvern", "Flug-Wyvern"),
    ELDER_DRAGON("Elder Dragon", "Drachenältester"),
    RELICT("Relict", "Relikt"),
    FANGED_BEAST("Fanged Beast", "Reisszahn-Bestie");

    private final String englishValue;
    private final String germanValue;

    MonsterType(String englishValue, String germanValue) {
        this.englishValue = englishValue;
        this.germanValue= germanValue;
    }

    @JsonValue
    public String getEnglishValue() {
        return englishValue;
    }

    @JsonValue
    public String getGermanValue() {
        return germanValue;
    }
}
