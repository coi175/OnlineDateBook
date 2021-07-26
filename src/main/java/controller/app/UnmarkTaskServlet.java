package controller.app;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.task.TaskDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that handles the user's request to change status of task (unmark task)
 */
@WebServlet(name = "UnmarkTaskServlet", urlPatterns = "/unmarkTask")
public class UnmarkTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskDao taskDao = new TaskDao();
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        Long id = data.get("id").getAsLong();

        taskDao.updateTaskState(id, "usually");
    }
}
