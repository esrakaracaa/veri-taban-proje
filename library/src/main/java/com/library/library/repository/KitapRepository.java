package com.library.library.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.library.library.entity.Kitap;

public interface KitapRepository extends JpaRepository<Kitap, Integer> {
    List<Kitap> findByAktifMiTrue();
    List<Kitap> findByKitapAdiContainingIgnoreCase(String keyword); // Bu eksikti

    @Query("SELECT k FROM Kitap k WHERE " +
           "(:keyword IS NULL OR k.kitapAdi LIKE %:keyword% OR k.isbn LIKE %:keyword%) AND " +
           "(:kategoriId IS NULL OR k.kategori.kategoriId = :kategoriId) AND k.aktifMi = true")
    Page<Kitap> sayfaliKitapAra(@Param("keyword") String keyword, 
                                 @Param("kategoriId") Integer kategoriId, 
                                 Pageable pageable);
}