package com.ostdlabs.bank_account.db.dao;

import com.ostdlabs.bank_account.db.pojo.organizations.StatementBase;

import java.util.List;

/** Объект доступа к данным таблицы ORGANIZATIONS.P_STATEMENT_BASE */
public interface StatementBaseDao extends AbstractDao<Integer,StatementBase> {
    /**
     * Получение Базовой информации о постановках
     * @param listnameId ID Фирмы
     * @param typeId ID направления постановок.
     * @return Базовой информации о постановках
     */
    StatementBase getByFirmAndType(Integer listnameId, Integer typeId);

    List getPercentStatistic(Integer buttom, Integer top);
}
