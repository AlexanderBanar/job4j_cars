package ru.job4j.store;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;
import ru.job4j.auto.Car;
import ru.job4j.auto.User;

public class AdRepository {
    private Transactor transactor = Transactor.instOf();

    private AdRepository() {
    }

    private static final class Lazy {
        private static final AdRepository INST = new AdRepository();
    }

    public static AdRepository instOf() {
        return Lazy.INST;
    }

    public List<Car> showCurrentDayAds() {
        long curTime = System.currentTimeMillis();
        long limitTime = curTime - 3600000 * 24;
        Date end = new Date(curTime);
        Date start = new Date(limitTime);
        return transactor.transact(
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
        return transactor.transact(
                session -> {
                    Query<Car> query = session.createQuery(
                            "select distinct c from Car c join fetch c.user join fetch c.photo p "
                                    + "where p.isAvailable = true");
                    return query.list();
                }
        );
    }

    public List<Car> showMyAds(User user) {
        return transactor.transact(
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
        return transactor.transact(
                session -> session.createQuery("from Car").list()
        );
    }
}