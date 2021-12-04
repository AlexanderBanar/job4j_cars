package ru.job4j.store;

import ru.job4j.auto.Car;
import ru.job4j.auto.User;

public class CarRepository {
    private Transactor transactor = Transactor.instOf();

    private CarRepository() {
    }

    private static final class Lazy {
        private static final CarRepository INST = new CarRepository();
    }

    public static CarRepository instOf() {
        return CarRepository.Lazy.INST;
    }

    public void saveCar(Car car) {
        transactor.transact(session -> session.save(car));
    }

    public void closeCarAd(User user, int id) {
        transactor.transact(
                session -> session.createQuery(
                        "update Car c set c.status = false where c.id = :iParam and c.user = :uParam")
                        .setParameter("iParam", id)
                        .setParameter("uParam", user)
                        .executeUpdate()
        );
    }
}
