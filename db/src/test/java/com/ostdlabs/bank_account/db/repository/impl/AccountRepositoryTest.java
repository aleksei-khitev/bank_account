package com.ostdlabs.bank_account.db.repository.impl;

import com.ostdlabs.bank_account.db.config.DBSpringConfig;
import com.ostdlabs.bank_account.db.entity.AccountEntity;
import com.ostdlabs.bank_account.db.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBSpringConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountRepositoryTest {
    private static final Integer testEntityID = 5;

    @Inject
    private AccountRepository accountRepository;//NOPMD

    @Test
    public void testFindAll() {
        assertThat("Wrong account's size", accountRepository.findAll(), hasSize(6));
    }

    @Test
    public void testCreate() {
        long sizeBefore = accountRepository.count();
        AccountEntity entity = new AccountEntity();
        entity.setBic("001111");
        entity.setIban("000000000011111");
        accountRepository.saveAndFlush(entity);
        long sizeAfter = accountRepository.count();
        assertThat("Wrong account's size after insertion", sizeAfter - sizeBefore, equalTo(1L));
    }

    @Test(expected = PersistenceException.class)
    public void testCreateWithOutBic() {
        AccountEntity entity = new AccountEntity();
        entity.setIban("000000000011111");
        accountRepository.saveAndFlush(entity);
    }

    @Test(expected = PersistenceException.class)
    public void testCreateWithOutIban() {
        AccountEntity entity = new AccountEntity();
        entity.setBic("001111");
        accountRepository.saveAndFlush(entity);
    }

    @Test
    public void testUpdate() {
        AccountEntity entity = accountRepository.findOne(testEntityID);
        assertThat("Incorrect initial data", entity.getBic(), equalTo("022006"));
        assertThat("Incorrect initial data", entity.getIban(), equalTo("02345678912345678800006"));
        entity.setBic("022007");
        accountRepository.saveAndFlush(entity);
        entity = accountRepository.findOne(testEntityID);
        assertThat("Entity was not modified", entity.getBic(), equalTo("022007"));
        assertThat("IBAN was not unexpectedly modified", entity.getIban(), equalTo("02345678912345678800006"));
    }

    @Test
    public void testRemoveByEntity() {
        long sizeBefore = accountRepository.count();
        AccountEntity entity = accountRepository.findOne(testEntityID);
        accountRepository.delete(entity);
        accountRepository.flush();
        long sizeAfter = accountRepository.count();
        assertThat("Wrong account's size after removing", sizeBefore - sizeAfter, equalTo(1L));
    }

    @Test
    public void testRemoveByID() {
        long sizeBefore = accountRepository.count();
        accountRepository.delete(testEntityID);
        accountRepository.flush();
        long sizeAfter = accountRepository.count();
        assertThat("Wrong account's size after removing", sizeBefore - sizeAfter, equalTo(1L));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testRemoveNotExist() {
        accountRepository.delete(40);
        accountRepository.flush();
    }
}
