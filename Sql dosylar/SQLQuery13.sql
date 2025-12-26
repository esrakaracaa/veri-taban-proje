USE LibraryDB;
CREATE USER library_user FOR LOGIN library_user;
ALTER ROLE db_owner ADD MEMBER library_user;

