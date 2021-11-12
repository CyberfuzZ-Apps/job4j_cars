package ru.job4j.cars.store;

import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Selling;
import ru.job4j.cars.model.User;

import java.util.Collection;

/**
 * Интерфейс Store - определяет методы хранилища.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface Store {

    void saveUser(User user);

    void saveCar(Car car);

    void saveSelling(Selling selling);

    void deleteSelling(int id);

    Collection<Selling> findAllSelling();

    Selling findSellingById(int id);

    User findUserByEmail(String email);

    Collection<Selling> findSellingByUser(User user);

}
