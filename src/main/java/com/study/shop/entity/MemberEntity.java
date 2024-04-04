package com.study.shop.entity;

import com.study.shop.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Setter
@Getter
@Table(name = "Member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String displayName;
    private String password;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUsername(memberDTO.getUsername());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setDisplayName(memberDTO.getDisplayName());
        return memberEntity;
    }

}
