package com.study.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "item_file")
public class ItemFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;

    public static ItemFileEntity toItemFileEntity(ItemEntity itemEntity, String originalFileName, String storedFileName) {
        ItemFileEntity itemFileEntity = new ItemFileEntity();
        itemFileEntity.setOriginalFileName(originalFileName);
        itemFileEntity.setStoredFileName(storedFileName);
        itemFileEntity.setItemEntity(itemEntity);
        return itemFileEntity;
    }
}
