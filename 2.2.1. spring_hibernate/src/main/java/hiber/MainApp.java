package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car(1, "Mazda");
        Car car2 = new Car(2, "BMW");
        Car car3 = new Car(2, "MAZDA");
        Car car4 = new Car(3, "BMW");

        User user1 = new User("Artem", "Sigitov", "art200119@gmail.com", car1);
        User user2 = new User("Alex", "WW", "@gmail.com", car2);
        User user3 = new User("Max", "Smirnov", "aer@gmail.com", car3);
        User user4 = new User("Andrey", "Ivanov", "kx@gmail.com", car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        System.out.println(userService.findUserByModelAndSeries("Mazda", 2));

        context.close();
    }
}
