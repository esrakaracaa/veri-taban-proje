package com.library.library.service;

import java.util.List;

import com.library.library.entity.Kullanici;

public interface KullaniciService {

    List<Kullanici> tumKullanicilariGetir();

    List<Kullanici> aktifKullanicilariGetir();

    Kullanici kaydet(Kullanici kullanici);
    
    void sil(Integer id);
    
    Kullanici idIleGetir(Integer id);

    // --- Yeni Eklenen Metodlar ---
    
    // Giriş yaparken e-posta ve şifre kontrolü için:
    Kullanici girisKontrol(String email, String sifre); 

    // Kayıt olma işlemi için (zaten kaydet var ama istersen ismini ayırabilirsin):
    Kullanici kullaniciKaydet(Kullanici kullanici); 

    Kullanici kullaniciBul(Integer id);

    
}