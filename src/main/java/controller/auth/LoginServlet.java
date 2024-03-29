package controller.auth;

import dao.user.UserDao;
import model.PasswordSecurityService;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Servlet that handles the user's request login in account
 */
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
        UserDao userDao = new UserDao();

        // get login and password from request
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // find user by username (login)
        User user = userDao.getUserByUsername(username);

        try {
            // if user with that login and password in database, then user, else null
            boolean matches = PasswordSecurityService.validatePassword(password, user.getPassword());
            if(!matches) {
                user = null;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }


        boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
        if(ajax) {
            // if user is exist (User)
            if(user != null) {
                // send success answer to client and put user into a session
                req.getSession().setAttribute("user", user);
                resp.getWriter().write("Success");
            }
            else {
                // else if user is not exist (null), send fault answer to client
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
