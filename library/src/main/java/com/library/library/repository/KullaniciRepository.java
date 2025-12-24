package com.library.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.entity.Kullanici;

public interface KullaniciRepository extends JpaRepository<Kullanici, Integer> {
    List<Kullanici> findByAktifMiTrue();

    // ✅ DÜZELTİLDİ: email yerine eposta kullanıldı
    Kullanici findByEposta(String eposta);

    // ✅ DÜZELTİLDİ: Giriş işlemi için eposta ve şifre kontrolü
    Kullanici findByEpostaAndSifre(String eposta, String sifre);
}