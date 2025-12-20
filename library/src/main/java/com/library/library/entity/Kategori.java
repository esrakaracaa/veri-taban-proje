package com.library.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Kategoriler", schema = "dbo")
public class Kategori {

    @Id
    @Column(name = "KategoriId")
    private Integer kategoriId;

    @Column(name = "Ad")
    private String ad;

    @Column(name = "AktifMi")
    private Boolean aktifMi;

    public Integer getKategoriId() { return kategoriId; }
    public void setKategoriId(Integer kategoriId) { this.kategoriId = kategoriId; }

    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }

    public Boolean getAktifMi() { return aktifMi; }
    public void setAktifMi(Boolean aktifMi) { this.aktifMi = aktifMi; }
}

