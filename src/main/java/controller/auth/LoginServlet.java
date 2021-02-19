package controller.auth;

import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = (username.equals("admin") && password.equals("admin")) ? new User("admin", "admin", "") : null;


        boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
        if(ajax) {
            if(user != null) {
                req.getSession().setAttribute("user", user);
                resp.getWriter().write("Success");
            }
            else {
                resp.getWriter().write("Invalid login or password");
            }
        }
        else {
            if(user != null) {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/home");
            }
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }

    }

}
