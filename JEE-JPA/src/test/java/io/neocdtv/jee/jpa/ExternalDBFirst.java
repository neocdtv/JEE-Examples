/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jpa;

import io.neocdtv.jee.jpa.entity.onetomany.uni.ChildUniMany;
import io.neocdtv.jee.jpa.entity.onetomany.uni.ParentUniOne;
import javax.persistence.EntityManager;
import org.junit.Test;


public class ExternalDBFirst extends ExternalDBTest {

    @Override
    public void setUp() {

    }
    
    @Test
    public void test() {
        final ParentUniOne parent = createParent();
        final EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(parent);
        entityManager.getTransaction().commit();
    }

    private ParentUniOne createParent() {
        final ParentUniOne parentUniOne = new ParentUniOne();
        final ChildUniMany first = new ChildUniMany();
        final ChildUniMany second = new ChildUniMany();
        parentUniOne.addChild(second);
        parentUniOne.addChild(first);
        return parentUniOne;
    }
    
}
