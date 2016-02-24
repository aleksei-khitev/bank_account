package com.ostdlabs.bank_account.web.util;

import com.ostdlabs.bank_account.db.entity.AccountEntity;
import com.ostdlabs.bank_account.web.vo.AccountVO;

import java.util.List;

public interface AccountConverter {
    AccountVO toVO(AccountEntity entity);
    List<AccountVO> toVOs(List<AccountEntity> entities);
    AccountEntity toEntity(AccountVO VO);
    List<AccountEntity> toEntities(List<AccountVO> VOs);
}
