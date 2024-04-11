package com.study.shop.controller;

import com.study.shop.dto.ItemDTO;
import com.study.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/item/add")
    public String itemadd(Authentication auth) {
        return "item/add";
    }

    @PostMapping("/item/add")
    public String save(@ModelAttribute ItemDTO itemDTO) throws IOException {
        System.out.println("itemDTO = " + itemDTO);
        itemService.save(itemDTO);
        return "redirect:/item/list";
    }

    @GetMapping("/item/list")
    public String findAll(Model model) {
        // DB에서 전체 게시글 데이터를 가죠와서 list.html에 보여준다
        List<ItemDTO> itemDTOList = itemService.findAll();
        model.addAttribute("itemList", itemDTOList);
        return "item/list";
    }

    @GetMapping("/item/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ItemDTO itemDTO = itemService.findById(id);
        model.addAttribute("item", itemDTO);
        return "item/detail";
    }

    @GetMapping("/item/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        ItemDTO itemDTO = itemService.findById(id);
        model.addAttribute("item", itemDTO);
        return "item/update";
    }
    @PostMapping("item/update")
    public String itemUpdate(@ModelAttribute ItemDTO itemDTO) {
        itemService.update(itemDTO);
        return "redirect:/item/list";
    }

    @GetMapping("item/delete/{id}")
    public String delete(@PathVariable Long id) {
        itemService.delete(id);
        return "redirect:/item/list";
    }
}
