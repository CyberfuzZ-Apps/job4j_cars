package ru.job4j.cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Selling;
import ru.job4j.cars.model.User;

import java.util.Collection;
import java.util.function.Function;

/**
 * Класс HbmStore - реализация методов хранилища с помощью Hibernate.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class HbmStore implements Store {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private HbmStore() {

    }

    private static final class Lazy {
        private static final HbmStore INST = new HbmStore();
    }

    public static HbmStore instOf() {
        return Lazy.INST;
    }

    private <T> T transaction(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            transaction.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void saveCar(Car car) {
        transaction(session -> {
            session.saveOrUpdate(car);
            return car;
        });
    }

    @Override
    public void saveSelling(Selling selling) {
        transaction(session -> {
            session.saveOrUpdate(selling);
            return selling;
        });
    }

    @Override
    public void deleteSelling(int id) {
        Selling selling = findSellingById(id);
        transaction(session -> {
            session.delete(selling);
            return selling;
        });
    }

    @Override
    public void saveUser(User user) {
        transaction(session -> session.save(user));
    }

    @Override
    public Collection<Selling> findAllSelling() {
        return transaction(session -> session.createQuery(
                "from Selling").list());
    }

    @Override
    public Selling findSellingById(int id) {
        return (Selling) transaction(session -> session.createQuery(
                        "from Selling where id = :sId")
                .setParameter("sId", id)
                .uniqueResult());
    }

    @Override
    public User findUserByEmail(String email) {
        return (User) transaction(session -> session.createQuery(
                        "from User where email = :uEmail")
                .setParameter("uEmail", email)
                .uniqueResult());
    }

    @Override
    public Collection<Selling> findSellingByUser(User user) {
        return transaction(session -> session.createQuery(
                        "from Selling where user = :sUser")
                .setParameter("sUser", user).list());
    }

}
