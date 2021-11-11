package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.Selling;

import java.util.List;
import java.util.function.Function;

/**
 * Класс AdRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class AdRepository {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private AdRepository() {

    }

    private static final class Lazy {
        private static final AdRepository INST = new AdRepository();
    }

    public static AdRepository instOf() {
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

    public List<Selling> findNew() {
        return transaction(session -> session.createQuery(
                "from Selling where day(current_timestamp - created) <= 1").list());
    }

    public List<Selling> findWithPhoto() {
        return transaction(session -> session.createQuery(
                "from Selling where photo = true").list());
    }

    public List<Selling> findByModel(String model) {
        return transaction(session -> session.createQuery(
                "from Selling where model = :sModel")
                .setParameter("sModel", model)
                .list());
    }

    public static void main(String[] args) {
        System.out.println(AdRepository.instOf().findNew());
        System.out.println("****************");
        System.out.println(AdRepository.instOf().findWithPhoto());
        System.out.println("****************");
        System.out.println(AdRepository.instOf().findByModel("a"));
    }

}
