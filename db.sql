drop database diary;
drop user seung;

create database diary default character set utf8;
create user seung@'%' identified by '12341234';
grant all privileges on diary.* to seung@'%';
alter user seung@'%' identified with mysql_native_password by '12341234';
alter database diary default character set utf8;

