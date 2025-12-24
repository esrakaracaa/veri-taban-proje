package com.library.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    // Formlardan veri alırken kullanmaya devam etmek için duruyor
    @Column(name = "YazarId", nullable = false)
    private Integer yazarId;

    @Column(name = "KategoriId", nullable = false)
    private Integer kategoriId;

    @Column(name = "BasimYili")
    private Integer basimYili;

    @Column(name = "adet")
    private Integer adet = 10;

    @Column(name = "AktifMi")
    private Boolean aktifMi = true;

    // ✅ YENİ: Yazarlar tablosuyla nesne bazlı ilişki (Thymeleaf burayı kullanır)
    @ManyToOne
    @JoinColumn(name = "YazarId", insertable = false, updatable = false)
    private Yazar yazar;

    // ✅ YENİ: Kategoriler tablosuyla nesne bazlı ilişki
    @ManyToOne
    @JoinColumn(name = "KategoriId", insertable = false, updatable = false)
    private Kategori kategori;

    // --- GETTER VE SETTER METODLARI ---

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

    public Integer getAdet() { return adet; }
    public void setAdet(Integer adet) { this.adet = adet; }

    public Boolean getAktifMi() { return aktifMi; }
    public void setAktifMi(Boolean aktifMi) { this.aktifMi = aktifMi; }

    // ✅ YENİ GETTER/SETTERLAR (Hatanın çözümü için kritik)
    public Yazar getYazar() { return yazar; }
    public void setYazar(Yazar yazar) { this.yazar = yazar; }

    public Kategori getKategori() { return kategori; }
    public void setKategori(Kategori kategori) { this.kategori = kategori; }
}