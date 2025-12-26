-- 1) Login þifresini sabitle + policy kapat
ALTER LOGIN library_user 
WITH PASSWORD = '12345',
CHECK_POLICY = OFF,
CHECK_EXPIRATION = OFF;

-- 2) Login aktif olsun
ALTER LOGIN library_user ENABLE;

-- 3) Default DB
ALTER LOGIN library_user WITH DEFAULT_DATABASE = LibraryDB;

-- 4) DB içindeki role'a ekle (user zaten var)
USE LibraryDB;
ALTER ROLE db_owner ADD MEMBER library_user;
