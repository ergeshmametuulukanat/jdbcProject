package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String CREATE_TABLE_SQL = "create table if not exists users" +
                "(id bigint not null auto_increment," +
                "name varchar(45) not null," +
                "last_name varchar(45) not null," +
                "age tinyint not null," +
                "primary key (id))";
        try (Connection conn = Util.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(CREATE_TABLE_SQL);
            System.out.println("Table created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String DROP_TABLE_SQL = "drop table users";
        try (Connection conn = Util.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(DROP_TABLE_SQL);
            System.out.println("Table dropped");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SAVE_USER_SQL = "insert into users(name, last_name, age) values (?,?,?)";
        try (Connection conn = Util.connect();
             PreparedStatement stmt = conn.prepareStatement(SAVE_USER_SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
            System.out.println(name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String REMOVE_USER_SQL = "delete from users where id = ?";
        try (Connection conn = Util.connect();
             PreparedStatement stmt = conn.prepareStatement(REMOVE_USER_SQL)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.println("User is deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String GET_ALL_USERS_SQL = "select * from users";
        try (Connection conn = Util.connect();
             PreparedStatement stmt = conn.prepareStatement(GET_ALL_USERS_SQL);
             ResultSet rs = stmt.executeQuery()) {
            User user;
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setAge(rs.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        String CLEAN_USERS_TABLE_SQL = "truncate users";
        try (Connection conn = Util.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(CLEAN_USERS_TABLE_SQL);
            System.out.println("Table is cleaned");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
