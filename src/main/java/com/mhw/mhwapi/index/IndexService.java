package com.mhw.mhwapi.index;

import org.apache.solr.client.solrj.SolrClient;

public interface IndexService {

    int batchSize = 200;

    String[] LANGUAGES = {"de","en"};

    String getCollectionName();

    void indexByType(SolrClient solrClient);
}
