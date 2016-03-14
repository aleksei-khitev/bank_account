package com.ostdlabs.bank_account.web.controller;

import com.ostdlabs.bank_account.web.service.AccountService;
import com.ostdlabs.bank_account.web.vo.AccountVO;
import com.ostdlabs.bank_account.web.vo.OperationStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Controller for REST points. */
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
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        return accountService.get(id);
    }

    @RequestMapping(value = "/bank_accounts/save")
    public OperationStatus save(@RequestParam Integer id, @RequestParam String iban, @RequestParam String bic) {
        List<String> errors = new ArrayList<>();
        if (iban == null || iban.equals("") || iban.equals("null")) {
            errors.add("iban can't be empty");
        }
        if (bic == null || bic.equals("") || bic.equals("null")) {
            errors.add("bic can't be empty");
        }
        if (!CollectionUtils.isEmpty(errors)) {
            return OperationStatus.newInstance("Saving was incorrect", true, errors);
        }
        AccountVO vo = new AccountVO(id, iban, bic);
        accountService.update(vo);
        return OperationStatus.newInstance("Saving was correct", false, errors);
    }


    @RequestMapping(value = "/bank_accounts/remove")
    public OperationStatus remove(@RequestParam Integer id) {
        if (id == null || id < 0) {
            List<String> errors = new ArrayList<>();
            errors.add("id can't be negative");
            return OperationStatus.newInstance("Removing was incorrect", true, errors);
        }
        accountService.remove(id);
        return OperationStatus.newInstance("Removing was correct", false, Collections.emptyList());
    }
}
