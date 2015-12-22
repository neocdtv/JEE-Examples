/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.neocdtv.jee.jpa.entity.Parent;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author xix
 */
@Stateless
public class Service {

    @PersistenceContext(unitName = "jpaPlay")
    private EntityManager em;

    public Parent create(final Parent parent) {
        em.persist(parent);
        return parent;
    }

    public Parent update(final Parent parent) {
        em.merge(parent);
        return parent;
    }
    
    public Parent readPartial(final Long id, final EntityGraph<Parent> entityGraph) throws JsonProcessingException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException {
        Map<String, Object> hints = new HashMap<>();
        hints.put(QueryHints.JPA_LOAD_GRAPH, entityGraph);
        return em.find(Parent.class, id, hints);
    }

    public Parent updatePartial(final Parent parentParam, final String[] nodes) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }

    public Parent read(final Long id) {
        return em.find(Parent.class, id);
    }

    public List<Parent> read() {
        final TypedQuery<Parent> createNamedQuery = em.createNamedQuery(Parent.QUERY_NAME_READ_ALL, Parent.class);
        return createNamedQuery.getResultList();
    }
}
