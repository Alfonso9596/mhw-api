package com.mhw.mhwapi.index;

import com.mhw.mhwapi.api.v1.solrconfiguration.dto.SolrCollectionDto;
import com.mhw.mhwapi.enums.CollectionType;
import org.apache.solr.client.solrj.SolrClient;

public interface IndexService {

    int BATCH_SIZE = 200;

    String[] LANGUAGES = {"de","en"};

    boolean isSupported(CollectionType collectionType);

    void indexByType(SolrClient solrClient, SolrCollectionDto solrCollectionDto);
}
