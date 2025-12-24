package com.library.library.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.library.library.entity.Kitap;

public interface KitapService {
    
    // --- Temel CRUD İşlemleri ---
    Kitap idIleGetir(Integer id);
    void sil(Integer id);
    List<Kitap> tumKitaplariGetir();
    List<Kitap> aktifKitaplariGetir();
    Kitap kaydet(Kitap kitap);

    /**
     * ✅ Tüm kitapları belirli sayfalar halinde getirir.
     */
    Page<Kitap> tumKitaplariSayfaliGetir(Pageable pageable);

    /**
     * ✅ GÜNCELLENDİ: Sayfalamalı ve Kategori Filtreli Arama
     * WebController'dan gelen (keyword, kategoriId, pageable) parametreleriyle tam uyumludur.
     */
    Page<Kitap> sayfaliKitapAra(String keyword, Integer kategoriId, Pageable pageable);

    /**
     * Standart Arama (List olarak dönen eski sürüm)
     */
    List<Kitap> kitapAra(String keyword); 
}