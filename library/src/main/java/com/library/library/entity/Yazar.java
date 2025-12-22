package com.library.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Yazarlar", schema = "dbo")
public class Yazar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "YazarId")
    private Integer yazarId;

    @Column(name = "AdSoyad", nullable = false)
    private String adSoyad;

    @Column(name = "AktifMi")
    private Boolean aktifMi;

    public Integer getYazarId() {
        return yazarId;
    }

    public void setYazarId(Integer yazarId) {
        this.yazarId = yazarId;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public Boolean getAktifMi() {
        return aktifMi;
    }

    public void setAktifMi(Boolean aktifMi) {
        this.aktifMi = aktifMi;
    }
}

