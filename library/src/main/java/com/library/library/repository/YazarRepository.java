package com.library.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.entity.Yazar;

public interface YazarRepository extends JpaRepository<Yazar, Integer> {
}

