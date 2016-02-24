package com.ostdlabs.bank_account.core.service;

/**
 * Для работы с системными свойствами.
 */
public interface SystemUtil {
    /**
     * Получение имени пользователя из системных свойств
     * @return Имя, под которым пользователь вошел в систему
     */
    String getCurrentUserName();

    /**
     * Получение имени компьютера из системных свойств
     * @return Имя компьютера.
     */
    String getCurrentHostName();
}
