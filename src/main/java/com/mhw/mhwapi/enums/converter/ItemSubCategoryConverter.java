package com.mhw.mhwapi.enums.converter;

import com.mhw.mhwapi.enums.ItemSubCategory;
import jakarta.persistence.AttributeConverter;

public class ItemSubCategoryConverter implements AttributeConverter<ItemSubCategory, String> {


    @Override
    public String convertToDatabaseColumn(ItemSubCategory attribute) {
        return attribute.getValue();
    }

    @Override
    public ItemSubCategory convertToEntityAttribute(String dbData) {
        for (ItemSubCategory itemSubCategory : ItemSubCategory.values()) {
            if (itemSubCategory.getValue().equals(dbData)) {
                return itemSubCategory;
            }
        }
        return null;
    }
}
