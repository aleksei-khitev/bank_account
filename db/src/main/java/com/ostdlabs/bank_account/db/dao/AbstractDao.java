package com.ostdlabs.bank_account.db.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Для задачи общего функционала объектов доступа к данным.
 */
public interface AbstractDao<K extends Serializable, T> {
    /**
     * Поиск всех элементов.
     * @return Все элементы.
     */
    List<T> findAll();

    /**
     * Получение объекта оп его ID
     * @param key ID объекта в базе
     * @return Найденный объект
     */
    T get(K key);

    /**
     * Схранение нового объекта в базу данных.
     * @param object Объект для сохранения.
     * @return ID сохраненного объекта.
     */
    K create(T object);

    /**
     * Схранение измененного объекта в базу данных.
     * @param object Объект для сохранения.
     */
    void update(T object);

    /**
     * Удаление объекта из базы данных.
     * @param entity Объяект для удаления.
     */
    void delete(T entity);
}
