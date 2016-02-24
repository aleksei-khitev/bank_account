package com.ostdlabs.bank_account.db.dao;

import com.ostdlabs.bank_account.db.pojo.organizations.Statement;

import java.util.List;

/** Объект доступа к данным таблицы ORGANIZATIONS.P_STATEMENT */
public interface StatementDao extends AbstractDao<Integer, Statement> {

    /** Получение списка постановок по базе */
    List<Statement> getByStatementBase(Integer statementBaseId);

    List getByDirectionAndDate(String direction, String date);

    List getByNameDirectionAndPeriod(String direction, String startDate, String endDate);
}
