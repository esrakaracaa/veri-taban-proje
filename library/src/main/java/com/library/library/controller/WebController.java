package com.library.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.library.library.entity.Kitap;
import com.library.library.entity.Kullanici;
import com.library.library.service.KategoriService;
import com.library.library.service.KitapService;
import com.library.library.service.KullaniciService;
import com.library.library.service.YazarService;

@Controller
public class WebController {
    private final KullaniciService kullaniciService;

    private final KitapService kitapService;
    private final YazarService yazarService;
    private final KategoriService kategoriService;

    // Tüm servisleri buraya enjekte ediyoruz
    public WebController(KitapService kitapService, YazarService yazarService, KategoriService kategoriService, KullaniciService kullaniciService) {
        this.kitapService = kitapService;
        this.yazarService = yazarService;
        this.kategoriService = kategoriService;
        this.kullaniciService = kullaniciService;
    }

    @GetMapping("/")
    public String anaSayfa() {
        return "index";
    }

    @GetMapping("/kitap-listesi")
    public String kitapListesi(Model model) {
        model.addAttribute("kitaplar", kitapService.tumKitaplariGetir());
        return "kitaplar";
    }

    // --- YENİ EKLENEN KISIMLAR ---

    // 1. Kitap Ekleme Sayfasını Göster (GET)
    @GetMapping("/kitap-ekle")
    public String kitapEkleForm(Model model) {
        model.addAttribute("kitap", new Kitap()); // Boş bir kitap nesnesi gönderiyoruz
        model.addAttribute("yazarlar", yazarService.tumYazarlariGetir()); // Dropdown için yazarlar
        model.addAttribute("kategoriler", kategoriService.tumKategorileriGetir()); // Dropdown için kategoriler
        return "kitap-ekle"; // kitap-ekle.html sayfasını aç
    }

    // 2. Formdan Gelen Veriyi Kaydet (POST)
    @PostMapping("/kitap-kaydet")
    public String kitapKaydet(@ModelAttribute("kitap") Kitap kitap) {
        kitap.setAktifMi(true); // Yeni eklenen kitap varsayılan olarak aktiftir
        kitapService.kaydet(kitap);
        return "redirect:/kitap-listesi"; // Kayıttan sonra listeye geri dön
    }

    // --- YENİ: Kitap Silme İşlemi ---
    @GetMapping("/kitap-sil/{id}")
    public String kitapSil(@PathVariable("id") Integer id) {
        kitapService.sil(id); // Bu metodu servise de eklememiz gerekecek
        return "redirect:/kitap-listesi";
    }

    // --- YENİ: Düzenleme Sayfasını Aç ---
    @GetMapping("/kitap-duzenle/{id}")
    public String kitapDuzenleForm(@PathVariable("id") Integer id, Model model) {
        Kitap kitap = kitapService.idIleGetir(id);
        model.addAttribute("kitap", kitap);
        model.addAttribute("yazarlar", yazarService.tumYazarlariGetir());
        model.addAttribute("kategoriler", kategoriService.tumKategorileriGetir());
        return "kitap-duzenle"; // Yeni bir HTML sayfası oluşturacağız
    }

    // --- YENİ: Güncellemeyi Kaydet ---
    // Not: JPA'da 'save' metodu ID varsa güncelleme yapar, yoksa yeni kayıt ekler.
    @PostMapping("/kitap-guncelle")
    public String kitapGuncelle(@ModelAttribute("kitap") Kitap kitap) {
        kitapService.kaydet(kitap);
        return "redirect:/kitap-listesi";
    }

    // --- KULLANICI İŞLEMLERİ ---

    // 1. Kullanıcı Listesi
    @GetMapping("/kullanicilar")
    public String kullaniciListesi(Model model) {
        model.addAttribute("kullanicilar", kullaniciService.tumKullanicilariGetir());
        return "kullanicilar";
    }

    // 2. Kullanıcı Ekleme Formu
    @GetMapping("/kullanici-ekle")
    public String kullaniciEkleForm(Model model) {
        model.addAttribute("kullanici", new Kullanici());
        return "kullanici-ekle";
    }

    // 3. Kullanıcı Kaydetme
    @PostMapping("/kullanici-kaydet")
    public String kullaniciKaydet(@ModelAttribute("kullanici") Kullanici kullanici) {
        // Yeni kullanıcı varsayılan değerleri
        if (kullanici.getKullaniciId() == null) {
            kullanici.setBorc(java.math.BigDecimal.ZERO); // Borcu 0 olsun
        }
        kullaniciService.kaydet(kullanici);
        return "redirect:/kullanicilar";
    }

    // 4. Kullanıcı Silme
    @GetMapping("/kullanici-sil/{id}")
    public String kullaniciSil(@PathVariable("id") Integer id) {
        kullaniciService.sil(id);
        return "redirect:/kullanicilar";
    }
}