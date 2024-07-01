package com.mhw.mhwapi.enums;

import com.mhw.mhwapi.api.v1.search.dto.DocumentResultDto;
import com.mhw.mhwapi.api.v1.search.dto.monster.MonsterResultDto;

public enum SearchType {

    MONSTER(MonsterResultDto.class, CollectionType.MONSTER);

    Class<? extends DocumentResultDto> returnedClass;

    CollectionType collectionType;

    SearchType(Class<? extends DocumentResultDto> returnedClass, CollectionType collectionType) {
        this.returnedClass = returnedClass;
        this.collectionType = collectionType;
    }

    public Class<? extends DocumentResultDto> getReturnedClass() {
        return returnedClass;
    }

    public CollectionType getCollectionType() {
        return collectionType;
    }
}
