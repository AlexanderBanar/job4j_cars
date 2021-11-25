package ru.job4j.store;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.auto.Car;
import ru.job4j.auto.User;

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

    private <T> T transact(final Function<Session, T> command) {
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
        long curTime = System.currentTimeMillis();
        long limitTime = curTime - 3600000 * 24;
        Date end = new Date(curTime);
        Date start = new Date(limitTime);
        return this.transact(
                session -> {
                    Query<Car> query = session.createQuery(
                            "select distinct c from Car c join fetch c.user join fetch c.photo "
                                    + "where c.created between :start and :end")
                            .setParameter("start", start)
                            .setParameter("end", end);
                    return query.list();
                }
        );
    }

    public List<Car> showAllAdsWithPhoto() {
        return this.transact(
                session -> {
                    Query<Car> query = session.createQuery(
                            "select distinct c from Car c join fetch c.user join fetch c.photo p "
                                    + "where p.isAvailable = true");
                    return query.list();
                }
        );
    }

    public List<Car> showMyAds(User user) {
        return this.transact(
                session -> {
                    Query<Car> query = session.createQuery(
                            "select distinct c from Car c join fetch c.user join fetch c.photo "
                                    + "where c.user = :uParam");
                    query.setParameter("uParam", user);
                    return query.list();
                }
        );
    }

    public List<Car> showAllAds() {
        return this.transact(
                session -> session.createQuery("from Car").list()
        );
    }

    public void saveUser(User user) {
        this.transact(session -> session.save(user));
    }

    public User getUser(User user) {
        List<User> list = this.transact(
                session -> {
                    Query<User> query = session.createQuery(
                            "select distinct u from User u "
                                    + "where u.name = :name and u.password = :pass");
                    query.setParameter("name", user.getName());
                    query.setParameter("pass", user.getPassword());
                    return query.list();
                }
        );
        User result = null;
        if (list.size() > 0) {
            result = list.get(0);
        }
        return result;
    }

    public User getUserByName(String name) {
        List<User> list = this.transact(
                session -> {
                    Query<User> query = session.createQuery(
                            "select distinct u from User u "
                                    + "where u.name = :name");
                    query.setParameter("name", name);
                    return query.list();
                }
        );
        User result = null;
        if (list.size() > 0) {
            result = list.get(0);
        }
        return result;
    }

    public void saveCar(Car car) {
        this.transact(session -> session.save(car));
    }

    public void closeCarAd(User user, int id) {
        this.transact(
                session -> session.createQuery(
                        "update Car c set c.status = false where c.id = :iParam and c.user = :uParam")
                        .setParameter("iParam", id)
                        .setParameter("uParam", user)
                        .executeUpdate()
        );
    }
}