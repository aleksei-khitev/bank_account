package com.ostdlabs.bank_account.web.service.impl;

import com.ostdlabs.bank_account.db.entity.AccountEntity;
import com.ostdlabs.bank_account.db.repository.AccountRepository;
import com.ostdlabs.bank_account.jms.service.JmsSender;
import com.ostdlabs.bank_account.web.service.AccountService;
import com.ostdlabs.bank_account.web.util.AccountConverter;
import com.ostdlabs.bank_account.web.vo.AccountVO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class AccountServiceImpl implements AccountService {

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private AccountConverter accountConverter;

    @Inject
    private JmsSender jmsSender;

    public List<AccountVO> list() {
        return accountConverter.toVOs(accountRepository.findAll());
    }

    public AccountVO get(Integer id) {
        return accountConverter.toVO(accountRepository.findOne(id));
    }

    public void create(AccountVO accountVO) {
        AccountEntity entity = accountConverter.toEntity(accountVO);
        accountRepository.saveAndFlush(entity);
        jmsSender.send("Created "+entity.toString());
    }

    public void update(AccountVO accountVO) {
        AccountEntity entity = accountConverter.toEntity(accountVO);
        accountRepository.saveAndFlush(entity);
        jmsSender.send("Updated "+entity.toString());
    }

    public void remove(Integer id) {
        accountRepository.delete(id);
        jmsSender.send("Updated with id "+id);
    }

}
