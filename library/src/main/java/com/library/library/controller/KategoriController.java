package com.library.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.entity.Kategori;
import com.library.library.service.KategoriService;

@RestController
@RequestMapping("/kategoriler")
public class KategoriController {

    private final KategoriService kategoriService;

    public KategoriController(KategoriService kategoriService) {
        this.kategoriService = kategoriService;
    }

    @GetMapping
    public List<Kategori> tumunuGetir() {
        return kategoriService.tumKategorileriGetir();
    }
}
