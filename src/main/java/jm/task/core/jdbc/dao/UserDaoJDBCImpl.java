//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl extends Util implements UserDao {
//    private final Connection connection;
//    private String sql;
//    private List<User> userList;
//
//    public UserDaoJDBCImpl() {
//        connection = getConnection();
//    }
//
//    public void createUsersTable() {
//        sql = """
//                CREATE TABLE `MyBDtest`.`Users` (`ID` INT(11) NOT NULL AUTO_INCREMENT ,
//                 `Name` VARCHAR(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
//                 `LastName` VARCHAR(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
//                 `Age` INT(3) NOT NULL , PRIMARY KEY (`ID`)) ENGINE = InnoDB CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;
//                """;
//        try (Statement statement = connection.createStatement()) {
//            statement.execute(sql);
//        } catch (SQLException ignore) {
//        }
//    }
//
//    public void dropUsersTable() {
//        sql = "DROP TABLE Users";
//
//        try (Statement statement = connection.createStatement()) {
//            statement.execute(sql);
//        } catch (SQLException ignore) {
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        sql = "INSERT INTO Users(Name, LastName, Age) VALUES (?, ?, ?)";
//
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, name);
//            statement.setString(2, lastName);
//            statement.setInt(3, age);
//            statement.executeUpdate();
//            System.out.printf("User с именем - %s добавлен в базу данных\n", name);
//        } catch (SQLException ignore) {}
//    }
//
//    public void removeUserById(long id) {
//        sql = "DELETE FROM Users WHERE ID IN (?)";
//
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setLong(1, id);
//            statement.executeUpdate();
//        } catch (SQLException ignore) {
//        }
//    }
//
//    public List<User> getAllUsers() {
//        userList = new ArrayList<>();
//        sql = "SELECT * FROM Users";
//
//        try (Statement statement = connection.createStatement()) {
//            ResultSet rs = statement.executeQuery(sql);
//
//            while (rs.next()) {
//                User user = new User(
//                        rs.getString("Name"),
//                        rs.getString("LastName"),
//                        rs.getByte("Age"));
//                user.setId(rs.getLong("ID"));
//                userList.add(user);
//            }
//        } catch (SQLException ignore) {}
//
//        return userList;
//    }
//
//    public void cleanUsersTable() {
//        sql = "DELETE FROM Users";
//
//        try (Statement statement = connection.createStatement()) {
//            statement.execute(sql);
//        } catch (SQLException ignore) {
//        }
//    }
//}
