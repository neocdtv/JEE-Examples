/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.neocdtv.domain.customer.Customer;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author xix
 */
@Stateless
public class Service {

    @PersistenceContext(unitName = "jpaPlay")
    private EntityManager em;
    
    public Customer readPartial(final Long id, final EntityGraph<Customer> entityGraph) throws JsonProcessingException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException {
        Map<String, Object> hints = new HashMap<>();
        hints.put(QueryHints.JPA_LOAD_GRAPH, entityGraph);
        return em.find(Customer.class, id, hints);
    }

    public <T> T read(final Class<T> clazz, final Long id) {
        return em.find(clazz, id);
    }
}
