package ru.job4j.cars.store;

import ru.job4j.cars.model.Selling;
import ru.job4j.cars.model.User;

import java.util.Collection;

/**
 * Класс SellingStore - содержит методы сохранения, удаления и поиска объявления
 * в базе данных.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class SellingStore implements Store<Selling> {

    private SellingStore() {

    }

    private static final class Lazy {
        private static final SellingStore INST = new SellingStore();
    }

    public static SellingStore instOf() {
        return Lazy.INST;
    }

    @Override
    public void save(Selling selling) {
        HbmUtils.instOf().transaction(session -> {
            session.saveOrUpdate(selling);
            return selling;
        });
    }

    @Override
    public void delete(int id) {
        Selling selling = findById(id);
        HbmUtils.instOf().transaction(session -> {
            session.delete(selling);
            return selling;
        });
    }

    @Override
    public Collection<Selling> findAll() {
        return HbmUtils.instOf().transaction(session -> session.createQuery(
                "from Selling").list());
    }

    @Override
    public Selling findById(int id) {
        return (Selling) HbmUtils.instOf().transaction(session -> session.createQuery(
                        "from Selling where id = :sId")
                .setParameter("sId", id)
                .uniqueResult());
    }

    public Collection<Selling> findSellingByUser(User user) {
        return HbmUtils.instOf().transaction(session -> session.createQuery(
                        "from Selling where user = :sUser")
                .setParameter("sUser", user).list());
    }
}
