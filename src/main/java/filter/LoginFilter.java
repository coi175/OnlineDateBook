package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        String startPageURI = request.getContextPath() + "/";

        boolean isLogged = session != null && session.getAttribute("user") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean startPageRequest = request.getRequestURI().equals(startPageURI);
        boolean registerRequest = request.getRequestURI().equals(registerURI);
        boolean publicResources = request.getRequestURI().endsWith(".css") || request.getRequestURI().endsWith(".js") || request.getRequestURI().endsWith(".jpg");

        if(isLogged || loginRequest || registerRequest || publicResources || startPageRequest) {
            chain.doFilter(request, response);
        }
        else {
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {

    }
}
