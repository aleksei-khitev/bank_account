package com.ostdlabs.bank_account.db.pojo.organizations;

import com.ostdlabs.bank_account.db.pojo.DataBaseEntity;

import javax.persistence.*;

/**
 * Класс для вязи с сущностью базы данных
 */
@Entity
@Table(name="P_USERS")
public class User implements DataBaseEntity {//NOPMD
    /** Поле ID, ключ */
    @Id
    @Column(name="ID")
    private Integer id;//NOPMD

    /** Логин */
    @Column(name="USER_NAME")
    private String userName;

    /** Группа, в которую входит пользователь */
    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    /** Просто getter */
    public Integer getId() {
        return id;
    }

    /** Просто setter */
    public void setId(final Integer id) {//NOPMD
        this.id = id;
    }

    /** Просто getter */
    public String getUserName() {
        return userName;
    }

    /** Просто setter */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /** Просто getter */
    public Group getGroup() {
        return group;
    }

    /** Просто setter */
    public void setGroup(final Group group) {
        this.group = group;
    }
}

