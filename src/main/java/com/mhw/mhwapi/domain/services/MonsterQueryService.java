package com.mhw.mhwapi.domain.services;

import com.mhw.mhwapi.api.v1.monster.dto.MonsterDto;
import com.mhw.mhwapi.api.v1.monster.dto.MonsterSimpleDto;
import com.mhw.mhwapi.common.Filter;
import com.mhw.mhwapi.common.Page;
import com.mhw.mhwapi.common.QueryOperator;
import com.mhw.mhwapi.common.SearchCriteria;
import com.mhw.mhwapi.domain.entities.EntitySpecification;
import com.mhw.mhwapi.domain.entities.monster.MonsterEntity;
import com.mhw.mhwapi.domain.entities.monster.MonsterRepository;
import com.mhw.mhwapi.domain.entities.monster.MonsterRepositoryCustom;
import com.mhw.mhwapi.domain.entities.monsterText.MonsterTextEntity;
import com.mhw.mhwapi.domain.entities.monsterText.MonsterTextRepository;
import com.mhw.mhwapi.mappers.monster.MonsterConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Autowired
    private EntitySpecification<MonsterEntity> entityEntitySpecification;

    public Page<MonsterSimpleDto> getPageableMonsters(String lang, PageRequest pageable, Map<String, Object> params) {
        List<Filter> filters = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Filter filter = new Filter();
            filter.setField(entry.getKey());
            filter.setOperator(QueryOperator.EQUALS);
            filter.setValue(String.valueOf(entry.getValue()));
            filters.add(filter);
        }
        SearchCriteria searchCriteria = new SearchCriteria(filters);
        var data = monsterRepositoryCustom.findMonsterByPageable(pageable, entityEntitySpecification.specificationBuilder(searchCriteria));
        for (MonsterEntity monsterEntity : data.getContent()) {
            this.addMonsterInformation(monsterEntity, lang);
            this.translateMonsterSize(monsterEntity, lang);
        }

        return convertPageResponse(monsterConverter.mapSimple(data.getContent()), data, pageable);
    }

    public List<MonsterSimpleDto> getAllMonsters(String lang, Map<String, Object> params) {
        List<Filter> filters = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Filter filter = new Filter();
            filter.setField(entry.getKey());
            filter.setOperator(QueryOperator.EQUALS);
            filter.setValue(String.valueOf(entry.getValue()));
            filters.add(filter);
        }
        SearchCriteria searchCriteria = new SearchCriteria(filters);
        List<MonsterEntity> monsterList = monsterRepositoryCustom.findMonsterBy(entityEntitySpecification.specificationBuilder(searchCriteria));
        for (MonsterEntity monsterEntity : monsterList) {
            this.addMonsterInformation(monsterEntity, lang);
            this.translateMonsterSize(monsterEntity, lang);
        }

        return monsterList
                .stream()
                .map(monsterConverter::mapSimple)
                .sorted(Comparator.comparing(MonsterSimpleDto::getName))
                .collect(Collectors.toList());


    }

    public MonsterDto getMonsterById(String lang, Integer id) {
        MonsterEntity monsterEntity = monsterRepository.getReferenceById(id);

        this.addMonsterInformation(monsterEntity, lang);
        this.translateMonsterSize(monsterEntity, lang);

        return monsterConverter.map(monsterEntity);
    }

    private void addMonsterInformation(MonsterEntity monsterEntity, String lang) {
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
        } else if (lang.equals("en")) {
            if (monsterEntity.getSize().equals("small")) {
                monsterEntity.setSize("Small");
            } else {
                monsterEntity.setSize("Large");
            }
        }
    }
}
