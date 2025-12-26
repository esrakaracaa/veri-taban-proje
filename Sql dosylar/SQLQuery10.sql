USE [LibraryDB];
GO

-- Login var mý?
SELECT name FROM sys.sql_logins WHERE name = 'library_user';

-- DB user var mý?
SELECT name FROM sys.database_principals WHERE name = 'library_user';

-- Yoksa oluþtur ve yetki ver
IF NOT EXISTS (SELECT 1 FROM sys.database_principals WHERE name = 'library_user')
    CREATE USER [library_user] FOR LOGIN [library_user];

ALTER ROLE db_datareader ADD MEMBER [library_user];
ALTER ROLE db_datawriter ADD MEMBER [library_user];
ALTER ROLE db_ddladmin  ADD MEMBER [library_user]; -- tablolar vs. oluþturacaksan lazým
GO
