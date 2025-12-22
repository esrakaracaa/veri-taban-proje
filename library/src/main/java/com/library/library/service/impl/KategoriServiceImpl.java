package com.library.library.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library.entity.Kategori;
import com.library.library.repository.KategoriRepository;
import com.library.library.service.KategoriService;

@Service
public class KategoriServiceImpl implements KategoriService {

    private final KategoriRepository kategoriRepository;

    public KategoriServiceImpl(KategoriRepository kategoriRepository) {
        this.kategoriRepository = kategoriRepository;
    }

    @Override
    public List<Kategori> tumKategorileriGetir() {
        return kategoriRepository.findAll();
    }
      // ✅ yeni: aktifleri getir
    @Override
    public List<Kategori> aktifKategorileriGetir() {
        return kategoriRepository.findByAktifMiTrue();
    }

    // ✅ yeni: kaydet (POST için lazım)
    @Override
    public Kategori kaydet(Kategori kategori) {
        return kategoriRepository.save(kategori);
    }
}
