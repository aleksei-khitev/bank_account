package com.ostdlabs.bank_account.db.dao;

import com.ostdlabs.bank_account.db.pojo.organizations.Subscription;

import java.util.List;

/** Объект доступа к данным таблицы ORGANIZATIONS.SUBSCRIPTIONS */
public interface SubscriptionDao extends AbstractDao<Integer,Subscription> {

    /** Получение абонементов по ID фирмы */
    List<Subscription> getByFirm(Integer firmId);

    /** Получение всех активных абонементов. */
    List<Integer> getAllActive();

    List<Integer> getWithActiveStatements();
}
