CREATE USER jpaPlay identified by 123; 
grant resource, connect, create view, create synonym, create trigger, create procedure, create materialized view to jpaPlay;

drop user jpaPlay cascade;

Create resource on Glassfish 
asadmin create-jdbc-connection-pool --datasourceclassname oracle.jdbc.pool.OracleDataSource --restype javax.sql.DataSource --property url="jdbc\:oracle\:thin\:@localhost\:1521\:XE":user=jpaPlay:password=123 JpaPlayPool
asadmin create-jdbc-resource --connectionpoolid=JpaPlayPool jdbc/jpaPlay
