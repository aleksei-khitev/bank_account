package com.ostdlabs.bank_account.db.dao.impl;

import com.ostdlabs.bank_account.db.pojo.organizations.Subscription;
import com.ostdlabs.bank_account.db.qualifier.Organizations;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.ostdlabs.bank_account.db.dao.SubscriptionDao;

import javax.inject.Inject;
import java.util.List;

/** {@inheritDoc} */
@Repository
public class SubscriptionDaoImpl extends AbstractDaoImpl<Integer, Subscription> implements SubscriptionDao {
    /** Запрос для получения активных фирм. */
    public static final String QUERY_STRING = "select distinct F.LISTNAME_ID from FIRMSUBSCRIPTIONGLOBAL F";
    public static final String WITH_STAT_SQL = "select distinct F.LISTNAME_ID from FIRMSUBSCRIPTIONGLOBAL F " +
            "WHERE f.LISTNAME_ID IN (" +
            "SELECT b.LISTNAME_ID " +
            "from P_STATEMENT_BASE b " +
            "WHERE b.FINISH_DATE > CURRENT_TIMESTAMP)";
    /** Фабрика сессий для базы данных ORGANIZATIONS. */
    @Inject
    @Organizations
    protected SessionFactory sessionFactory;//NOPMD

    /** {@inheritDoc} */
    protected SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    /** {@inheritDoc} */
    public List<Subscription> getByFirm(final Integer firmId) {
        return createEntityCriteria().createAlias("office", "o")//NOPMD
                .add(Restrictions.eq("o.listnameId", firmId)).list();
    }

    /** {@inheritDoc} */
    public List<Integer> getAllActive(){
        return getSession().createSQLQuery(QUERY_STRING).list();//NOPMD
    }

    public List<Integer> getWithActiveStatements(){
        return getSession().createSQLQuery(WITH_STAT_SQL).list();//NOPMD
    }
}
