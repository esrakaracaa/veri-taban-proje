package com.library.library.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*; // Tüm web anotasyonları için

import com.library.library.dto.OduncAlRequest; 
import com.library.library.entity.Odunc;
import com.library.library.service.OduncService;

import java.util.List;

@RestController
@RequestMapping("/api/oduncler")
public class OduncController {

    private final OduncService oduncService;

    public OduncController(OduncService oduncService) {
        this.oduncService = oduncService;
    }

    /**
     * Tüm ödünç kayıtlarını sayfalı ve sıralı getirir.
     */
    @GetMapping
    public Page<Odunc> tumunuGetir(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        // DÜZELTME: Entity sınıfımızda alan adı "durum" olduğu için 
        // "sistemDurumu" yerine "durum" olarak güncellendi.
        Pageable pageable = PageRequest.of(page, size, 
            Sort.by("durum").descending().and(Sort.by("id").descending()));
            
        return oduncService.tumunuGetir(pageable);
    }

    @GetMapping("/aktif")
    public List<Odunc> aktifleriGetir() {
        return oduncService.aktifOduncleriGetir();
    }

    /**
     * Kitap ödünç alma işlemi (Postman ile test edilebilir)
     */
    @PostMapping("/al")
    public Odunc oduncAl(@RequestBody OduncAlRequest req) {
        // DÜZELTME: OduncService.oduncAl metodu (kullaniciId, kitapId) bekler.
        // DTO'nuzda getKitapId() olduğundan emin olun.
        return oduncService.oduncAl(req.getKullaniciId(), req.getKitapId());
    }

    /**
     * Kitap teslim etme işlemi (Ceza hesaplamasını tetikler)
     */
    @PostMapping("/teslim/{oduncId}")
    public Odunc teslimEt(@PathVariable Integer oduncId) {
        // HATA ÇÖZÜMÜ: OduncService'deki teslimEt artık 'Odunc' nesnesi döndüğü için 
        // "cannot return a void result" hatası giderildi.
        return oduncService.teslimEt(oduncId);
    }
}