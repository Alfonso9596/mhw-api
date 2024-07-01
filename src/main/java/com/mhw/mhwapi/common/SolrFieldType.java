package com.mhw.mhwapi.common;

public enum SolrFieldType {

    STRING("s"),
    INTEGER("i"),
    LONG("l"),
    DOUBLE("d"),
    BOOLEAN("b"),
    FLOAT("f"),
    TEXT("txt"),
    TEXT_SINGLE("t");

    private final String suffix;

    SolrFieldType(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}
