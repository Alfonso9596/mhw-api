package com.mhw.mhwapi.enums.converter;

import com.mhw.mhwapi.enums.MonsterType;
import jakarta.persistence.AttributeConverter;
import lombok.NonNull;

public class MonsterTypeConverter implements AttributeConverter<MonsterType, String> {

    @Override
    public String convertToDatabaseColumn(@NonNull MonsterType type) {
        return type.getEnglishValue();
    }

    @Override
    public MonsterType convertToEntityAttribute(String dbData) {
        for (MonsterType type : MonsterType.values()) {
            if (type.getEnglishValue().equals(dbData)) {
                return type;
            }
        }
        return null;
    }
}
