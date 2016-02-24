package com.ostdlabs.bank_account.db.dao;

import com.ostdlabs.bank_account.db.pojo.organizations.User;

/** Объект доступа к данным таблицы ORGANIZATIONS.P_USERS */
public interface UserDao extends AbstractDao<Integer,User> {
    /** Получение пользоватеял по имени */
    User getByName(String name);
}
