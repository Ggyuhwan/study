package com.study.shop.service;

import com.study.shop.dto.ItemDTO;
import com.study.shop.entity.ItemEntity;
import com.study.shop.entity.ItemFileEntity;
import com.study.shop.repository.ItemFileRepository;
import com.study.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// DTO -> Entity
// Entity -> DTO
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemFileRepository itemFileRepository;
    public void save(ItemDTO itemDTO) throws IOException {
            // 첨부 파일 X
        if (itemDTO.getItemFile().isEmpty()) {
            ItemEntity itemEntity = ItemEntity.toSaveEntity(itemDTO);
            itemRepository.save(itemEntity);
        } else {
            // 첨부 파일 O
            MultipartFile itemFile = itemDTO.getItemFile();
            String originalFilename = itemFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
            String savePath = "C:/springboot_img/" + storedFileName;
            itemFile.transferTo(new File(savePath));
            ItemEntity itemEntity = ItemEntity.toSaveFileEntity(itemDTO);
            Long savedId = itemRepository.save(itemEntity).getId();
            ItemEntity item = itemRepository.findById(savedId).get();

            ItemFileEntity itemFileEntity = ItemFileEntity.toItemFileEntity(item, originalFilename, storedFileName);
            itemFileRepository.save(itemFileEntity);
        }

    }
    @Transactional
    public List<ItemDTO> findAll() {
        List<ItemEntity> itemEntityList = itemRepository.findAll();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntityList) {
            itemDTOList.add(ItemDTO.toItemDTO(itemEntity));
        }
        return itemDTOList;
    }

    @Transactional
    public ItemDTO findById(Long id) {
        Optional<ItemEntity> byId = itemRepository.findById(id);
        if (byId.isPresent()) {
            ItemEntity itemEntity = byId.get();
            ItemDTO itemDTO = ItemDTO.toItemDTO(itemEntity);
            return itemDTO;
        } else {
            return null;
        }
    }

    public void update(ItemDTO itemDTO) {
        ItemEntity itemEntity = ItemEntity.toUpdateEntity(itemDTO);
        itemRepository.save(itemEntity);
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}
