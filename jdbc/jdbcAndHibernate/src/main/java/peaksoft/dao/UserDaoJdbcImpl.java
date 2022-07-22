package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    private final Util util = new Util();

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String createTable = "create table if not exists users(" +
                "id serial primary key," +
                "first_name varchar," +
                "last_name varchar," +
                "age INT);";

        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTable);
            System.out.println("table created ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("!!!");
        }
    }

    public void dropUsersTable() {
        String dropUsersTable = "drop table if exists users";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropUsersTable);
            System.out.println("table drop ");
        } catch (SQLException e) {
            e.getMessage();
            System.out.println("Exception");
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        String save = "insert into users(name,last_name,age)values(?,?,?)";
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(save)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("true");

        } catch (SQLException e) {
           e.printStackTrace();

        }
    }

    public void removeUserById(long id) {
        String remove = "delete from users where id = id";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(remove);
            System.out.println("true");
        } catch (SQLException e) {
            e.getMessage();
            System.out.println("*~*");
        }


    }

    public List<User> getAllUsers() {
        String allUsers = "select * from users";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(allUsers);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User("name", "last_name", "age");
                users.add(user);
            }

        } catch (SQLException e) {
            e.getMessage();
            System.out.println("^_^");
        }
        return null;
    }

    public void cleanUsersTable() {
        String usersTable = "delete from users";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(usersTable);
            System.out.println("true");
        } catch (SQLException e) {
            e.getMessage();
            System.out.println("*~*");
        }

    }
}