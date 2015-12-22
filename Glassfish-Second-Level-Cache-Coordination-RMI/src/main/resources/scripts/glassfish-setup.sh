#!/bin/bash

asadmin create-cluster -p 9348 cluster-cache-coordination
asadmin create-local-instance -p 9348 --cluster cluster-cache-coordination i0-cache-coordination
asadmin create-local-instance -p 9348 --cluster cluster-cache-coordination i1-cache-coordination

asadmin create-jdbc-connection-pool -p 9348 --datasourceclassname oracle.jdbc.pool.OracleDataSource --restype javax.sql.DataSource --property url="jdbc\:oracle\:thin\:@localhost\:1521\:XE":user=cacheCordination:password=123 cacheCordinationPool
asadmin create-jdbc-resource -p 9348 --connectionpoolid=cacheCordinationPool jdbc/cacheCordinationResource
asadmin create-resource-ref -p 9348 --target cluster-cache-coordination jdbc/cacheCordinationResource

# configure cache coordination for L2 over RMI
asadmin create-system-properties -p 9348 --target i0-cache-coordination "eclipselink.cache.coordination.rmi.url=rmi\://localhost\:28686/"
asadmin create-system-properties -p 9348 --target i1-cache-coordination "eclipselink.cache.coordination.rmi.url=rmi\://localhost\:28687/"