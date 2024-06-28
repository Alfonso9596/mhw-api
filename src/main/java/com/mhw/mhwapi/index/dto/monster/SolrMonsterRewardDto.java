package com.mhw.mhwapi.index.dto.monster;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

@Data
public class SolrMonsterRewardDto {

    @Field("id")
    Integer id;

    @Field("monster_id_i")
    Integer monsterId;

    @Field("condition_id_i")
    Integer conditionId;

    @Field("rank_t")
    String rank;

    @Field("item_id_i")
    Integer itemId;

    @Field("stack_i")
    Integer stack;

    @Field("percentage_i")
    Integer percentage;
}
