CREATE PROCEDURE sp_GecikenKitaplariGetir
    @KullaniciId INT
AS
BEGIN
    SELECT
        o.OduncId,
        k.KitapAdi,
        o.OduncTarihi,
        o.TeslimTarihi
    FROM OduncIslemleri o
    JOIN KitapKopyalari kk ON kk.KopyaId = o.KopyaId
    JOIN Kitaplar k ON k.KitapId = kk.KitapId
    WHERE o.KullaniciId = @KullaniciId
      AND o.IadeTarihi IS NULL
      AND o.TeslimTarihi < GETDATE();
END;
