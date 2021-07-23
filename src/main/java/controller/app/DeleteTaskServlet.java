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

@WebServlet(name = "DeleteTaskServlet", urlPatterns = "/deleteTask")
public class DeleteTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskDao taskDao = new TaskDao();
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);

        Long id = data.get("id").getAsLong();
        taskDao.deleteTaskById(id);

    }
}
