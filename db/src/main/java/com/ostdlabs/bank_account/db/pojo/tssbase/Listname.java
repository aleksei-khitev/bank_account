package com.ostdlabs.bank_account.db.pojo.tssbase;

import com.ostdlabs.bank_account.db.pojo.DataBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** Класс для вязи с сущностью базы данных */
@Entity
@Table(name="LISTNAMES")
public class Listname implements DataBaseEntity {
    /** Поле ID, ключ */
    @Id
    @Column(name="ID")
    private Integer id;//NOPMD

    /** Название фирмы */
    @Column(name="LISTNAME")
    private String listName;//NOPMD

    /** Просто getter */
    public Integer getId() {
        return id;
    }

    /** Просто setter */
    public void setId(final Integer id) {//NOPMD
        this.id = id;
    }

    /** Просто getter */
    public String getListName() {
        return listName;
    }

    /** Просто setter */
    public void setListName(final String listName) {
        this.listName = listName;
    }
}
