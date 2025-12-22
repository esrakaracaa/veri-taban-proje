package com.library.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Kategoriler", schema = "dbo")
public class Kategori {

    private Integer kategoriId;
    private String ad;
    private Boolean aktifMi;

    @Id
    
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "KategoriId")
public Integer getKategoriId() { return kategoriId; }

    public void setKategoriId(Integer kategoriId) { 
        this.kategoriId = kategoriId; 
    }

    @Column(name = "Ad")
    public String getAd() { 
        return ad; 
    }
    public void setAd(String ad) { 
        this.ad = ad; 
    }

    @Column(name = "AktifMi")
    public Boolean getAktifMi() { 
        return aktifMi; 
    }
    public void setAktifMi(Boolean aktifMi) { 
        this.aktifMi = aktifMi; 
    }
}

