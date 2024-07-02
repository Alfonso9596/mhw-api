package com.mhw.mhwapi.domain.entities.solr;

import com.mhw.mhwapi.common.SolrFieldType;
import com.mhw.mhwapi.common.WildcardType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = SolrFieldDefinitionEntity.TABLE_NAME)
public class SolrFieldDefinitionEntity {

    static final String TABLE_NAME = "solr_field_definition";

    @Id
    private Integer id;

    private String importFieldName;

    private String responseFieldName;

    private Boolean multiValue;

    @Enumerated(value = EnumType.STRING)
    private SolrFieldType fieldType;

    private Boolean searchable;

    @Enumerated(value = EnumType.STRING)
    private WildcardType wildcardType;

    private Boolean freeTextSearch;

    private Double boost;

    private Boolean langSearch;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "collection_id", referencedColumnName = "id")
    private SolrCollectionEntity collection;
}
