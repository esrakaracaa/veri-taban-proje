UPDATE OduncIslemleri
SET IadeTarihi = DATEADD(DAY, 5, TeslimTarihi)
WHERE OduncId = 1;
