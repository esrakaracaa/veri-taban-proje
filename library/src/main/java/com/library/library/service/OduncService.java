package com.library.library.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.library.library.entity.Odunc;

public interface OduncService {
    Odunc oduncAl(Integer kullaniciId, Integer kitapId);
    
    // HATA ÇÖZÜMÜ: OduncController'daki "cannot be converted to Odunc" hatası için
    // metodun dönüş tipini 'Odunc' yapıyoruz.
    Odunc teslimEt(Integer oduncId); 
    
    void iadeTalebiOlustur(Integer oduncId);
    Page<Odunc> tumunuGetir(Pageable pageable);
    Page<Odunc> kullaniciyaGoreGetir(Integer kullaniciId, Pageable pageable);
    List<Odunc> kullaniciyaGoreGetir(Integer kullaniciId);
    List<Odunc> aktifOduncleriGetir();
}