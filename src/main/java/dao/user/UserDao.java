package dao.user;

import dao.utils.DaoConnectionManager;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection connection;

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (username, email, password) VALUES " +
            " (?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "select id, username, email, password from users where id =?";
    private static final String SELECT_USER_BY_USERNAME = "select id, username, email, password from users where username = ?";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set email = ?, password = ? where id = ?;";

    public UserDao() {
        connection = DaoConnectionManager.getConnection();
    }

    public void addUser(User user) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User getUserById(Long userId) {
        User user = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                user = new User(userId, username, email, password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public User getUserByUsername(String username) {
        User user = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                user = new User(id, name, email, password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public void deleteUser(int userId) {

    }

    public void updateUser() {

    }
}
