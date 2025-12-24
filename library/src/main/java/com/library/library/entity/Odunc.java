package com.library.library.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "OduncIslemleri", schema = "dbo")
public class Odunc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OduncId")
    private Integer id;

    @Column(name = "KullaniciId", nullable = false)
    private Integer kullaniciId;

    @Column(name = "KitapId", nullable = false) 
    private Integer kitapId;

    @Column(name = "KopyaId")
    private Integer kopyaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "KitapId", insertable = false, updatable = false)
    private Kitap kitap;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "KullaniciId", insertable = false, updatable = false)
    private Kullanici kullanici;

    // 1 dakikalık ceza testi için tüm tarihler LocalDateTime olmalıdır
    @Column(name = "OduncTarihi", nullable = false)
    private LocalDateTime oduncTarihi;

    @Column(name = "BeklenenIadeTarihi")
    private LocalDateTime beklenenIadeTarihi;

    @Column(name = "TeslimTarihi")
    private LocalDateTime teslimTarihi;

    @Column(name = "IadeTarihi")
    private LocalDateTime iadeTarihi;

    @Column(name = "Durum")
    private String durum;

    @Column(name = "CezaMiktari")
    private Double cezaMiktari = 0.0;

    public Odunc() {
        this.oduncTarihi = LocalDateTime.now();
        this.durum = "ÜYEDE";
    }

    // --- GETTER VE SETTERLAR (Eksiksiz) ---

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getKullaniciId() { return kullaniciId; }
    public void setKullaniciId(Integer kullaniciId) { this.kullaniciId = kullaniciId; }
    public Integer getKitapId() { return kitapId; }
    public void setKitapId(Integer kitapId) { this.kitapId = kitapId; }
    public Integer getKopyaId() { return kopyaId; }
    public void setKopyaId(Integer kopyaId) { this.kopyaId = kopyaId; }
    public Kitap getKitap() { return kitap; }
    public Kullanici getKullanici() { return kullanici; }

    public LocalDateTime getOduncTarihi() { return oduncTarihi; }
    public void setOduncTarihi(LocalDateTime oduncTarihi) { this.oduncTarihi = oduncTarihi; }
    public LocalDateTime getBeklenenIadeTarihi() { return beklenenIadeTarihi; }
    public void setBeklenenIadeTarihi(LocalDateTime beklenenIadeTarihi) { this.beklenenIadeTarihi = beklenenIadeTarihi; }
    public LocalDateTime getTeslimTarihi() { return teslimTarihi; }
    public void setTeslimTarihi(LocalDateTime teslimTarihi) { this.teslimTarihi = teslimTarihi; }

    // image_650f62.png'deki 'undefined method' hatasını çözen kritik metot
    public LocalDateTime getIadeTarihi() { return iadeTarihi; }
    public void setIadeTarihi(LocalDateTime iadeTarihi) { this.iadeTarihi = iadeTarihi; }

    public String getDurum() { return durum; }
    public void setDurum(String durum) { this.durum = durum; }
    public Double getCezaMiktari() { return cezaMiktari; }
    public void setCezaMiktari(Double cezaMiktari) { this.cezaMiktari = cezaMiktari; }
}