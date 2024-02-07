package com.mhw.mhwapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ItemSubCategory {
    ACCOUNT("account"),
    APPRAISAL("appraisal"),
    SUPPLY("supply"),
    TRADE("trade");

    private String value;

    ItemSubCategory(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
