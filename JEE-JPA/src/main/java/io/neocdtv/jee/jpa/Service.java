/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.neocdtv.jee.jpa.entity.onetomany.bi.ParentBiOne;
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

    public ParentBiOne create(final ParentBiOne parent) {
        em.persist(parent);
        return parent;
    }

    public ParentBiOne update(final ParentBiOne parent) {
        em.merge(parent);
        return parent;
    }
    
    public ParentBiOne readPartial(final Long id, final EntityGraph<ParentBiOne> entityGraph) throws JsonProcessingException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException {
        Map<String, Object> hints = new HashMap<>();
        hints.put(QueryHints.JPA_LOAD_GRAPH, entityGraph);
        return em.find(ParentBiOne.class, id, hints);
    }

    public ParentBiOne updatePartial(final ParentBiOne parentParam, final String[] nodes) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return null;
    }

    public ParentBiOne read(final Long id) {
        return em.find(ParentBiOne.class, id);
    }

    public List<ParentBiOne> read() {
        final TypedQuery<ParentBiOne> createNamedQuery = em.createNamedQuery(ParentBiOne.QUERY_NAME_READ_ALL, ParentBiOne.class);
        return createNamedQuery.getResultList();
    }
}
