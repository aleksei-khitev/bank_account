package com.ostdlabs.bank_account.web.service.impl;

import com.ostdlabs.bank_account.db.repository.AccountRepository;
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

    public List<AccountVO> list() {
        return accountConverter.toVOs(accountRepository.findAll());
    }

    public AccountVO get(Integer id) {
        return accountConverter.toVO(accountRepository.findOne(id));
    }

    public void create(AccountVO accountVO) {
        accountRepository.saveAndFlush(accountConverter.toEntity(accountVO));
    }

    public void update(AccountVO accountVO) {
        accountRepository.saveAndFlush(accountConverter.toEntity(accountVO));
    }

    public void remove(AccountVO accountVO) {
        accountRepository.delete(accountConverter.toEntity(accountVO));
    }

}
