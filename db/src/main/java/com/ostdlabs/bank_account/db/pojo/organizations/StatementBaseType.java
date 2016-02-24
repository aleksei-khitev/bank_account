package com.ostdlabs.bank_account.db.pojo.organizations;


import com.ostdlabs.bank_account.db.pojo.DataBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** Класс для вязи с сущностью базы данных */
@Entity
@Table(name="P_STATEMENT_BASE_TYPE")
public class StatementBaseType implements DataBaseEntity {
    /** Поле ID, ключ */
    @Id
    @Column(name="ID")
    private Integer id;//NOPMD

    /** Название направления */
    @Column(name="NAME")
    private String name;

    /** Просто getter */
    public Integer getId() {
        return id;
    }

    /** Просто setter */
    public void setId(final Integer id) {//NOPMD
        this.id = id;
    }

    /** Просто getter */
    public String getName() {
        return name;
    }

    /** Просто setter */
    public void setName(final String name) {
        this.name = name;
    }
}
