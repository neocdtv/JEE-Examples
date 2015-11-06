/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jpa.eclipselink;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sessions.Session;

public class EclipseLinkSessionCustomizer implements SessionCustomizer {

    @Override
    public void customize(Session session) throws Exception {
        listPropopertiesInPersitenceXml(session);
        addPropertiesToPersistenceXml(session);
    }

    public void listPropopertiesInPersitenceXml(Session session) {
        final Map<Object, Object> properties = session.getProperties();
        final Set<Object> keySet = properties.keySet();
        for (Object key : keySet) {
            final Object property = properties.get(key);
            System.out.println("key: " + key + ", value: " + property);
        }
    }

    public void addPropertiesToPersistenceXml(Session session) {
        try {
            // not all properties can be set here, why?
            InitialContext context = new InitialContext();
            
            // Check if coordination can be set here
            Properties p = System.getProperties();
             if (p.getProperty("COORDINATION_RMI_URL") != null) {
               
             session.setProperty(PersistenceUnitProperties.COORDINATION_RMI_URL, p.getProperty("COORDINATION_RMI_URL"));
             session.setProperty(PersistenceUnitProperties.COORDINATION_PROTOCOL, "rmi");
             session.setProperty(PersistenceUnitProperties.COORDINATION_NAMING_SERVICE, "rmi");
             }
             
        } catch (NamingException ex) {
            Logger.getLogger(EclipseLinkSessionCustomizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
