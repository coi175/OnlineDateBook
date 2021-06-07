package controller.auth;

import dao.user.UserDao;
import model.User;
import org.json.simple.JSONValue;
import model.PasswordSecurityService;
import model.ValidationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name="RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDao = new UserDao();

        Map<String, Boolean> responseJson = new LinkedHashMap<>();


        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = null;
        String repeatedPassword = null;
        try {
            password = PasswordSecurityService.generateStrongPasswordHash(req.getParameter("password"));
            repeatedPassword = PasswordSecurityService.generateStrongPasswordHash(req.getParameter("repeat_password"));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));

        if(ajax) {
            responseJson.put("isValidUsername", ValidationService.validateUsername(username));
            responseJson.put("isValidEmail", ValidationService.validateEmail(email));
            //responseJson.put("userNotExist", !isAlreadyExist(username, email));

            if(!responseJson.containsValue(false)) {
                req.getSession().setAttribute("isRegistered", true);
                userDao.addUser(new User(username, email, password));
                responseJson.put("message", true);
            }
            String json = JSONValue.toJSONString(responseJson);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);

        }

        else {
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }

    private boolean isAlreadyExist(String username, String email) {
        User user = userDao.getUserByUsername(username);
        if(user != null && user.getName().equals(username) || user.getEmail().equals(email)) {
            return true;
        }
        return false;
    }
}
