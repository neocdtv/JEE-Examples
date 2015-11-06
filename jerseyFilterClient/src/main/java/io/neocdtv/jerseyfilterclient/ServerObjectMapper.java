/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jerseyfilterclient;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author xix
 */
public class ServerObjectMapper {

    public static final ObjectMapper INSTANCE = new ObjectMapper();

    static {
    // use this block to customize the object mapper
    }

    private ServerObjectMapper() {
        // prevent instance
    }
}
