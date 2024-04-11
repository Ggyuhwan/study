package com.study.shop.dto;

import com.study.shop.entity.ItemEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 필드 매개변수
@ToString
//  DTO(Data Transfer Object), VO, Bean
public class ItemDTO {

    private Long id;
    private String item;
    private String price;
    private String writer;

    private MultipartFile itemFile; // add.html -> Controller 파일 담는 용도
    private String originalFileName; // 원본 파일 이름
    private String storedFileName;  // 서버 저장용 파일 이름
    private int fileAttached;       // 파일 첨부 여부( 첨부 1, 미첨부 0)
    public static ItemDTO toItemDTO(ItemEntity itemEntity) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(itemEntity.getId());
        itemDTO.setItem(itemEntity.getItem());
        itemDTO.setPrice(itemEntity.getPrice());
        itemDTO.setWriter(itemEntity.getWriter());
        if (itemEntity.getFileAttached() == 0) {
            itemDTO.setFileAttached(itemEntity.getFileAttached());
        } else {
            itemDTO.setFileAttached(itemEntity.getFileAttached());
            // 파일 이름을 가져가야 함
            // originalFileName, storedFileName : item_file (ItemFileEntity)
            itemDTO.setOriginalFileName(itemEntity.getItemFileEntityList().get(0).getOriginalFileName());
            itemDTO.setStoredFileName(itemEntity.getItemFileEntityList().get(0).getStoredFileName());

        }
        return itemDTO;
    }
}
