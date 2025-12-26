CREATE TRIGGER trg_IadeCezaHesapla
ON OduncIslemleri
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE 
        @OduncId INT,
        @KullaniciId INT,
        @TeslimTarihi DATE,
        @IadeTarihi DATE,
        @GecikmeGun INT;

    SELECT
        @OduncId = i.OduncId,
        @KullaniciId = i.KullaniciId,
        @TeslimTarihi = i.TeslimTarihi,
        @IadeTarihi = i.IadeTarihi
    FROM inserted i;

    IF @IadeTarihi IS NOT NULL
    BEGIN
        SET @GecikmeGun = DATEDIFF(DAY, @TeslimTarihi, @IadeTarihi);

        IF @GecikmeGun > 0
        BEGIN
            INSERT INTO Cezalar (KullaniciId, OduncId, GecikmeGun, Tutar)
            VALUES (@KullaniciId, @OduncId, @GecikmeGun, @GecikmeGun * 2);

            UPDATE Kullanicilar
            SET Borc = Borc + (@GecikmeGun * 2)
            WHERE KullaniciId = @KullaniciId;
        END;

        UPDATE OduncIslemleri
        SET Durum = 'IADE_EDILDI'
        WHERE OduncId = @OduncId;

        UPDATE KitapKopyalari
        SET Durum = 'MUSAIT'
        WHERE KopyaId = (SELECT KopyaId FROM inserted);
    END
END;
