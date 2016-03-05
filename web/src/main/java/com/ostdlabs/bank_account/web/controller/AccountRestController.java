package com.ostdlabs.bank_account.web.controller;

import com.ostdlabs.bank_account.web.service.AccountService;
import com.ostdlabs.bank_account.web.vo.AccountVO;
import com.ostdlabs.bank_account.web.vo.OperationStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@RestController
public class AccountRestController {
    @Inject
    private AccountService accountService;

    @RequestMapping(value = "/bank_accounts/list")
    public List<AccountVO> list() {
        return accountService.list();
    }

    @RequestMapping(value = "/bank_accounts/get")
    public AccountVO get(@RequestParam(value = "id") Integer id) {
        return accountService.get(id);
    }

    @RequestMapping(value = "/bank_accounts/save")
    public OperationStatus save(@RequestParam Integer id, @RequestParam String iban, @RequestParam String bic) {
        AccountVO vo = new AccountVO(id, iban, bic);
        accountService.update(vo);
        return OperationStatus.newInstance("Saving was correct", false, Collections.emptyList());
    }

    @RequestMapping(value = "/bank_accounts/remove")
    public OperationStatus remove(@RequestParam Integer id) {
        accountService.remove(id);
        return OperationStatus.newInstance("Saving was correct", false, Collections.emptyList());
    }
}
