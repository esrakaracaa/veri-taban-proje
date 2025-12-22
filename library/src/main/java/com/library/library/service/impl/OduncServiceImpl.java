package com.library.library.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.library.entity.KitapKopya;
import com.library.library.entity.Odunc;
import com.library.library.repository.KitapKopyaRepository;
import com.library.library.repository.OduncRepository;
import com.library.library.service.OduncService;

@Service
public class OduncServiceImpl implements OduncService {

    private final OduncRepository oduncRepository;
    private final KitapKopyaRepository kitapKopyaRepository;

    // KitapKopyalari.Durum değerleri
    private static final String KOPYA_MUSAIT = "MUSAIT";
    private static final String KOPYA_ODUNCTE = "ODUNCTE";

    // OduncIslemleri.Durum değerleri
    private static final String ODUNC_AKTIF = "ODUNCTE";
    private static final String ODUNC_TESLIM = "TESLIM";

    public OduncServiceImpl(OduncRepository oduncRepository, KitapKopyaRepository kitapKopyaRepository) {
        this.oduncRepository = oduncRepository;
        this.kitapKopyaRepository = kitapKopyaRepository;
    }

    @Override
    public List<Odunc> tumunuGetir() {
        return oduncRepository.findAll();
    }

    @Override
    public List<Odunc> aktifOduncleriGetir() {
        return oduncRepository.findByIadeTarihiIsNull();
    }

    @Override
    @Transactional
    public Odunc oduncAl(Integer kullaniciId, Integer kopyaId) {
        if (kullaniciId == null || kopyaId == null) {
            throw new IllegalArgumentException("kullaniciId ve kopyaId zorunludur.");
        }

        // 1) Kopya var mı?
        KitapKopya kopya = kitapKopyaRepository.findById(kopyaId)
                .orElseThrow(() -> new IllegalArgumentException("Kopya bulunamadı. kopyaId=" + kopyaId));

        // 2) Kopya MUSAIT mi?
        if (kopya.getDurum() == null || !KOPYA_MUSAIT.equalsIgnoreCase(kopya.getDurum())) {
            throw new IllegalArgumentException("Bu kopya müsait değil. kopyaId=" + kopyaId + " durum=" + kopya.getDurum());
        }

        // 3) Aktif ödünç var mı? (ek güvenlik)
        if (oduncRepository.existsByKopyaIdAndIadeTarihiIsNull(kopyaId)) {
            throw new IllegalArgumentException("Bu kopya zaten ödünçte görünüyor. kopyaId=" + kopyaId);
        }

        // 4) Ödünç kaydı oluştur
        LocalDate bugun = LocalDate.now();

        Odunc odunc = new Odunc();
        odunc.setKullaniciId(kullaniciId);
        odunc.setKopyaId(kopyaId);
        odunc.setOduncTarihi(bugun);
        odunc.setTeslimTarihi(bugun.plusDays(14)); // 14 gün sonra
        odunc.setIadeTarihi(null);
        odunc.setDurum(ODUNC_AKTIF);

        Odunc kayit = oduncRepository.save(odunc);

        // 5) Kopya durumunu ODUNCTE yap
        kopya.setDurum(KOPYA_ODUNCTE);
        kitapKopyaRepository.save(kopya);

        return kayit;
    }

    @Override
    @Transactional
    public Odunc teslimEt(Integer oduncId) {
        if (oduncId == null) {
            throw new IllegalArgumentException("oduncId zorunludur.");
        }

        Odunc odunc = oduncRepository.findById(oduncId)
                .orElseThrow(() -> new IllegalArgumentException("Ödünç kaydı bulunamadı. oduncId=" + oduncId));

        if (odunc.getIadeTarihi() != null) {
            throw new IllegalArgumentException("Bu ödünç zaten teslim edilmiş. oduncId=" + oduncId);
        }

        // 1) iade et
        odunc.setIadeTarihi(LocalDate.now());
        odunc.setDurum(ODUNC_TESLIM);
        Odunc guncel = oduncRepository.save(odunc);

        // 2) kopyayı tekrar MUSAIT yap
        Integer kopyaId = odunc.getKopyaId();
        KitapKopya kopya = kitapKopyaRepository.findById(kopyaId)
                .orElseThrow(() -> new IllegalArgumentException("Kopya bulunamadı. kopyaId=" + kopyaId));

        kopya.setDurum(KOPYA_MUSAIT);
        kitapKopyaRepository.save(kopya);

        return guncel;
    }
}
