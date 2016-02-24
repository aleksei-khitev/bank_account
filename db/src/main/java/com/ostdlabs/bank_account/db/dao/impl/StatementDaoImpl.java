package com.ostdlabs.bank_account.db.dao.impl;

import com.ostdlabs.bank_account.db.pojo.organizations.Statement;
import com.ostdlabs.bank_account.db.qualifier.Organizations;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.ostdlabs.bank_account.db.dao.StatementDao;

import javax.inject.Inject;
import java.util.List;

/** {@inheritDoc} */
@Repository
public class StatementDaoImpl extends AbstractDaoImpl<Integer, Statement> implements StatementDao {
    public static final String QUERY = "SELECT b.LISTNAME_ID, s.STATEMENT_ARITHMETIC_TYPE, s.STATEMENT_COUNT, s.PHONE, s.STATEMENT_DATE, " +
            "u.USER_NAME FROM P_STATEMENT s " +
            "JOIN P_STATEMENT_BASE b ON b.ID = s.STATEMENT_BASE_ID " +
            "JOIN P_USERS u ON u.ID = s.USER_ID " +
            "JOIN P_STATEMENT_BASE_TYPE t ON t.ID = b.STATEMENT_BASE_TYPE_ID " +
            "WHERE t.NAME = '%s' AND s.STATEMENT_DATE >= '%s 00:00' AND s.STATEMENT_DATE <= '%s 23:59'";
    public static final String QUERY2 = "SELECT b.LISTNAME_ID, s.STATEMENT_ARITHMETIC_TYPE, s.STATEMENT_COUNT, s.PHONE, s.STATEMENT_DATE, " +
            "u.USER_NAME FROM P_STATEMENT s " +
            "JOIN P_STATEMENT_BASE b ON b.ID = s.STATEMENT_BASE_ID " +
            "JOIN P_USERS u ON u.ID = s.USER_ID " +
            "JOIN P_STATEMENT_BASE_TYPE t ON t.ID = b.STATEMENT_BASE_TYPE_ID " +
            "WHERE t.NAME = '%s' AND s.STATEMENT_DATE >= '%s 00:00' AND s.STATEMENT_DATE <= '%s 23:59'";
    /** Фабрика сессий для базы данных ORGANIZATIONS. */
    @Inject
    @Organizations
    protected SessionFactory organizationsSF;//NOPMD

    /** {@inheritDoc} */
    protected SessionFactory getSessionFactory(){
        return organizationsSF;
    }

    /** {@inheritDoc} */
    public List<Statement> getByStatementBase(final Integer statementBaseId) {
        return createEntityCriteria()//NOPMD
                .createAlias("statementBase", "b")
                .add(Restrictions.eq("b.id", statementBaseId)).list();
    }

    @Override
    public List getByDirectionAndDate(String direction, String date) {
        return getSession().createSQLQuery(String.format(QUERY, StringUtils.capitalize(direction), date, date)).list();
    }

    @Override
    public List getByNameDirectionAndPeriod(String direction, String startDate, String endDate) {
        return getSession().createSQLQuery(String.format(QUERY2,StringUtils.capitalize(direction),startDate,endDate)).list();
    }
}
