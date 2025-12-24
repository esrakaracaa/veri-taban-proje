package com.library.library.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Kullanici kaydet(Kullanici kullanici) {
        // 1. Tarih kontrolü: Yeni kayıtsa şu anki tarihi ata
        if (kullanici.getOlusturmaTarihi() == null) {
            kullanici.setOlusturmaTarihi(LocalDateTime.now());
        }
        
        // 2. İlk kayıt için varsayılan değerleri kontrol et
        if (kullanici.getAktifMi() == null) {
            kullanici.setAktifMi(true);
        }
        
        if (kullanici.getBorc() == null) {
            kullanici.setBorc(BigDecimal.ZERO);
        }

        // 3. ROL Kontrolü: Boşsa varsayılan olarak 'UYE' ata
        if (kullanici.getRol() == null || kullanici.getRol().isEmpty()) {
            kullanici.setRol("UYE");
        }
        
        return kullaniciRepository.save(kullanici);
    }

    @Override
    @Transactional
    public void sil(Integer id) {
        kullaniciRepository.deleteById(id);
    }

    @Override
    public Kullanici idIleGetir(Integer id) {
        return kullaniciRepository.findById(id).orElse(null);
    }

    /**
     * ✅ DÜZELTİLDİ: Entity içindeki eposta alanı ile senkronize edildi.
     * image_56e8c0.png dosyasındaki hatayı çözen ana kısımdır.
     */
    @Override
    public Kullanici girisKontrol(String eposta, String sifre) {
        // Repository'deki findByEpostaAndSifre metodunu çağırır
        return kullaniciRepository.findByEpostaAndSifre(eposta, sifre);
    }

    @Override
    @Transactional
    public Kullanici kullaniciKaydet(Kullanici kullanici) {
        return kaydet(kullanici);
    }

    /**
     * ✅ Controller içindeki kırmızı çizgileri gideren metot.
     * image_567ffd.png dosyasındaki hatayı çözen kısımdır.
     */
    @Override
    public Kullanici kullaniciBul(Integer id) {
        return kullaniciRepository.findById(id).orElse(null);
    }
}