package com.mhw.mhwapi.exception;

import com.mhw.mhwapi.common.EntityType;

import java.io.Serializable;
import java.util.Map;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

public class ItemNotFoundException extends BaseException {

    public ItemNotFoundException(String message) {
        super(message);
    }

    public static ItemNotFoundException of(EntityType type, Map<String, Serializable> parameters) {
        String params = parameters.entrySet().stream()
                .map(e -> format("%s=%s", e.getKey(), e.getValue()))
                .collect(joining(", "));
        final String message = format("There is no %s with %s", type, params);
        return new ItemNotFoundException(message);
    }
}
