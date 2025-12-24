package com.library.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.entity.Yazar;
import com.library.library.service.YazarService;

@RestController
@RequestMapping("/api/yazarlar") // ✅ DÜZELTİLDİ: Başına /api eklendi
public class YazarController {

    private final YazarService yazarService;

    public YazarController(YazarService yazarService) {
        this.yazarService = yazarService;
    }

    @GetMapping
    public List<Yazar> tumunuGetir() {
        return yazarService.tumYazarlariGetir();
    }

    @PostMapping
    public Yazar ekle(@RequestBody Yazar yazar) {
        return yazarService.kaydet(yazar);
    }
}