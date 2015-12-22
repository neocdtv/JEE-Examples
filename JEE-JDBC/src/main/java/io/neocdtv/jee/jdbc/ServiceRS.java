/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author xix
 */
@Path("service")
@ApplicationScoped
public class ServiceRS {

    @Resource(lookup = "jdbc/jpaPlay")
    private DataSource dataSource;

    @GET
    public Response read(@QueryParam("id") Long id) throws SQLException {
        final String attributeOne = "ATTRIBUTEONE";
        final String attributeTwo = "ATTRIBUTETWO";

        //final ResultSet tables = connection.getMetaData().getTables(null, null, "PARENT", null);
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement =
                    connection.prepareStatement(String.format("SELECT %s FROM PARENT WHERE ID = ?", attributeOne + ", " + attributeTwo));
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                
                String attributeOneValue = rs.getString(attributeOne);
                String attributeTwoValue = rs.getString(attributeTwo);
                
                System.out.println("userid : " + attributeOneValue);
                System.out.println("username : " + attributeTwoValue);
                
            }
        }
        return Response.ok().build();
    }
}
