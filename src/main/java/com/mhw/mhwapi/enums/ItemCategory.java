package com.mhw.mhwapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ItemCategory {
    AMMO("ammo"),
    HIDDEN("hidden"),
    ITEM("item"),
    MATERIAL("material"),
    MISC("misc");

    private final String value;

    ItemCategory(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
