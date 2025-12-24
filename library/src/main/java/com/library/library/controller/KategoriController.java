package com.library.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.entity.Kategori;
import com.library.library.service.KategoriService;

@RestController
@RequestMapping("/api/kategoriler")
public class KategoriController {

    private final KategoriService kategoriService;

    public KategoriController(KategoriService kategoriService) {
        this.kategoriService = kategoriService;
    }

     // ✅ Var olan: tüm kategoriler
    @GetMapping
    public List<Kategori> tumunuGetir() {
        return kategoriService.tumKategorileriGetir();
    }

    // ✅ Yeni: sadece aktif kategoriler
    @GetMapping("/aktif")
    public List<Kategori> aktifleriGetir() {
        return kategoriService.aktifKategorileriGetir();
    }

    // ✅ Yeni: kategori ekleme
    @PostMapping
    public Kategori ekle(@RequestBody Kategori kategori) {
        return kategoriService.kaydet(kategori);
    }
}
