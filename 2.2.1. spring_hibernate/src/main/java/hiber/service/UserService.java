package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();

    void add (Car car);
    List<User> findUserByCar(String model, int series);

    public void linkUserToCar(User user, Car car);

}
