/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.glassfish.oracle.database.events.dcn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xix
 */

@Stateless
public class Service {
    @PersistenceContext(unitName = "cachedCoordinationUnit")
    protected EntityManager em;
    
    @Inject 
    private SingleTon singleTon;
    
    public CachedProperty read(String id) {
        return em.find(CachedProperty.class, id);
    }

    public void create(final CachedProperty cachedProperty) {
        em.persist(cachedProperty);
    }

    public void upadate(final CachedProperty cachedProperty) {
        em.merge(cachedProperty);
    }
    
    public void updatejdbc(final CachedProperty cachedProperty) throws SQLException {

        //final ResultSet tables = connection.getMetaData().getTables(null, null, "PARENT", null);
        try (Connection connection = singleTon.getConnection()) {
            String updateTableSQL = "UPDATE CACHED_PROPERTY SET SOME_VALUE = ? WHERE NAME = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateTableSQL);
            preparedStatement.setString(1, cachedProperty.getSomeValue());
            preparedStatement.setString(2, cachedProperty.getName());
            preparedStatement.executeUpdate();
        }
    }
}
