package com.study.shop.dto;


import com.study.shop.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 필드 매개변수
@ToString   // 필드 값 출력
public class MemberDTO {
    private Long id;
    private String username;
    private String displayName;
    private String password;

    public static MemberDTO tomemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setId(memberEntity.getId());
        memberDTO.setUsername(memberEntity.getUsername());
        memberDTO.setPassword(memberEntity.getPassword());
        memberDTO.setDisplayName(memberEntity.getDisplayName());
        return memberDTO;
    }
}
