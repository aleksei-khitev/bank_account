package com.ostdlabs.bank_account.db.pojo.organizations;

import com.ostdlabs.bank_account.db.pojo.DataBaseEntity;

import javax.persistence.*;
import java.util.Date;

/** Класс для вязи с сущностью базы данных */
@Entity
@Table(name="P_STATEMENT_BASE")
public class StatementBase implements DataBaseEntity {
    /** Поле ID, ключ */
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Seq_P_STATEMENT_BASE")
    @SequenceGenerator(name = "Seq_P_STATEMENT_BASE", sequenceName="generator_P_STATEMENT_BASE")
    private Integer id;//NOPMD

    /** ID фирмы */
    @Column(name="LISTNAME_ID")
    private Integer listnameId;

    /** Списание или возврат */
    @ManyToOne
    @JoinColumn(name = "STATEMENT_BASE_TYPE_ID")
    private StatementBaseType type;

    /** Лимит постановок */
    @Column(name="LIMIT")
    private Integer limit;

    @Column(name="COMMENT")
    private String comment;

    /** Дата начала периода */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="START_DATE")
    private Date startDate;

    /** Дата конца периода */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FINISH_DATE")
    private Date finishDate;

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

    /** Просто getter */
    public StatementBaseType getType() {
        return type;
    }

    /** Просто setter */
    public void setType(final StatementBaseType type) {
        this.type = type;
    }

    /** Просто getter */
    public Integer getLimit() {
        return limit;
    }

    /** Просто setter */
    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /** Просто getter */
    public Date getStartDate() {
        return new Date(startDate.getTime());
    }

    /** Просто setter */
    public void setStartDate(final Date startDate) {
        this.startDate = new Date(startDate.getTime());
    }

    /** Просто getter */
    public Date getFinishDate() {
        return new Date(finishDate.getTime());
    }

    /** Просто setter */
    public void setFinishDate(final Date finishDate) {
        this.finishDate = new Date(finishDate.getTime());
    }

}
