package com.ostdlabs.bank_account.db.dao.impl;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ostdlabs.bank_account.db.config.TestDBSpringConfig;
import com.ostdlabs.bank_account.db.dao.UserDao;
import com.ostdlabs.bank_account.db.exception.IncorrectDBData;
import com.ostdlabs.bank_account.db.pojo.organizations.User;

import javax.inject.Inject;

/**
 * Для тестирования UserDaoTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDBSpringConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UserDaoTest {
    /** Сообщение о провале теста */
    public static final String REASON = "Ошибка теста в UserDaoTest";

    @Inject
    private UserDao userDao;//NOPMD

    /**
     * Поиск по логину
     * Запись должна существовать.
     */
    @Test
    public void testGetByNameFound(){
        final User user = userDao.getByName("second_manager");
        assertThat(REASON, user.getUserName(), equalTo("second_manager"));//NOPMD
        assertThat(REASON, user.getGroup().getGroupName(), equalTo("Менеджер"));//NOPMD//NOPMD
    }

    /**
     * Поиск по логину
     * Запись не существует
     */
    @Test(expected= IncorrectDBData.class)
    public void testGetByNameNotFound(){
        userDao.getByName("some_one_else");
    }

    /**
     * Поиск по логину
     * Запись не существует
     */
    @Test(expected= IncorrectDBData.class)
    public void testGetByNameFoundButWithoutGroup(){
        userDao.getByName("wrong_user");
    }
}
