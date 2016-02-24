package com.ostdlabs.bank_account.web.service;

import com.ostdlabs.bank_account.web.vo.AccountVO;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<AccountVO> list();
    Optional<AccountVO> get(long id);
    void update(long id);
    void remove(long id);
}
