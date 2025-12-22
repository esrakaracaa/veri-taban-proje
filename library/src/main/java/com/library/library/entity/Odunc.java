package com.library.library.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OduncIslemleri", schema = "dbo")
public class Odunc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OduncId")
    private Integer oduncId;

    @Column(name = "KullaniciId", nullable = false)
    private Integer kullaniciId;

    @Column(name = "KopyaId", nullable = false)
    private Integer kopyaId;

    @Column(name = "OduncTarihi", nullable = false)
    private LocalDate oduncTarihi;

    @Column(name = "TeslimTarihi", nullable = false)
    private LocalDate teslimTarihi;

    @Column(name = "IadeTarihi")
    private LocalDate iadeTarihi;

    @Column(name = "Durum")
    private String durum; // örn: "ODUNCTE", "IADE_EDILDI"

    public Odunc() {}

    public Integer getOduncId() { return oduncId; }
    public void setOduncId(Integer oduncId) { this.oduncId = oduncId; }

    public Integer getKullaniciId() { return kullaniciId; }
    public void setKullaniciId(Integer kullaniciId) { this.kullaniciId = kullaniciId; }

    public Integer getKopyaId() { return kopyaId; }
    public void setKopyaId(Integer kopyaId) { this.kopyaId = kopyaId; }

    public LocalDate getOduncTarihi() { return oduncTarihi; }
    public void setOduncTarihi(LocalDate oduncTarihi) { this.oduncTarihi = oduncTarihi; }

    public LocalDate getTeslimTarihi() { return teslimTarihi; }
    public void setTeslimTarihi(LocalDate teslimTarihi) { this.teslimTarihi = teslimTarihi; }

    public LocalDate getIadeTarihi() { return iadeTarihi; }
    public void setIadeTarihi(LocalDate iadeTarihi) { this.iadeTarihi = iadeTarihi; }

    public String getDurum() { return durum; }
    public void setDurum(String durum) { this.durum = durum; }
}
