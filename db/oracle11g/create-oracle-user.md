```shell
sqlplus sys/SYSTEM123@xe as sysdba;
create user root identified by root;
alter user root account unlock;
grant create user,drop user,alter user ,create any view , drop any view,exp_full_database,imp_full_database, dba, connect,resource,create session to root;
```