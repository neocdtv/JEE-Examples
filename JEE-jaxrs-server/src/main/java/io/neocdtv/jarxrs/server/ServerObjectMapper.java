/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.logging.Logger;

/**
 *
 * @author xix
 */

public class ServerObjectMapper {

    private static final Logger LOGGER = Logger.getLogger(ServerObjectMapper.class.getName());

    public static final ObjectMapper INSTANCE = new ObjectMapper();

    static {
        INSTANCE.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        INSTANCE.configure(SerializationFeature.INDENT_OUTPUT, true);
    }
}
