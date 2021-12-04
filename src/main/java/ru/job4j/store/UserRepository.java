package ru.job4j.store;

import org.hibernate.query.Query;
import ru.job4j.auto.User;

import java.util.List;

public class UserRepository {
    private Transactor transactor = Transactor.instOf();

    private UserRepository() {
    }

    private static final class Lazy {
        private static final UserRepository INST = new UserRepository();
    }

    public static UserRepository instOf() {
        return UserRepository.Lazy.INST;
    }

    public void saveUser(User user) {
        transactor.transact(session -> session.save(user));
    }

    public User getUser(User user) {
        List<User> list = transactor.transact(
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
        List<User> list = transactor.transact(
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
}
