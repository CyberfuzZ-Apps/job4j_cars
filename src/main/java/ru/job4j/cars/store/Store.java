package ru.job4j.cars.store;

import java.util.Collection;

/**
 * Интерфейс Store - определяет методы хранилища.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface Store<T> {

    void save(T unit);

    void delete(int id);

    Collection<T> findAll();

    T findById(int id);

}
