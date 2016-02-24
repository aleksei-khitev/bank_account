package com.ostdlabs.bank_account.web.util.impl;

import com.ostdlabs.bank_account.db.entity.AccountEntity;
import com.ostdlabs.bank_account.web.util.AccountConverter;
import com.ostdlabs.bank_account.web.vo.AccountVO;
import org.springframework.util.StringUtils;

import javax.inject.Named;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Named
public class AccountConverterImpl implements AccountConverter {

    public AccountVO toVO(AccountEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity is null.");
        }
        if (entity.getBic() == null || StringUtils.isEmpty(entity.getBic())) {
            throw new IllegalArgumentException("BIC of Entity is null or empty.");
        }
        if (entity.getIban() == null || StringUtils.isEmpty(entity.getIban())) {
            throw new IllegalArgumentException("IBAN of Entity is null or empty.");
        }
        return new AccountVO(entity.getId(), entity.getIban(), entity.getBic());
    }

    public List<AccountVO> toVOs(List<AccountEntity> entities) {
        return entities.stream().map(this::toVO).collect(toList());
    }

    public AccountEntity toEntity(AccountVO VO) {
        if (VO == null) {
            throw new IllegalArgumentException("Value Object is null.");
        }
        if (VO.getBic() == null || StringUtils.isEmpty(VO.getBic())) {
            throw new IllegalArgumentException("BIC of Value Object is null or empty.");
        }
        if (VO.getIban() == null || StringUtils.isEmpty(VO.getIban())) {
            throw new IllegalArgumentException("IBAN of Value Object is null or empty.");
        }
        AccountEntity entity = new AccountEntity();
        entity.setBic(VO.getBic());
        entity.setIban(VO.getIban());
        if (VO.getId() != null) {
            entity.setId(VO.getId());
        }
        return entity;
    }

    public List<AccountEntity> toEntities(List<AccountVO> VOs) {
        return VOs.stream().map(this::toEntity).collect(toList());
    }
}
