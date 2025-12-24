package com.library.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.entity.Kitap;
import com.library.library.service.KitapService;

@RestController
@RequestMapping("/api/kitaplar") // ✅ DÜZELTİLDİ: Başına /api eklendi
public class KitapController {

    private final KitapService kitapService;

    public KitapController(KitapService kitapService) {
        this.kitapService = kitapService;
    }

    @GetMapping
    public List<Kitap> tumunuGetir() {
        return kitapService.tumKitaplariGetir();
    }

    @PostMapping
    public Kitap ekle(@RequestBody Kitap kitap) {
        return kitapService.kaydet(kitap);
    }

    @GetMapping("/aktif")
    public List<Kitap> aktifleriGetir() {
        return kitapService.aktifKitaplariGetir();
    }
}