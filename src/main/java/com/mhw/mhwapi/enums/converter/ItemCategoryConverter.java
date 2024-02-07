package com.mhw.mhwapi.enums.converter;

import com.mhw.mhwapi.enums.ItemCategory;
import jakarta.persistence.AttributeConverter;
import lombok.NonNull;

public class ItemCategoryConverter implements AttributeConverter<ItemCategory, String> {

    @Override
    public String convertToDatabaseColumn(@NonNull ItemCategory attribute) {
        return attribute.getValue();
    }

    @Override
    public ItemCategory convertToEntityAttribute(@NonNull String dbData) {
        for (ItemCategory itemCategory : ItemCategory.values()) {
            if (itemCategory.getValue().equals(dbData)) {
                return itemCategory;
            }
        }
        return null;
    }
}
