package ru.job4j.cars.store;

import ru.job4j.cars.model.User;

import java.util.Collection;

/**
 * Класс UserStore - содержит методы сохранения, удаления и поиска пользователя
 * в базе данных.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class UserStore implements Store<User> {

    private UserStore() {

    }

    private static final class Lazy {
        private static final UserStore INST = new UserStore();
    }

    public static UserStore instOf() {
        return Lazy.INST;
    }

    @Override
    public void save(User user) {
        HbmUtils.instOf().transaction(session -> session.save(user));
    }

    @Override
    public void delete(int id) {
        User user = findById(id);
        HbmUtils.instOf().transaction(session -> {
            session.delete(user);
            return user;
        });
    }

    @Override
    public Collection<User> findAll() {
        return HbmUtils.instOf().transaction(session -> session.createQuery(
                "from User").list());
    }

    @Override
    public User findById(int id) {
        return (User) HbmUtils.instOf().transaction(session -> session.createQuery(
                "from User where id = :uId")
                .setParameter("uId", id)
                .uniqueResult());
    }

    public User findByEmail(String email) {
        return (User) HbmUtils.instOf().transaction(session -> session.createQuery(
                        "from User where email = :uEmail")
                .setParameter("uEmail", email)
                .uniqueResult());
    }

}
