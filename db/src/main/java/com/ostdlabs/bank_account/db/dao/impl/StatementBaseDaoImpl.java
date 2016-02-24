package com.ostdlabs.bank_account.db.dao.impl;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import com.ostdlabs.bank_account.db.dao.StatementBaseDao;
import com.ostdlabs.bank_account.db.pojo.organizations.StatementBase;
import com.ostdlabs.bank_account.db.qualifier.Organizations;

import javax.inject.Inject;
import java.util.List;

/** {@inheritDoc} */
@Repository
public class StatementBaseDaoImpl extends AbstractDaoImpl<Integer, StatementBase> implements StatementBaseDao {

    public static final String PERCENT_STAT_QUERY = "SELECT MAX(r.LISTNAME_ID) AS FIRM, MAX(r.FINISH_DATE) AS zaeb, " +
            "MAX(t.NAME) AS HZ, MAX(r.LIMIT) AS LIMIT, (cast(MAX(LIMIT) as int)-cast(COUNT(s.ID) as int)) AS AVAILABLE " +
            "FROM P_STATEMENT_BASE r JOIN P_STATEMENT s ON s.STATEMENT_BASE_ID = r.ID JOIN P_STATEMENT_BASE_TYPE t ON " +
            "t.ID = r.STATEMENT_BASE_TYPE_ID GROUP BY r.ID HAVING (100 - (cast(COUNT(s.ID) as float)/cast(MAX(LIMIT) as float)*100)) > %d " +
            "and (100-(cast(COUNT(s.ID) as float)/cast(MAX(LIMIT) as float)*100)) < %d";

    /** Фабрика сессий для базы данных ORGANIZATIONS. */
    @Inject
    @Organizations
    protected SessionFactory organizationsSF;//NOPMD

    /** {@inheritDoc} */
    protected SessionFactory getSessionFactory(){
        return organizationsSF;
    }

    /** {@inheritDoc} */
    public StatementBase getByFirmAndType(final Integer listNameId, final Integer typeId) {
        final List<StatementBase> bases = createEntityCriteria().createAlias("type", "t")//NOPMD
                .add(Restrictions.eq("t.id", typeId))
                .add(Restrictions.eq("listnameId", listNameId)).list();
        StatementBase statementBase;
        if (CollectionUtils.isEmpty(bases)) {
            statementBase = new StatementBase();
        } else {
            statementBase = bases.get(0);//NOPMD
        }
        return statementBase;
    }

    public List getPercentStatistic(final Integer button, final Integer top){
        return getSession().createSQLQuery(String.format(PERCENT_STAT_QUERY,button,top)).list();
    }
}
