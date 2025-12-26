-- Durumu boþ olan kayýtlarý 'ÜYEDE' yapar, böylece kod hesaplamaya dahil eder
UPDATE dbo.OduncIslemleri SET Durum = 'ÜYEDE' WHERE Durum IS NULL OR Durum = '';