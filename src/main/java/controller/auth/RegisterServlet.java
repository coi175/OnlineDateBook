package controller.auth;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String message;

        boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));

        if(ajax) {
            if(isUserExist()) {
                message = "User with this username already exist";
            }

            else if(!validateUserInput()) {
                message = "Something wrong with your input. Please contact tech support";
            }

            else {
                req.getSession().setAttribute("isRegistered", true);
                message = "Success";
            }
            resp.getWriter().write(errorMessage);

        }

        else {
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        } */
    }
}
