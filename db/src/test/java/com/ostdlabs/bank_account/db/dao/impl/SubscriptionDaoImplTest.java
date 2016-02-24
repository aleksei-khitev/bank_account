package com.ostdlabs.bank_account.db.dao.impl;

import com.ostdlabs.bank_account.db.dao.SubscriptionDao;
import com.ostdlabs.bank_account.db.pojo.organizations.Subscription;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ostdlabs.bank_account.db.config.TestDBSpringConfig;

import javax.inject.Inject;

import java.util.List;

/**
 * Для тестирования SubscriptionDaoImplTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDBSpringConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class SubscriptionDaoImplTest {

    @Inject
    private SubscriptionDao subscriptionDao;//NOPMD

    /**
     * Поиск абонементов по фирме.
     * Одна запись.
     */
    @Test
    public void testGetByFirmOneRow(){
        final List<Subscription> subscriptions = subscriptionDao.getByFirm(2);
        Assert.assertThat(subscriptions, Matchers.notNullValue());
        Assert.assertThat(subscriptions.size(), Matchers.equalTo(1));//NOPMD
    }

    /**
     * Поиск абонементов по фирме.
     * Несколько записей.
     */
    @Test
    public void testGetByFirmMultipleRows(){
        final List<Subscription> subscriptions = subscriptionDao.getByFirm(3);
        Assert.assertThat(subscriptions, Matchers.notNullValue());
        Assert.assertThat(subscriptions.size(), Matchers.equalTo(2));//NOPMD
    }

    /**
     * Поиск абонементов по фирме.
     * Нет абонементов.
     */
    @Test
    public void testGetByFirmError(){
        final List<Subscription> subscriptions = subscriptionDao.getByFirm(5);
        Assert.assertThat(subscriptions, Matchers.notNullValue());
        Assert.assertThat(subscriptions.size(), Matchers.equalTo(0));//NOPMD
    }
}
