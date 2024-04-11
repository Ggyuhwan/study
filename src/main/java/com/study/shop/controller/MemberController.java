package com.study.shop.controller;

import com.study.shop.dto.MemberDTO;
import com.study.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    @GetMapping("/member/save")
    public String joinForm() {

        return "member/join";
    }
    @GetMapping("/member/login")
    public String loginForm() {
        return "member/login";
    }
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        if (memberDTO.getUsername().length() <= 2 && memberDTO.getPassword().length() <= 4) {
            System.out.println("아이디 & 비번 4자리이상");
            return "redirect:/member/save";
        }
        memberService.save(memberDTO);
        return "redirect:/member/login";
    }

    @GetMapping("/member/update")
    public String myPage(Authentication auth, Model model) {
        MemberDTO memberDTO = memberService.updateForm(auth.getName());
        model.addAttribute("data", memberDTO);
        System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
        System.out.println("auth = " + auth);
        System.out.println("authname = " + auth.getName());
        System.out.println("auth.isAuthenticated() = " + auth.isAuthenticated());
        if (auth.isAuthenticated() == false) {
            return "redirect:";
        }
        return "member/mypage";
    }
    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {

        memberService.update(memberDTO);
        return "redirect:/member/update";
    }
    @GetMapping("/test")
    public String test() {
        var result = new BCryptPasswordEncoder().encode("1234");
        System.out.println("result = " + result);
        return "index";
    }

}
