package com.ostdlabs.bank_account.db.pojo.organizations;

import com.ostdlabs.bank_account.db.pojo.DataBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** Класс для вязи с сущностью базы данных */
@Entity
@Table(name="SUBSCRIPTIONSTYPE")
public class SubscriptionsType implements DataBaseEntity {
    /** Поле ID, ключ */
    @Id
    @Column(name="ID")
    private Integer id;//NOPMD

    /** Тип абонемента */
    @Column(name="SUBSCRIPTIONTYPE")
    private String subscriptionType;

    /** Просто getter */
    public Integer getId() {
        return id;
    }

    /** Просто setter */
    public void setId(final Integer id) {//NOPMD
        this.id = id;
    }

    /** Просто getter */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /** Просто setter */
    public void setSubscriptionType(final String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
