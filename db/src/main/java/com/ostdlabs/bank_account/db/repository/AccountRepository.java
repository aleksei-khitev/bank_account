package com.ostdlabs.bank_account.db.repository;

import com.ostdlabs.bank_account.db.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
}
