package com.library.library.service;

import java.util.List;

import com.library.library.entity.Kullanici;

public interface KullaniciService {

    List<Kullanici> tumKullanicilariGetir();

    List<Kullanici> aktifKullanicilariGetir();

    Kullanici kaydet(Kullanici kullanici);
}
