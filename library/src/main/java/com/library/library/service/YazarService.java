package com.library.library.service;

import java.util.List;

import com.library.library.entity.Yazar;

public interface YazarService {

    List<Yazar> tumYazarlariGetir();

    Yazar kaydet(Yazar yazar);
}
