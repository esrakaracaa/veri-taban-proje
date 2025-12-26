CREATE VIEW vw_KitapListesi AS
SELECT
    k.KitapId,
    k.KitapAdi,
    y.AdSoyad AS YazarAdi,
    ka.Ad AS KategoriAdi,
    COUNT(CASE WHEN kk.Durum = 'MUSAIT' THEN 1 END) AS MusaitKopyaSayisi
FROM Kitaplar k
JOIN Yazarlar y ON y.YazarId = k.YazarId
JOIN Kategoriler ka ON ka.KategoriId = k.KategoriId
LEFT JOIN KitapKopyalari kk ON kk.KitapId = k.KitapId
GROUP BY k.KitapId, k.KitapAdi, y.AdSoyad, ka.Ad;
