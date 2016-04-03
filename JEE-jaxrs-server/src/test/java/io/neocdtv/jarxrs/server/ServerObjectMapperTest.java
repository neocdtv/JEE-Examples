/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import io.neocdtv.datamodel.Object1;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author xix
 */
public class ServerObjectMapperTest {

    private static final String JSON_WITH_UNKNOWN_PROPERTIES = "{\"firstName\": \"Krzysztof\", \"lastName\": \"Wolf\"}";
    private static final String JSON_WITH_WRONG_DATATYPE = "{\"firstName\": \"Krzysztof\", \"age\": \"23w\", \"cars\": \"1w\"}";


    public void unknownProperties() {
        try {
            ServerObjectMapper.INSTANCE.readValue(JSON_WITH_UNKNOWN_PROPERTIES, Object1.class);
        } catch (IOException ex) {
            Logger.getLogger(ServerObjectMapperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void wrongDatatype() {
        try {
            final Person person = ServerObjectMapper.INSTANCE.readValue(JSON_WITH_WRONG_DATATYPE, Person.class);
            System.out.println(ServerObjectMapper.INSTANCE.writeValueAsString(person));
        } catch (IOException ex) {
            Logger.getLogger(ServerObjectMapperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
