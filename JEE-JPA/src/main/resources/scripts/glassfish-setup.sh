#!/bin/bash
asadmin delete-jdbc-resource jdbc/jpaPlay -p 4848
asadmin delete-jdbc-connection-pool JpaPlayPool -p 4848
asadmin create-jdbc-connection-pool --datasourceclassname oracle.jdbc.pool.OracleDataSource --restype javax.sql.DataSource --property url="jdbc\:oracle\:thin\:@localhost\:1521\:XE":user=jpaPlay:password=123 JpaPlayPool -p 4848
asadmin create-jdbc-resource --connectionpoolid=JpaPlayPool jdbc/jpaPlay -p 4848