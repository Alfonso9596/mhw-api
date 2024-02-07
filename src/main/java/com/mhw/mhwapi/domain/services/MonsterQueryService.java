package com.mhw.mhwapi.domain.services;

import com.mhw.mhwapi.api.v1.monster.dto.MonsterDto;
import com.mhw.mhwapi.api.v1.monster.dto.MonsterSimpleDto;
import com.mhw.mhwapi.api.v1.monster.dto.MonsterTextDto;
import com.mhw.mhwapi.common.Page;
import com.mhw.mhwapi.domain.entities.monster.MonsterEntity;
import com.mhw.mhwapi.domain.entities.monster.MonsterRepository;
import com.mhw.mhwapi.domain.entities.monster.MonsterRepositoryCustom;
import com.mhw.mhwapi.domain.entities.monsterText.MonsterTextEntity;
import com.mhw.mhwapi.domain.entities.monsterText.MonsterTextRepository;
import com.mhw.mhwapi.mappers.monster.MonsterConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mhw.mhwapi.util.QueryPageResponseUtil.convertPageResponse;

@Service
public class MonsterQueryService {

    @Autowired
    private MonsterRepository monsterRepository;

    @Autowired
    private MonsterTextRepository monsterTextRepository;

    @Autowired
    private MonsterConverter monsterConverter;

    @Autowired
    private MonsterRepositoryCustom monsterRepositoryCustom;

    public Page<MonsterDto> getAllMonsters(String lang, PageRequest pageable) {
        var data = monsterRepositoryCustom.findMonsterBy(pageable);
        for (MonsterEntity monsterEntity : data.getContent()) {
            this.addMonsterInformation(monsterEntity, lang, false);
            this.translateMonsterSize(monsterEntity, lang);
        }

        return convertPageResponse(monsterConverter.map(data.getContent()), data, pageable);
    }

    public List<MonsterDto> getBySize(String lang, String size) {
        List<MonsterEntity> monsterEntities = monsterRepository.getBySize(size);
        for (MonsterEntity monsterEntity : monsterEntities) {
            this.addMonsterInformation(monsterEntity, lang, false);
        }

        return monsterEntities
                .stream()
                .map(monsterConverter::map)
                .sorted(Comparator.comparing(MonsterDto::getId))
                .collect(Collectors.toList());
    }

    private void addMonsterInformation(MonsterEntity monsterEntity, String lang, boolean isSimple) {
        Optional<MonsterTextEntity> monsterTextEntity = monsterTextRepository.getMonsterTextByIdAndLang(monsterEntity.getId(), lang);

        monsterTextEntity.ifPresent(monsterEntity::setData);
    }

    private void translateMonsterSize(MonsterEntity monsterEntity, String lang) {
        if (lang.equals("de")) {
            if (monsterEntity.getSize().equals("small")) {
                monsterEntity.setSize("Klein");
            } else {
                monsterEntity.setSize("Gross");
            }
        }
    }
}
