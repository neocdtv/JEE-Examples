/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.glassfish.oracle.database.events.dcn;

import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.dcn.DatabaseChangeEvent;
import oracle.jdbc.dcn.DatabaseChangeListener;
import oracle.jdbc.dcn.DatabaseChangeRegistration;

/**
 *
 * @author xix
 */

@Singleton
@Startup
public class SingleTon {
    
    @Resource(lookup = "jdbc/cacheCordinationResource")
    DataSource oracleDataSource;
        
    private Logger logger = Logger.getLogger(SingleTon.class.getName());

    public OracleConnection getConnection() {
        OracleConnection connection = null;
        logger.log(Level.INFO, "init DCN");
        try {
            connection = oracleDataSource.getConnection().unwrap(OracleConnection.class);
            Properties prop = new Properties();
            prop.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS, "true");
            DatabaseChangeRegistration dcr = connection.registerDatabaseChangeNotification(prop);

            dcr.addListener(new DatabaseChangeListener() {

                @Override
                public void onDatabaseChangeNotification(DatabaseChangeEvent dce) {
                    System.out.println("Changed row id : " + dce.getTableChangeDescription()[0].getRowChangeDescription()[0].getRowid().stringValue());
                }
            });

        } catch (SQLException ex) {
            Logger.getLogger(SingleTon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
    
}
