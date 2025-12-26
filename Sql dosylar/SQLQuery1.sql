CREATE TABLE ogrenciler(
OgrenciID INT PRIMARY KEY
IDENTITY(1,1),
Ad NVARCHAR(50),
Soyad NVARCHAR(50),
Yas INT

);
INSERT INTO ogrenciler(Ad,Soyad,Yas)
