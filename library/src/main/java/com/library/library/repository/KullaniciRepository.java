package com.library.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.entity.Kullanici;

public interface KullaniciRepository 
        extends JpaRepository<Kullanici, Integer> {

    List<Kullanici> findByAktifMiTrue();

    Kullanici findByEmail(String email);

    
}
