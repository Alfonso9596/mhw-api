package com.mhw.mhwapi.domain.services.index;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrCollectionDto;
import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrFieldDefinitionDto;
import com.mhw.mhwapi.index.dto.IndexEntryDto;
import io.swagger.v3.core.util.ReflectionUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseIndexService {

    public Map<Field, List<SolrFieldDefinitionDto>> createConfigurationMap(SolrCollectionDto configuration, Class<?> clz) {
        Map<Field, List<SolrFieldDefinitionDto>> map = new HashMap<>();
        List<Field> dataFields = getAllFields(clz);
        if (configuration == null || CollectionUtils.isEmpty(configuration.getSolrFieldDefinitions())) {
            return map;
        }
        Set<SolrFieldDefinitionDto> fieldDefinitions = configuration.getSolrFieldDefinitions();

        for (Field dataField : dataFields) {
            String name = dataField.getName();
            List<SolrFieldDefinitionDto> configurationField = fieldDefinitions.stream()
                    .filter(field -> name.equalsIgnoreCase(field.getImportFieldName()))
                    .toList();

            if (configurationField.isEmpty()) {
                continue;
            }
            map.put(dataField, configurationField);
        }
        return map;
    }

    protected void index(SolrClient solrClient, IndexEntryDto indexEntry, Map<Field, List<SolrFieldDefinitionDto>> configurationMap) throws IllegalAccessException {
        SolrInputDocument solrDocument = new SolrInputDocument();
        for (Map.Entry<Field, List<SolrFieldDefinitionDto>> entry : configurationMap.entrySet()) {
            Field field = entry.getKey();
            List<SolrFieldDefinitionDto> definitions = entry.getValue();
            for (SolrFieldDefinitionDto definition : definitions) {
                solrDocument.addField(definition.getSolrFieldName(), field.get(indexEntry));
            }
        }
        try {
            solrClient.add(solrDocument);
            solrClient.commit();
        } catch (IOException | SolrServerException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Field> getAllFields(Class<?> clz) {
        return ReflectionUtils.getDeclaredFields(clz);
    }
}
