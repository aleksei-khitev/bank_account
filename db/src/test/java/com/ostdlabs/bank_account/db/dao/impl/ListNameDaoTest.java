package com.ostdlabs.bank_account.db.dao.impl;

import static org.junit.Assert.assertThat;

import com.ostdlabs.bank_account.db.pojo.tssbase.Listname;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasSize;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ostdlabs.bank_account.db.config.TestDBSpringConfig;
import com.ostdlabs.bank_account.db.dao.ListNameDao;

import javax.inject.Inject;
import java.util.List;

/**
 * Для тестирования ListNameDao.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDBSpringConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ListNameDaoTest {

    @Inject
    private ListNameDao listNameDao;//NOPMD

    /**
     * Получение всех фирм из базы.
     */
    @Test
    public void testFindAll(){
        final List<Listname> lisnames = listNameDao.findAll();
        assertThat("Не правильный список фирм.", lisnames, hasSize(6));
    }

}
