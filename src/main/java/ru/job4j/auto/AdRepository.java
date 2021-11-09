package ru.job4j.auto;

import java.util.List;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class AdRepository {
    private final StandardServiceRegistry registry;
    private final SessionFactory sf;

    private AdRepository() {
        registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
    }

    private static final class Lazy {
        private static final AdRepository INST = new AdRepository();
    }

    public static AdRepository instOf() {
        return Lazy.INST;
    }

    private <T> T search(final Function<Session, T> command) {
        final Session session = sf.openSession();
        session.beginTransaction();
        try {
            T rsl = command.apply(session);
            session.getTransaction().commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Car> showCurrentDayAds() {
        return this.search(
                session -> {
                    Query<Car> query = session.createQuery(
                            "select distinct c from Car c join fetch c.user join fetch c.photo "
                                    + "where c.created > CURRENT_DATE - hour(24)");
                    return query.list();
                }
        );
    }

    public List<Car> showAllAdsWithPhoto() {
        return this.search(
                session -> {
                    Query<Car> query = session.createQuery(
                            "select distinct c from Car c join fetch c.user join fetch c.photo p "
                                    + "where p.isAvailable = true");
                    return query.list();
                }
        );
    }

    public List<Car> showAllAdsOfBrand(String brand) {
        return this.search(
                session -> {
                    Query<Car> query = session.createQuery(
                            "select distinct c from Car c join fetch c.user join fetch c.photo "
                                    + "where c.brand = :bParam");
                    query.setParameter("bParam", brand);
                    return query.list();
                }
        );
    }
}