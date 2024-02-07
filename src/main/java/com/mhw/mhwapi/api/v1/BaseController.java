package com.mhw.mhwapi.api.v1;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@CrossOrigin
public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    private static final String LANGUAGE_IS_NOT_SUPPORTED = "Language [%s] is not supported.";
    private static final String MONSTER_SIZE_IS_NOT_SUPPORTED = "Monster size [%s] is not supported.";

    public void validateLangPath(String lang) {
        switch (lang) {
            case "en":
            case "de":
                break;
            default:
                String message = String.format(LANGUAGE_IS_NOT_SUPPORTED, lang);
                LOGGER.error(message);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    public void validateMonsterSizePath(String size) {
        switch (size) {
            case "small":
            case "large":
                break;
            default:
                String message = String.format(MONSTER_SIZE_IS_NOT_SUPPORTED, size);
                LOGGER.error(message);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }
}
