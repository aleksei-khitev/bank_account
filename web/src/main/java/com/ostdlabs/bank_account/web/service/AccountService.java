package com.ostdlabs.bank_account.web.service;

import com.ostdlabs.bank_account.web.vo.AccountVO;

import java.util.List;

/** Service for invoking DAO, Converter and JMS methods. */
public interface AccountService {

    /** Recieving list of converted entities. */
    List<AccountVO> list();

    /** Getting converted entity by id. */
    AccountVO get(Integer id);

    /** Saving entity and sending jms to queue. */
    void create(AccountVO accountVO);

    /** Saving entity and sending jms to queue. */
    void update(AccountVO accountVO);

    /** Removing entity and sending jms to queue. */
    void remove(Integer id);
}
