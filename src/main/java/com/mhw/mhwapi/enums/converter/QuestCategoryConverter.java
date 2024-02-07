package com.mhw.mhwapi.enums.converter;

import com.mhw.mhwapi.enums.QuestCategory;
import jakarta.persistence.AttributeConverter;

public class QuestCategoryConverter implements AttributeConverter<QuestCategory, String> {


    @Override
    public String convertToDatabaseColumn(QuestCategory attribute) {
        return attribute.getValue();
    }

    @Override
    public QuestCategory convertToEntityAttribute(String dbData) {
        for (QuestCategory questCategory : QuestCategory.values()) {
            if (questCategory.getValue().equals(dbData)) {
                return questCategory;
            }
        }
        return null;
    }
}
