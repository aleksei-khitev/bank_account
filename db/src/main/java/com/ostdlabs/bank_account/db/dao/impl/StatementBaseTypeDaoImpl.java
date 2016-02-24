package com.ostdlabs.bank_account.db.dao.impl;

import com.ostdlabs.bank_account.db.dao.StatementBaseTypeDao;
import com.ostdlabs.bank_account.db.pojo.organizations.StatementBaseType;
import com.ostdlabs.bank_account.db.qualifier.Organizations;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

/** {@inheritDoc} */
@Repository
public class StatementBaseTypeDaoImpl extends AbstractDaoImpl<Integer, StatementBaseType> implements StatementBaseTypeDao {

    /** Фабрика сессий для базы данных ORGANIZATIONS. */
    @Inject
    @Organizations
    protected SessionFactory organizationsSF;//NOPMD

    /** {@inheritDoc} */
    protected SessionFactory getSessionFactory(){
        return organizationsSF;
    }
}
