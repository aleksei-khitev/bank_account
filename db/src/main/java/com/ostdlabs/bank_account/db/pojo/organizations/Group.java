package com.ostdlabs.bank_account.db.pojo.organizations;

import com.ostdlabs.bank_account.db.pojo.DataBaseEntity;

import javax.persistence.*;

/** Класс для вязи с сущностью базы данных */
@Entity
@Table(name="P_GROUPS")
public class Group implements DataBaseEntity {
    /** Поле ID, ключ */
    @Id
    @Column(name="ID")
    private Integer id;//NOPMD

    /** Название группы */
    @Column(name="GROUP_NAME")
    private String groupName;

    /** Просто getter */
    public Integer getId() {
        return id;
    }

    /** Просто setter */
    public void setId(final Integer id) {//NOPMD
        this.id = id;
    }

    /** Просто getter */
    public String getGroupName() {
        return groupName;
    }

    /** Просто setter */
    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }
}
