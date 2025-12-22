package com.library.library.service;

import java.util.List;

import com.library.library.entity.Kitap;

public interface KitapService {
    List<Kitap> tumKitaplariGetir();
     List<Kitap> aktifKitaplariGetir();
    Kitap kaydet(Kitap kitap);
}

