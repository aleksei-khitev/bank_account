package com.ostdlabs.bank_account.db.exception;

/**
 * Исключение при работе с данными.
 * Пример - не удалось найти пользователя
 */
public class IncorrectDBData extends RuntimeException {
    /** Конструктор по умолчанию */
    public IncorrectDBData(final String message, final Throwable cause) {
        super(message, cause);
    }
}
