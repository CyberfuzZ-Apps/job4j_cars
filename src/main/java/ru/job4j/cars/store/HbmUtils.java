package ru.job4j.cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.function.Function;

/**
 * Класс HbmUtils - утилитный класс для инициализации Hibernate.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class HbmUtils {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private HbmUtils() {

    }

    private static final class Lazy {
        private static final HbmUtils INST = new HbmUtils();
    }

    public static HbmUtils instOf() {
        return Lazy.INST;
    }

    public <T> T transaction(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tr = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tr.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
