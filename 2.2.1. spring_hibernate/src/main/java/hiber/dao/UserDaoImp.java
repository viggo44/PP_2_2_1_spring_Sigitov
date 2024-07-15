package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override

    public List<User> listUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public User findUserByModelAndSeries(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User where car.model = :paramModel and car.series = :paramSeries", User.class);
        query.setParameter("paramModel", model);
        query.setParameter("paramSeries", series);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // или можно выбросить свое кастомное исключение
        }
    }


}
