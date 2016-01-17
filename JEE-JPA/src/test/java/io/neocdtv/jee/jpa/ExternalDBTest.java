/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

/**
 *
 * @author xix
 */
public abstract class ExternalDBTest {

    @Rule
    public TestName name = new TestName();

    private EntityManager entityManager;

    private static final String DEFAULT_PERSISTENCE_UNIT_NAME = "jpaPlay-test";

    public abstract void setUp();

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Before
    public void beforeTest() throws Exception {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(getPersistenceUnitName());
        entityManager = entityManagerFactory.createEntityManager();
        setUp();
    }

    protected String getPersistenceUnitName() {
        return DEFAULT_PERSISTENCE_UNIT_NAME;
    }

    @After
    public void afterTest() {
        if (getEntityManager() != null) {
            return;
        }

        final EntityTransaction transaction = getEntityManager().getTransaction();

        if (transaction != null && transaction.isActive()) {
            if (transaction.getRollbackOnly()) {
                transaction.rollback();
            } else {
                transaction.commit();
            }
        }

        getEntityManager().close();
    }

    protected void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    protected void commitTransaction() {
        getEntityManager().getTransaction().commit();
    }

    protected void executeNativeStatements(final String... statements) {
        beginTransaction();
        for (String statement : statements) {
            getEntityManager().createNativeQuery(statement).executeUpdate();
        }
        commitTransaction();
    }
}
