package com.ostdlabs.bank_account.web.service;

import com.ostdlabs.bank_account.web.vo.AccountVO;

import java.util.List;

public interface AccountService {
    List<AccountVO> list();
    AccountVO get(Integer id);
    void create(AccountVO accountVO);
    void update(AccountVO accountVO);
    void remove(AccountVO accountVO);
}
