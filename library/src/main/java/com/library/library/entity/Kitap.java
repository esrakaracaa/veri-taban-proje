package com.library.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Kitaplar", schema = "dbo")
public class Kitap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KitapId")
    private Integer kitapId;

    @Column(name = "KitapAdi", nullable = false)
    private String kitapAdi;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "YazarId", nullable = false)
    private Integer yazarId;

    @Column(name = "KategoriId", nullable = false)
    private Integer kategoriId;

    @Column(name = "BasimYili")
    private Integer basimYili;

    @Column(name = "AktifMi")
    private Boolean aktifMi;

    public Integer getKitapId() { return kitapId; }
    public void setKitapId(Integer kitapId) { this.kitapId = kitapId; }

    public String getKitapAdi() { return kitapAdi; }
    public void setKitapAdi(String kitapAdi) { this.kitapAdi = kitapAdi; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getYazarId() { return yazarId; }
    public void setYazarId(Integer yazarId) { this.yazarId = yazarId; }

    public Integer getKategoriId() { return kategoriId; }
    public void setKategoriId(Integer kategoriId) { this.kategoriId = kategoriId; }

    public Integer getBasimYili() { return basimYili; }
    public void setBasimYili(Integer basimYili) { this.basimYili = basimYili; }

    public Boolean getAktifMi() { return aktifMi; }
    public void setAktifMi(Boolean aktifMi) { this.aktifMi = aktifMi; }
}
