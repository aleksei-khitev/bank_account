package com.ostdlabs.bank_account.db.pojo.organizations;

/**
 * Определяет, списание это постановок или возврат
 */
public enum StatementArithmeticType {
    /** Возврат */
    INCREMENT,
    /** Списание */
    DECREMENT
}
