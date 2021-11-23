package ru.job4j.cars.store;

import ru.job4j.cars.model.Car;

import java.util.Collection;

/**
 * Класс CarStore - содержит методы сохранения, удаления и поиска автомобиля
 * в базе данных.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class CarStore implements Store<Car> {

    private CarStore() {

    }

    private static final class Lazy {
        private static final CarStore INST = new CarStore();
    }

    public static CarStore instOf() {
        return Lazy.INST;
    }

    @Override
    public void save(Car car) {
        HbmUtils.instOf().transaction(session -> {
            session.saveOrUpdate(car);
            return car;
        });
    }

    @Override
    public void delete(int id) {
        Car car = findById(id);
        HbmUtils.instOf().transaction(session -> {
            session.delete(car);
            return car;
        });
    }

    @Override
    public Collection<Car> findAll() {
        return HbmUtils.instOf().transaction(session -> session.createQuery(
                "from Car ").list());
    }

    @Override
    public Car findById(int id) {
        return (Car) HbmUtils.instOf().transaction(session -> session.createQuery(
                        "from Car where id = :cId")
                .setParameter("cId", id)
                .uniqueResult());
    }
}
