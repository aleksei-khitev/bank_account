package com.ostdlabs.bank_account.db.pojo.organizations;

import com.ostdlabs.bank_account.db.pojo.DataBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** Класс для вязи с сущностью базы данных */
@Entity
@Table(name="OFFICES")
public class Office implements DataBaseEntity {
    /** Поле ID, ключ */
    @Id
    @Column(name="ID")
    private Integer id;//NOPMD

    /** ID фирмы */
    @Column(name="listname_id")
    private Integer listnameId;

    /** Просто getter */
    public Integer getId() {
        return id;
    }

    /** Просто setter */
    public void setId(final Integer id) {//NOPMD
        this.id = id;
    }

    /** Просто getter */
    public Integer getListnameId() {
        return listnameId;
    }

    /** Просто setter */
    public void setListnameId(final Integer listnameId) {
        this.listnameId = listnameId;
    }
}
