package controller.auth;

import service.ValidationService;
import org.json.simple.JSONValue;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name="RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Boolean> responseJson = new LinkedHashMap<>();

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeatedPassword = req.getParameter("repeat_password");

        boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));

        if(ajax) {
            responseJson.put("isValidUsername", ValidationService.validateUsername(username));
            responseJson.put("isValidEmail", ValidationService.validateEmail(email));
            responseJson.put("isValidPassword", ValidationService.validatePassword(password));
            responseJson.put("isValidPasswordMatch", ValidationService.validatePasswordMatch(password, repeatedPassword));
            responseJson.put("userNotExist", !isAlreadyExist());

            if(!responseJson.containsValue(false)) {
                req.getSession().setAttribute("isRegistered", true);
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

    private boolean isAlreadyExist() {
        return false;
    }
}
