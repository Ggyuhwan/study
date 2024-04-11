package com.study.shop.entity;

import com.study.shop.dto.ItemDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "Item")
// DB의 테이블 역할을 하는 클래스
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false) // 크기 20, not null
    private String item;
    private String price;
    private String writer;

    @Column
    private int fileAttached; // 1 or 0

    @OneToMany(mappedBy = "itemEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemFileEntity> itemFileEntityList = new ArrayList<>();

    public static ItemEntity toSaveEntity(ItemDTO itemDTO) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItem(itemDTO.getItem());
        itemEntity.setPrice(itemDTO.getPrice());
        itemEntity.setWriter(itemDTO.getWriter());
        itemEntity.setFileAttached(0); // 파일 없음
        return itemEntity;
    }

    public static ItemEntity toUpdateEntity(ItemDTO itemDTO) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(itemDTO.getId());
        itemEntity.setItem(itemDTO.getItem());
        itemEntity.setPrice(itemDTO.getPrice());
        itemEntity.setWriter(itemDTO.getWriter());
        return itemEntity;
    }

    public static ItemEntity toSaveFileEntity(ItemDTO itemDTO) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItem(itemDTO.getItem());
        itemEntity.setPrice(itemDTO.getPrice());
        itemEntity.setWriter(itemDTO.getWriter());
        itemEntity.setFileAttached(1); // 파일 있음
        return itemEntity;
    }
}
