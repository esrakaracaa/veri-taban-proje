package com.library.library.service;

import java.util.List;

import com.library.library.entity.Kullanici;

public interface KullaniciService {

    List<Kullanici> tumKullanicilariGetir();

    List<Kullanici> aktifKullanicilariGetir();

    Kullanici kaydet(Kullanici kullanici);
    
    // Mevcut kodların altına, süslü parantez kapanmadan önce ekle:
void sil(Integer id);
Kullanici idIleGetir(Integer id);

}
