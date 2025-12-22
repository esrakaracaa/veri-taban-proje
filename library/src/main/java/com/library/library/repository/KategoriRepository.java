package com.library.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.entity.Kategori;

public interface KategoriRepository extends JpaRepository<Kategori, Integer> {

    // AktifMi = true olanları getirir
    List<Kategori> findByAktifMiTrue();
}


