package com.mhw.mhwapi.index.dto.monster;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

@Data
public class SolrMonsterBreakDto {

    @Field("id")
    Integer id;

    @Field("monster_id_i")
    Integer monsterId;

    @Field("name_de_t")
    String nameDe;

    @Field("name_en_t")
    String nameEn;

    @Field("flinch_i")
    Integer flinch;

    @Field("wound_i")
    Integer wound;

    @Field("sever_i")
    Integer sever;

    @Field("extract_t")
    String extract;
}
