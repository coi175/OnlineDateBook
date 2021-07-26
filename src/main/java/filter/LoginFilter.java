package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for login
 */
@WebFilter(urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login";
        String registerURI = request.getContextPath() + "/register";

        // if user is logged
        boolean isLogged = session != null && session.getAttribute("user") != null;
        // if user try to enter to the login page
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        // if user try to enter to the register page
        boolean registerRequest = request.getRequestURI().equals(registerURI);
        // if app try to get some resource
        boolean publicResources = request.getRequestURI().endsWith(".css") || request.getRequestURI().endsWith(".js") || request.getRequestURI().endsWith(".jpg");

        // then filter allow to enter
        if(isLogged || loginRequest || registerRequest || publicResources) {
            chain.doFilter(request, response);
        }
        else {
            // else app send redirect to the login page
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {

    }
}
