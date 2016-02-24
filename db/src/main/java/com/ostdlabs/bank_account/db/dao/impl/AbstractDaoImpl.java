package com.ostdlabs.bank_account.db.dao.impl;

import org.hibernate.*;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import com.ostdlabs.bank_account.db.dao.AbstractDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/** {@inheritDoc} */
public abstract class AbstractDaoImpl<K extends Serializable, T> implements AbstractDao<K,T> {
    /** Класс сущности. */
    private final Class<T> persistentClass;//NOPMD

    /** Конструктор с задачей класса сущности. */
    @SuppressWarnings("unchecked")
    public AbstractDaoImpl(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /** Фабрика сессий для базы данных. */
    protected abstract SessionFactory getSessionFactory();

    /**
     * Получение сессии из реализованной потомком фабрики
     * @return Сессия
     */
    protected Session getSession(){
        Session session;
        try {
            session = getSessionFactory().getCurrentSession();//NOPMD
        }catch (HibernateException e){
            session = getSessionFactory().openSession();//NOPMD
        }
        return session;
    }

    /**
     * Подготовка критерия вместе с кастом к классу.
     * @return Критерий
     */
    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);//NOPMD
    }

    /** {@inheritDoc} */
    public List<T> findAll(){
        return getSession().createCriteria(persistentClass).setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE).list();//NOPMD
    }

    /** {@inheritDoc} */
    public final T get(final K key) {
        return (T) getSession().get(persistentClass, key);//NOPMD
    }

    /** {@inheritDoc} */
    public K create(final T object) {
        return (K)getSession().save(object);//NOPMD
    }

    /** {@inheritDoc} */
    public void update(final T object) {
        getSession().update(object);//NOPMD
    }

    /** {@inheritDoc} */
    public void delete(final T entity) {
        getSession().delete(entity);//NOPMD
    }
}
