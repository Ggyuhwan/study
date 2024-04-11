package com.study.shop.service;

import com.study.shop.dto.MemberDTO;
import com.study.shop.entity.MemberEntity;
import com.study.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    // 생성자 주입
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository 의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        var hash = passwordEncoder.encode(memberEntity.getPassword());
        memberEntity.setPassword(hash);
        memberRepository.save(memberEntity);
        // reposiory의 save 메서드 호출 (조건. entity객체를 넘겨줘야 함)
    }

    public MemberDTO updateForm(String username) {
        Optional<MemberEntity> member = memberRepository.findByUsername(username);
        MemberEntity memberEntity = member.get();
        MemberDTO dto = MemberDTO.tomemberDTO(memberEntity);
        return dto;
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }
}
