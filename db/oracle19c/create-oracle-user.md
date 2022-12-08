```shell
docker exec -it booster-oracle /bin/bash
mkdir /opt/oracle/oradata/ORCLCDB/booster
sqlplus sys/SYSTEM123@ORCLCDB as sysdba;

show pdbs;
create pluggable database booster admin user booster_admin identified by admin ROLES=(CONNECT) create_file_dest='/opt/oracle/oradata/ORCLCDB/booster';
show pdbs;
alter pluggable database booster open;
show pdbs;
alter session set container=booster;
show pdbs;
create tablespace BOOSTER_DATA datafile '/opt/oracle/oradata/ORCLCDB/booster/BOOSTER_DATA.DBF' size 5000m;
alter database datafile '/opt/oracle/oradata/ORCLCDB/booster/BOOSTER_DATA.DBF' resize 8000m;
create user root identified by root;
grant unlimited tablespace to root;
alter user root default tablespace BOOSTER_DATA;
grant create session,connect,resource,create table,create tablespace,create view to root;

sqlplus sys/SYSTEM123@ORCLCDB as sysdba;
drop user root;
alter pluggable database booster close;
drop pluggable database booster including datafiles;
```