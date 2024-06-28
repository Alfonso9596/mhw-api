package com.mhw.mhwapi.index.dto.monster;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

@Data
public class SolrMonsterRewardConditionDto {

    @Field("id")
    Integer id;

    @Field("name_de_t")
    String nameDe;

    @Field("name_en_t")
    String nameEn;
}
