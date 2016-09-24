package com.birladeanu.dal.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;

/**
 * Created by pavel on 9/7/16.
 */
public interface GenericDao {

    void persist(Object t);

    <T>T find(Class<T> t, Serializable i);

    <T>T getReference(Class<T> t, Serializable i);

    EntityManager getEntityManager();

    void remove(Object o);

    Query createNamedQuery(String query);

}
