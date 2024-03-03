package com.mhw.mhwapi.common;

public interface ApiUrlMapping {

    String API_PATH = "/api";
    String LANG_PATH = "/{lang}";

    interface MonsterUrlMapping {
        String MONSTER_PATH = "/monsters";
        String SIZE_PARAM = "/{size}";
        String GET_ALL = API_PATH + LANG_PATH + MONSTER_PATH + "/all";
        String GET_ALL_PAGEABLE = API_PATH + LANG_PATH + MONSTER_PATH;
        String GET_BY_ID = API_PATH + LANG_PATH + MONSTER_PATH + "/{id}";
    }

    interface ItemUrlMapping {
        String ITEM_PATH = "/items";
        String GET_ALL = API_PATH + LANG_PATH + ITEM_PATH;
        String GET_BY_ID = API_PATH + LANG_PATH + ITEM_PATH + "/{id}";
    }

    interface LanguageUrlMapping {
        String LANGUAGE_PATH = "/language";
        String GET_ALL_ACTIVE = API_PATH + LANGUAGE_PATH;
    }
}
