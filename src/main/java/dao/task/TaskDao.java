package dao.task;

import dao.utils.DaoConnectionManager;
import model.Task;

import java.sql.*;

public class TaskDao {
    private Connection connection;

    private static final String INSERT_TASK_SQL = "INSERT INTO tasks" +
            " (title, data, time, description, user_id, state) VALUES " +
            " (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_TASK_BY_ID = "select id, title, data, time, description, user_id, state " +
            "from tasks where id =?";
    private static final String EDIT_TASK_SQL = "UPDATE tasks SET title = ?, description = ?, time = ? WHERE id = ?";
    public TaskDao() {
        connection = DaoConnectionManager.getConnection();
    }

    public void addTask(Task task) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK_SQL)) {
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setDate(2, task.getData());
            preparedStatement.setTime(3, task.getTime());
            preparedStatement.setString(4, task.getDescription());
            preparedStatement.setLong(5, task.getUserId());
            preparedStatement.setString(6, task.getState());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Get all info about task from database
     * @param id
     * @return
     */
    public Task getTask(Long id) {
        Task task = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long taskId = rs.getLong("id");
                String title = rs.getString("title");
                Date date = rs.getDate("data");
                Long userId = rs.getLong("user_id");
                Time time = rs.getTime("time");
                String description = rs.getString("description");
                String state = rs.getString("state");
                task = new Task(taskId, title, description, date, userId, time, state);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return task;
    }

    /**
     * Function for editing information about task in database
     * @param task
     */
    public void editTask(Task task) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(EDIT_TASK_SQL)) {
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setTime(3, task.getTime());
            preparedStatement.setLong(4, task.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
