package com.ostdlabs.bank_account.db.dao;

import com.ostdlabs.bank_account.db.pojo.tssbase.Listname;

import java.util.List;

/**
 * Объект доступа к данным таблицы TSSBASE.LISTNAME
 */
public interface ListNameDao extends AbstractDao<Integer, Listname> {
    /**
     * Получение списка всех фирм.
     * @return Список всех фирм.
     */
    List<Listname> findAll();
}
