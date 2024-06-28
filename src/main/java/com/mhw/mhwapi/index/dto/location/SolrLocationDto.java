package com.mhw.mhwapi.index.dto.location;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

@Data
public class SolrLocationDto {

    @Field("id")
    Integer id;

    @Field("order_id_i")
    Integer orderId;

    @Field("name_de_t")
    String nameDe;

    @Field("name_en_t")
    String nameEn;
}
