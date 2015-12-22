#!/bin/bash
#asadmin delete-jdbc-resource jdbc/cacheCordinationResource -p 9348
#asadmin delete-jdbc-connection-pool cacheCordinationResourcePool -p 9348
#asadmin create-jdbc-connection-pool --datasourceclassname oracle.jdbc.pool.OracleDataSource --restype javax.sql.DataSource --property url="jdbc\:oracle\:thin\:@localhost\:1521\:XE":user=cacheCordination:password=123 cacheCordinationResourcePool -p 9348
#asadmin create-jdbc-resource --connectionpoolid=cacheCordinationResourcePool jdbc/cacheCordinationResource -p 9348