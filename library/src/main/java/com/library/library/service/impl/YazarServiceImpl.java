package com.library.library.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library.entity.Yazar;
import com.library.library.repository.YazarRepository;
import com.library.library.service.YazarService;

@Service
public class YazarServiceImpl implements YazarService {

    private final YazarRepository yazarRepository;

    public YazarServiceImpl(YazarRepository yazarRepository) {
        this.yazarRepository = yazarRepository;
    }

    @Override
    public List<Yazar> tumYazarlariGetir() {
        return yazarRepository.findAll();
    }

    @Override
    public Yazar kaydet(Yazar yazar) {
        return yazarRepository.save(yazar);
    }
}

