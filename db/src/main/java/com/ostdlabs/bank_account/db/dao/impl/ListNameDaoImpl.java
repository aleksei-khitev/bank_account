package com.ostdlabs.bank_account.db.dao.impl;

import com.ostdlabs.bank_account.db.pojo.tssbase.Listname;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import com.ostdlabs.bank_account.db.dao.ListNameDao;
import com.ostdlabs.bank_account.db.qualifier.TssBase;

import javax.inject.Inject;
import java.util.List;

/** {@inheritDoc} */
@Repository
public class ListNameDaoImpl extends AbstractDaoImpl<Integer, Listname> implements ListNameDao {

    /** Фабрика сессий для базы данных TSSBASE. */
    @Inject
    @TssBase
    private SessionFactory tssbaseSF;//NOPMD

    /** {@inheritDoc} */
    protected SessionFactory getSessionFactory(){
        return tssbaseSF;
    }

    /** {@inheritDoc} */
    public List<Listname> findAll() {
        return createEntityCriteria().addOrder(Order.asc("listName")).list();//NOPMD
    }
}
