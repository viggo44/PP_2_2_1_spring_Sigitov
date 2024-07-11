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

        User user1 = new User("Artem", "Sigitov", "art200119@gmail.com");
        User user2 = new User("Alex", "WW", "@gmail.com");
        User user3 = new User("Max", "Smirnov", "aer@gmail.com");
        User user4 = new User("Andrey", "Ivanov", "kx@gmail.com");

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
//
        userService.linkUserToCar(user1, car1);
        userService.linkUserToCar(user2, car2);
        userService.linkUserToCar(user3, car3);
        userService.linkUserToCar(user4, car4);


//      userService.add(new Car(1,"Mazda"));
//      userService.add(new Car(2,"BMW"));
//      userService.add(new Car(2,"MAZDA"));
//      userService.add(new Car(3,"BMW"));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        List<User> usersMazda = userService.findUserByCar("Mazda", 2);
        for (User user : usersMazda) {
            System.out.println(user.getId() + " Имеет мазду 3 серии");
        }

        context.close();
    }
}
