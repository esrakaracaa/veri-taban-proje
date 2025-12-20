package com.library.library.service.impl;

import com.library.library.entity.Kategori;
import com.library.library.repository.KategoriRepository;
import com.library.library.service.KategoriService;

import org.springframework.stereotype.Service;

import java.util.List;

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
}
