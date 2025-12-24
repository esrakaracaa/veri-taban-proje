package com.library.library.service.impl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Kitap> tumKitaplariSayfaliGetir(Pageable pageable) {
        return kitapRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Kitap kaydet(Kitap kitap) {
        // Stok yönetimi: Adet 0 ise otomatik pasif yap
        if (kitap.getAdet() != null && kitap.getAdet() <= 0) {
            kitap.setAktifMi(false);
        } else if (kitap.getAdet() != null && kitap.getAdet() > 0) {
            kitap.setAktifMi(true);
        }
        return kitapRepository.save(kitap);
    }

    @Override
    public List<Kitap> aktifKitaplariGetir() {
        return kitapRepository.findByAktifMiTrue();
    }

    @Override
    @Transactional
    public void sil(Integer id) {
        kitapRepository.deleteById(id);
    }

    @Override
    public Kitap idIleGetir(Integer id) {
        return kitapRepository.findById(id).orElse(null);
    }

    /**
     * ✅ GÜNCELLENDİ: Sayfalı, Kategori Filtreli Arama Metodu
     * Bu metot KitapRepository içindeki @Query ile tanımladığımız 
     * gelişmiş arama metodunu kullanır.
     */
    @Override
    public Page<Kitap> sayfaliKitapAra(String keyword, Integer kategoriId, Pageable pageable) {
        // Repository katmanındaki @Query destekli metodumuza yönlendiriyoruz.
        // Parametreler (keyword, kategoriId, pageable) arayüzle tam uyumlu hale getirildi.
        return kitapRepository.sayfaliKitapAra(keyword, kategoriId, pageable);
    }

    @Override
    public List<Kitap> kitapAra(String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return kitapRepository.findByKitapAdiContainingIgnoreCase(keyword);
        }
        return kitapRepository.findAll();
    }
}