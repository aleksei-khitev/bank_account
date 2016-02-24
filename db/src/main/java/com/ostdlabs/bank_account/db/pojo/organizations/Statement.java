package com.ostdlabs.bank_account.db.pojo.organizations;

import com.ostdlabs.bank_account.db.pojo.DataBaseEntity;

import javax.persistence.*;
import java.util.Date;

/** Класс для вязи с сущностью базы данных */
@Entity
@Table(name="P_STATEMENT")
public class Statement implements DataBaseEntity {
    /** Поле ID, ключ */
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Seq_P_STATEMENT")
    @SequenceGenerator(name = "Seq_P_STATEMENT", sequenceName="generator_P_STATEMENT")
    private Integer id;//NOPMD

    /** Количество постановок */
    @Column(name="STATEMENT_COUNT")
    private Integer count;

    /** Списание или возврат постановок */
    @Column(name="STATEMENT_ARITHMETIC_TYPE")
    @Enumerated(EnumType.STRING)
    private StatementArithmeticType arithmeticType;

    /** Телефон по которому заведены постановки */
    @Column(name="PHONE")
    private String phone;

    /** Дата операции */
    @Column(name="STATEMENT_DATE")
    private Date date;

    /** Пользователь, который провел операцию */
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    /** База постановок. В ей описываются лимиты и прочее */
    @ManyToOne
    @JoinColumn(name = "STATEMENT_BASE_ID")
    private StatementBase statementBase;

    /** Просто getter */
    public Integer getId() {
        return id;
    }

    /** Просто setter */
    public void setId(final Integer id) {//NOPMD
        this.id = id;
    }

    /** Просто getter */
    public Integer getCount() {
        return count;
    }

    /** Просто setter */
    public void setCount(final Integer count) {
        this.count = count;
    }

    /** Просто getter */
    public StatementArithmeticType getArithmeticType() {
        return arithmeticType;
    }

    /** Просто setter */
    public void setArithmeticType(final StatementArithmeticType arithmeticType) {
        this.arithmeticType = arithmeticType;
    }

    /** Просто getter */
    public String getPhone() {
        return phone;
    }

    /** Просто setter */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /** Просто getter */
    public Date getDate() {
        return new Date(date.getTime());
    }

    /** Просто setter */
    public void setDate(final Date date) {
        this.date = new Date(date.getTime());
    }

    /** Просто getter */
    public User getUser() {
        return user;
    }

    /** Просто setter */
    public void setUser(final User user) {
        this.user = user;
    }

    /** Просто getter */
    public StatementBase getStatementBase() {
        return statementBase;
    }

    /** Просто setter */
    public void setStatementBase(final StatementBase statementBase) {
        this.statementBase = statementBase;
    }
}
