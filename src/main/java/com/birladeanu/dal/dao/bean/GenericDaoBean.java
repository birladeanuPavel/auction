package com.birladeanu.dal.dao.bean;

import com.birladeanu.dal.dao.GenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;

/**
 * Created by pavel on 9/7/16.
 */
@Repository
public class GenericDaoBean implements GenericDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(Object t) {
        entityManager.persist(t);
    }

    @Override
    public <T> T find(Class<T> t, Serializable i) {
        return entityManager.find(t, i);
    }

    @Override
    public <T> T getReference(Class<T> t, Serializable i) {
        return entityManager.getReference(t, i);
    }

    @Override
    public Query createNamedQuery(String query) {
        return entityManager.createNamedQuery(query);
    }

    @Override
    public void remove(Object o) {
        entityManager.remove(o);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
