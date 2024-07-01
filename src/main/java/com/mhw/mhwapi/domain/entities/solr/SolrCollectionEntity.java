package com.mhw.mhwapi.domain.entities.solr;

import com.mhw.mhwapi.enums.CollectionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = SolrCollectionEntity.TABLE_NAME)
public class SolrCollectionEntity {

    static final String TABLE_NAME = "solr_collection";

    @Id
    private Integer id;

    @NotEmpty
    private String name;

    @Enumerated(value = EnumType.STRING)
    private CollectionType collectionType;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SolrFieldDefinitionEntity> solrFieldDefinitions;

    public void setSolrFieldDefinitions(Set<SolrFieldDefinitionEntity> solrFieldDefinitions) {
        if (solrFieldDefinitions != null) {
            solrFieldDefinitions.forEach(solrFieldDefinitionEntity -> solrFieldDefinitionEntity.setCollection(this));
            this.solrFieldDefinitions = solrFieldDefinitions;
        }
    }

}
