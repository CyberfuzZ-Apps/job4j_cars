package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.Selling;
import ru.job4j.cars.model.User;

/**
 * Класс HbmRun
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            User user = new User(
                    "Ivan",
                    "ivan@local",
                    "123"
            );
            session.persist(user);

            Selling selling = new Selling(
                    "Description",
                    "Model",
                    "Body",
                    false,
                    user
            );
            session.persist(selling);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
