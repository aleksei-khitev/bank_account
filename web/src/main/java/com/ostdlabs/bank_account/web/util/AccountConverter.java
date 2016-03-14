package com.ostdlabs.bank_account.web.util;

import com.ostdlabs.bank_account.db.entity.AccountEntity;
import com.ostdlabs.bank_account.web.vo.AccountVO;

import java.util.List;

/** Converter for creating value object from entity and vice versa */
public interface AccountConverter {
    /** Creating value object from entity */
    AccountVO toVO(AccountEntity entity);

    /** Creating value object list from entity list */
    List<AccountVO> toVOs(List<AccountEntity> entities);

    /** Creating entity from value object */
    AccountEntity toEntity(AccountVO VO);

    /** Creating entity list from value object list */
    List<AccountEntity> toEntities(List<AccountVO> VOs);
}
