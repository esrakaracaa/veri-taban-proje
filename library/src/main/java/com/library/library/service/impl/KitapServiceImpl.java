package com.library.library.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library.entity.Kitap;
import com.library.library.repository.KitapRepository;
import com.library.library.service.KitapService;

@Service
public class KitapServiceImpl implements KitapService {
    

    private final KitapRepository kitapRepository;

    public KitapServiceImpl(KitapRepository kitapRepository) {
        this.kitapRepository = kitapRepository;
    }

    @Override
    public List<Kitap> tumKitaplariGetir() {
        return kitapRepository.findAll();
    }

    @Override
    public Kitap kaydet(Kitap kitap) {
        return kitapRepository.save(kitap);
    }
    @Override
public List<Kitap> aktifKitaplariGetir() {
    return kitapRepository.findByAktifMiTrue();
}
@Override
    public void sil(Integer id) {
        kitapRepository.deleteById(id);
    }
    @Override
    public Kitap idIleGetir(Integer id) {
        // Kitabı bulamazsa null döner, istersen hata da fırlatabiliriz ama şimdilik basit tutalım
        return kitapRepository.findById(id).orElse(null);
    }
}

