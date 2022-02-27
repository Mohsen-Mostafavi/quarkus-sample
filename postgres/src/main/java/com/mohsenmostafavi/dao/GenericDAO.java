package com.mohsenmostafavi.dao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public abstract class GenericDAO<T> {
    protected abstract EntityManager entityManager();

    @Transactional
    public  <T>  T create(T entity) throws Exception {

        entityManager().persist(entity);
        return entity;

    }

    @Transactional
    public <T>  T  edit (T entity) throws Exception {
        entity=entityManager().merge(entity);
        return entity;
    }

    @Transactional
    public <T> boolean delete (T entity) throws Exception {
        entityManager().remove(entityManager().merge(entity));
        return true;
    }

}
