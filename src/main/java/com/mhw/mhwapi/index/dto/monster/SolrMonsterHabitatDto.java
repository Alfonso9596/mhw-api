package com.mhw.mhwapi.index.dto.monster;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

@Data
public class SolrMonsterHabitatDto {

    @Field("id")
    Integer id;

    @Field("monster_id_i")
    Integer monsterId;

    @Field("location_id_i")
    Integer locationId;

    @Field("start_area_t")
    String startArea;

    @Field("move_area_t")
    String moveArea;

    @Field("rest_area_t")
    String restArea;
}
