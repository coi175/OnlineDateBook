package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter that prohibit to enter to login page if user already log in
 */
@WebFilter(urlPatterns = {"/login"})
public class ProhibitLoginPageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        boolean isLogged = session != null && session.getAttribute("user") != null;

        // if user don't log in
        if(!isLogged) {
            // he can enter to login page
            chain.doFilter(request, response);
        }
        else {
            // else app will redirect to the /home page
            response.sendRedirect("/home");
        }
    }

    @Override
    public void destroy() {

    }
}
