package com.mhw.mhwapi.enums.converter;

import com.mhw.mhwapi.enums.QuestType;
import jakarta.persistence.AttributeConverter;

public class QuestTypeConverter implements AttributeConverter<QuestType, String> {

    @Override
    public String convertToDatabaseColumn(QuestType attribute) {
        return attribute.getValue();
    }

    @Override
    public QuestType convertToEntityAttribute(String dbData) {
        for (QuestType questType : QuestType.values()) {
            if (questType.getValue().equals(dbData)) {
                return questType;
            }
        }
        return null;
    }
}
