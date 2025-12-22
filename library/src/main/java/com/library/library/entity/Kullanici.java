package com.library.library.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Kullanicilar", schema = "dbo")
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KullaniciId")
    private Integer kullaniciId;

    @Column(name = "AdSoyad", nullable = false)
    private String adSoyad;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Sifre", nullable = false)
    private String sifre;

    @Column(name = "Rol", nullable = false)
    private String rol;

    @Column(name = "AktifMi")
    private Boolean aktifMi;

    @Column(name = "Borc")
    private BigDecimal borc;

    @Column(name = "OlusturmaTarihi")
    private LocalDateTime olusturmaTarihi;

    // GETTER - SETTER
    public Integer getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(Integer kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Boolean getAktifMi() {
        return aktifMi;
    }

    public void setAktifMi(Boolean aktifMi) {
        this.aktifMi = aktifMi;
    }

    public BigDecimal getBorc() {
        return borc;
    }

    public void setBorc(BigDecimal borc) {
        this.borc = borc;
    }

    public LocalDateTime getOlusturmaTarihi() {
        return olusturmaTarihi;
    }

    public void setOlusturmaTarihi(LocalDateTime olusturmaTarihi) {
        this.olusturmaTarihi = olusturmaTarihi;
    }
}

