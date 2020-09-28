package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        User user1 = new User("Jon", "Jones", (byte) 33);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());

        User user2 = new User("Israel", "Adesanya", (byte) 31);
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());

        User user3 = new User("Kamaru", "Usman", (byte) 33);
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());

        User user4 = new User("Stipe", "Miocic", (byte) 36);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
