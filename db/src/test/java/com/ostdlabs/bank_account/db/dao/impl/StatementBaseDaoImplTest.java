package com.ostdlabs.bank_account.db.dao.impl;

import com.ostdlabs.bank_account.db.pojo.organizations.StatementBase;
import com.ostdlabs.bank_account.db.pojo.organizations.StatementBaseType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ostdlabs.bank_account.db.config.TestDBSpringConfig;
import com.ostdlabs.bank_account.db.dao.StatementBaseDao;

import javax.inject.Inject;

import java.util.Date;

import static org.hamcrest.Matchers.*;

/**
 * Для тестирования StatementBaseDao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDBSpringConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class StatementBaseDaoImplTest {
    /** Сообщение о провале теста */
    public static final String REASON = "Ошибка теста в StatementBaseDaoImplTest";
    @Inject
    private StatementBaseDao statementBaseDao;//NOPMD

    /**
     * Поиск по фирме и направлению.
     * Запись должна существовать.
     */
    @Test
    public void testGetByFirmAndTypeExixst(){
        final StatementBase base = statementBaseDao.getByFirmAndType(1, 0);
        Assert.assertThat(REASON, base, notNullValue());
        Assert.assertThat(REASON, base.getId(), equalTo(0));//NOPMD
        Assert.assertThat(REASON, base.getListnameId(), equalTo(1));//NOPMD
        Assert.assertThat(REASON, base.getLimit(), equalTo(10));//NOPMD
        Assert.assertThat(REASON, base.getType(), notNullValue());//NOPMD
        Assert.assertThat(REASON, base.getType().getId(), equalTo(0));//NOPMD
        Assert.assertThat(REASON, base.getType().getName(), equalTo("Аренда"));//NOPMD
    }

    /**
     * Поиск по фирме и направлению.
     * Запись не должна существовать.
     */
    @Test
    public void testGetByFirmAndTypeNotExist(){
        final StatementBase base = statementBaseDao.getByFirmAndType(5, 0);
        Assert.assertThat(REASON, base.getId(), nullValue());//NOPMD
    }

    /**
     * Создание записи в базе
     */
    @Test
    public void testCreateSuccessFull(){
        final StatementBase base = new StatementBase();
        base.setListnameId(2);
        final StatementBaseType type = new StatementBaseType();
        type.setId(2);
        base.setType(type);
        base.setLimit(20);
        base.setStartDate(new Date());
        base.setFinishDate(new Date());
        final Integer generatedId = statementBaseDao.create(base);
        Assert.assertThat(REASON, generatedId, nullValue());
    }
}
