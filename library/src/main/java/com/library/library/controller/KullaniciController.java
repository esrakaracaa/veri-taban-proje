package com.library.library.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.entity.Kullanici;
import com.library.library.service.KullaniciService;

@RestController
@RequestMapping("/api/kullanicilar")
public class KullaniciController {

    private final KullaniciService kullaniciService;

    public KullaniciController(KullaniciService kullaniciService) {
        this.kullaniciService = kullaniciService;
    }

    // 1. TÜMÜNÜ GETİR (JSON Listesi döner)
    @GetMapping
    public List<Kullanici> tumunuGetir() {
        return kullaniciService.tumKullanicilariGetir();
    }

    // 2. SADECE AKTİF OLANLARI GETİR
    @GetMapping("/aktif")
    public List<Kullanici> aktifleriGetir() {
        return kullaniciService.aktifKullanicilariGetir();
    }

    // 3. ID'YE GÖRE TEK BİR KULLANICI GETİR
    @GetMapping("/{id}")
    public ResponseEntity<Kullanici> getir(@PathVariable("id") Integer id) {
        // Artık kullaniciBul metodu serviste tanımlı
        Kullanici kullanici = kullaniciService.kullaniciBul(id);
        return kullanici != null ? ResponseEntity.ok(kullanici) : ResponseEntity.notFound().build();
    }

    // 4. YENİ KULLANICI EKLE
    @PostMapping
    public Kullanici ekle(@RequestBody Kullanici kullanici) {
        return kullaniciService.kaydet(kullanici);
    }

    // 5. KULLANICI GÜNCELLE (Hatalar Giderildi)
    @PutMapping("/{id}")
    public ResponseEntity<Kullanici> guncelle(@PathVariable("id") Integer id, @RequestBody Kullanici kullanici) {
        // Mevcut kullanıcıyı veri tabanından sorguluyoruz
        Kullanici mevcutKullanici = kullaniciService.kullaniciBul(id);
        
        if (mevcutKullanici == null) {
            return ResponseEntity.notFound().build();
        }

        // Alanları güncelliyoruz (Entity sınıfındaki getEposta ile uyumlu hale getirildi)
        mevcutKullanici.setAdSoyad(kullanici.getAdSoyad());
        mevcutKullanici.setEposta(kullanici.getEposta());
        mevcutKullanici.setSifre(kullanici.getSifre());
        mevcutKullanici.setRol(kullanici.getRol());
        mevcutKullanici.setAktifMi(kullanici.getAktifMi());

        return ResponseEntity.ok(kullaniciService.kaydet(mevcutKullanici));
    }

    // 6. KULLANICI SİL
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> sil(@PathVariable("id") Integer id) {
        kullaniciService.sil(id);
        return ResponseEntity.ok().build();
    }
}