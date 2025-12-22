package com.library.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.entity.Kullanici;
import com.library.library.service.KullaniciService;

@RestController
@RequestMapping("/kullanicilar")
public class KullaniciController {

    private final KullaniciService kullaniciService;

    public KullaniciController(KullaniciService kullaniciService) {
        this.kullaniciService = kullaniciService;
    }

    @GetMapping
    public List<Kullanici> tumunuGetir() {
        return kullaniciService.tumKullanicilariGetir();
    }

    @GetMapping("/aktif")
    public List<Kullanici> aktifleriGetir() {
        return kullaniciService.aktifKullanicilariGetir();
    }

    @PostMapping
    public Kullanici ekle(@RequestBody Kullanici kullanici) {
        return kullaniciService.kaydet(kullanici);
    }
}
