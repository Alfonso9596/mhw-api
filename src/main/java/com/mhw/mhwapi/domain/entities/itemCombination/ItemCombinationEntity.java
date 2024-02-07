package com.mhw.mhwapi.domain.entities.itemCombination;

import com.mhw.mhwapi.domain.entities.item.ItemEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = ItemCombinationEntity.TABLE_NAME)
@Getter
@Setter
public class ItemCombinationEntity {

    static final String TABLE_NAME = "item_combination";

    @Id
    private Integer id;
    @ManyToOne(targetEntity = ItemEntity.class)
    @JoinColumn(name = "result_id")
    private ItemEntity result;
    @ManyToOne(targetEntity = ItemEntity.class)
    @JoinColumn(name = "first_id")
    private ItemEntity first;
    @ManyToOne(targetEntity = ItemEntity.class)
    @JoinColumn(name = "second_id")
    private ItemEntity second;
    private Integer quantity;
}
