package com.library.library.service;

import java.util.List;

import com.library.library.entity.Kategori;

public interface KategoriService {
    List<Kategori> tumKategorileriGetir();  

    // ✅ yeni: sadece aktifleri getir
    List<Kategori> aktifKategorileriGetir();

    // ✅ yeni: kategori ekle/kaydet
    Kategori kaydet(Kategori kategori);
}
