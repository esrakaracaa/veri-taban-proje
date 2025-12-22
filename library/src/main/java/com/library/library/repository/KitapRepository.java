package com.library.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.entity.Kitap;

public interface KitapRepository extends JpaRepository<Kitap, Integer> {

    // Aktif kitapları getir
    List<Kitap> findByAktifMiTrue();
}
