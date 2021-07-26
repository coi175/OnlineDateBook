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

/**
 * Servlet that handles the user's request to get one task
 */
@WebServlet(name = "GetTaskServlet", urlPatterns = "/getTask")
public class GetTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskDao taskDao = new TaskDao();
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);

        Long id = data.get("id").getAsLong();
        Task task = taskDao.getTask(id);
        String json = new Gson().toJson(task);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}