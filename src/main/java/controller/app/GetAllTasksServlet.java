package controller.app;

import com.google.gson.Gson;
import dao.task.TaskDao;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Servlet that handles the user's request to get list of task
 */
@WebServlet(name = "GetAllTasksServlet", urlPatterns = "/getAllTasks")
public class GetAllTasksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskDao taskDao = new TaskDao();

        // get user from session to get user id and use it in the request to database
        User user = (User) req.getSession().getAttribute("user");
        List<Task> tasks = taskDao.getAllTaskByUser(user.getId());

        // change state of tasks
        Calendar cal = Calendar.getInstance();
        for(Task task : tasks) {
            cal.setTime(task.getData());
            // count of days in month
            int dayInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            // day of task
            int day = cal.get(Calendar.DAY_OF_MONTH);
            // month of task
            int month = cal.get(Calendar.MONTH);

            // date of now
            cal.setTime(new Date());
            int dayNow = cal.get(Calendar.DAY_OF_MONTH);
            int monthNow = cal.get(Calendar.MONTH);

            // if month of task and now moths equals
            if(month == monthNow) {
                // delete task if it so old
                if (dayNow - day > 1) {
                    taskDao.deleteTaskById(task.getId());
                    // change state of task if it data was yesterday
                } else if(dayNow - day == 1) {
                    if (task.getState().equals("usually")) {
                        taskDao.updateTaskState(task.getId(), "fault");
                    } else if(task.getState().equals("marked")) {
                        taskDao.updateTaskState(task.getId(), "success");
                    }
                }
            } else {
                if (dayNow + dayInMonth - day > 1) {
                    taskDao.deleteTaskById(task.getId());
                } else if(dayNow + dayInMonth - day == 1) {
                    if (task.getState().equals("usually")) {
                        taskDao.updateTaskState(task.getId(), "fault");
                    } else if(task.getState().equals("marked")) {
                        taskDao.updateTaskState(task.getId(), "success");
                    }
                }
            }
        }

        tasks = taskDao.getAllTaskByUser(user.getId());
        // compare by status of task (usually or marked) and if it equals compare by time
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task, Task t1) {
                if(task.getState().equals("usually") && !t1.getState().equals("usually")) {
                    return -1;
                } else if(!task.getState().equals("usually") && t1.getState().equals("usually")) {
                    return 1;
                } else {
                    return task.getTime().compareTo(t1.getTime());
                }
            }
        });

        String json = new Gson().toJson(tasks);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}