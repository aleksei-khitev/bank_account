package com.ostdlabs.bank_account.db.pojo.organizations;

import com.ostdlabs.bank_account.db.pojo.DataBaseEntity;

import javax.persistence.*;

/** Класс для вязи с сущностью базы данных */
@Entity
@Table(name="SUBSCRIPTIONS")
public class Subscription implements DataBaseEntity {
    /** Поле ID, ключ */
    @Id
    @Column(name="ID")
    private Integer id;//NOPMD

    /** ID типа абонемента */
    @ManyToOne
    @JoinColumn(name = "SUBSCRIPTIONSTYPE_ID")
    private SubscriptionsType subscriptionType;

    /** ID фирмы */
    @ManyToOne
    @JoinColumn(name = "OFFICE_ID")
    private Office office;

    /** Просто getter */
    public Integer getId() {
        return id;
    }

    /** Просто setter */
    public void setId(final Integer id) {//NOPMD
        this.id = id;
    }

    /** Просто getter */
    public SubscriptionsType getSubscriptionType() {
        return subscriptionType;
    }

    /** Просто setter */
    public void setSubscriptionType(final SubscriptionsType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    /** Просто getter */
    public Office getOffice() {
        return office;
    }

    /** Просто setter */
    public void setOffice(final Office office) {
        this.office = office;
    }
}
