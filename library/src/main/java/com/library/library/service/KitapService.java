package com.library.library.service;

import java.util.List;

import com.library.library.entity.Kitap;

public interface KitapService {

    Kitap idIleGetir(Integer id);
    void sil(Integer id);
    List<Kitap> tumKitaplariGetir();
     List<Kitap> aktifKitaplariGetir();
    Kitap kaydet(Kitap kitap);
}

