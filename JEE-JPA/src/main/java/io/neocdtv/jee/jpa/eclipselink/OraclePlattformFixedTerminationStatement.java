/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jpa.eclipselink;

import org.eclipse.persistence.platform.database.OraclePlatform;

/**
 *
 * @author xix
 */
public class OraclePlattformFixedTerminationStatement extends OraclePlatform {
    @Override
    public String getStoredProcedureTerminationToken() {
        return ";";
    }
}
