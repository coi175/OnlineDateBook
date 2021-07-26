package controller.app;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.task.TaskDao;
import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet that handles the user's request to edit a task
 */
@WebServlet(name = "EditTaskServlet", urlPatterns = "/editTask")
public class EditTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        TaskDao taskDao = new TaskDao();
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);

        Long id = data.get("id").getAsLong();
        String title = data.get("title").getAsString();
        String description = data.get("description").getAsString();
        SimpleDateFormat parser = new SimpleDateFormat("hh:mm");
        Time time = null;
        try {
            time = new Time(parser.parse(data.get("time").getAsString()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // state of task (new)
        String state = "usually";
        // create new task
        Task task = new Task(id, title, description, time);

        // add new task into database
        taskDao.editTask(task);

    }
}