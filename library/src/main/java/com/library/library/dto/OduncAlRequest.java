package com.library.library.dto;

public class OduncAlRequest {
    private Integer kullaniciId;
    private Integer kitapId; // getKitapId() hatası için eklendi
    private Integer kopyaId;

    public Integer getKullaniciId() { return kullaniciId; }
    public void setKullaniciId(Integer kullaniciId) { this.kullaniciId = kullaniciId; }

    public Integer getKitapId() { return kitapId; }
    public void setKitapId(Integer kitapId) { this.kitapId = kitapId; }

    public Integer getKopyaId() { return kopyaId; }
    public void setKopyaId(Integer kopyaId) { this.kopyaId = kopyaId; }
}