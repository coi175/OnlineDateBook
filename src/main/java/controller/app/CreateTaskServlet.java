package controller.app;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.task.TaskDao;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Servlet that handles the user's request to create a new task
 */
@WebServlet(name = "CreateTaskServlet", urlPatterns = "/createTask")
public class CreateTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // Create new TaskDao to work with database with JDBC
        TaskDao taskDao = new TaskDao();
        // Create new json object which get data from client
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        // Get data from json object
        String title = data.get("title").getAsString();
        String description = data.get("description").getAsString();

        // parse time to into the desired format
        SimpleDateFormat parser = new SimpleDateFormat("hh:mm");
        Time time = null;
        try {
            time = new Time(parser.parse(data.get("time").getAsString()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String day = data.get("day").getAsString();

        // create state of task (new task = usually)
        String state = "usually";

        // calculate date of task
        Calendar cal = Calendar.getInstance();
        // if task create on tomorrow +1 to day of data of present
        if (day.equals("tomorrow")) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        // calculate date
        Date date = cal.getTime();
        // convert util.Date to sql.Date
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        // get user from session (we need userId)
        User user = (User) req.getSession().getAttribute("user");
        // create new task
        Task task = new Task(title, description, sqlDate, user.getId(), time, state);

        // add new task into database
        taskDao.addTask(task);

    }
}