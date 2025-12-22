package com.library.library.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library.entity.Kullanici;
import com.library.library.repository.KullaniciRepository;
import com.library.library.service.KullaniciService;

@Service
public class KullaniciServiceImpl implements KullaniciService {

    private final KullaniciRepository kullaniciRepository;

    public KullaniciServiceImpl(KullaniciRepository kullaniciRepository) {
        this.kullaniciRepository = kullaniciRepository;
    }

    @Override
    public List<Kullanici> tumKullanicilariGetir() {
        return kullaniciRepository.findAll();
    }

    @Override
    public List<Kullanici> aktifKullanicilariGetir() {
        return kullaniciRepository.findByAktifMiTrue();
    }

    @Override
    public Kullanici kaydet(Kullanici kullanici) {
        kullanici.setOlusturmaTarihi(LocalDateTime.now());
        kullanici.setAktifMi(true);
        return kullaniciRepository.save(kullanici);
    }
}
