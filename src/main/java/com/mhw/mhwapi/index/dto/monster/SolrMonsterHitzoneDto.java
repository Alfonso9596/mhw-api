package com.mhw.mhwapi.index.dto.monster;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

@Data
public class SolrMonsterHitzoneDto {

    @Field("id")
    Integer id;

    @Field("monster_id_i")
    Integer monsterId;

    @Field("name_de_t")
    String nameDe;

    @Field("name_en_t")
    String nameEn;

    @Field("cut_i")
    Integer cut;

    @Field("impact_i")
    Integer impact;

    @Field("shot_i")
    Integer shot;

    @Field("fire_i")
    Integer fire;

    @Field("water_i")
    Integer water;

    @Field("ice_i")
    Integer ice;

    @Field("thunder_i")
    Integer thunder;

    @Field("dragon_i")
    Integer dragon;

    @Field("ko_i")
    Integer ko;
}
