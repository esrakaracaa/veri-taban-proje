package com.library.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "KitapKopyalari", schema = "dbo")
public class KitapKopya {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KopyaId")
    private Integer kopyaId;

    @Column(name = "KitapId", nullable = false)
    private Integer kitapId;

    @Column(name = "Barkod")
    private String barkod;

    @Column(name = "RafBilgisi")
    private String rafBilgisi;

    @Column(name = "Durum")
    private String durum;

    public Integer getKopyaId() { return kopyaId; }
    public void setKopyaId(Integer kopyaId) { this.kopyaId = kopyaId; }

    public Integer getKitapId() { return kitapId; }
    public void setKitapId(Integer kitapId) { this.kitapId = kitapId; }

    public String getBarkod() { return barkod; }
    public void setBarkod(String barkod) { this.barkod = barkod; }

    public String getRafBilgisi() { return rafBilgisi; }
    public void setRafBilgisi(String rafBilgisi) { this.rafBilgisi = rafBilgisi; }

    public String getDurum() { return durum; }
    public void setDurum(String durum) { this.durum = durum; }
}
