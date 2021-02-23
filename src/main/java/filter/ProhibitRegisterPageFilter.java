package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/register"})
public class ProhibitRegisterPageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        boolean isRegistered = session != null && session.getAttribute("isRegistered") != null;
        boolean isLogged = session != null && session.getAttribute("user") != null;

        if(!isRegistered && !isLogged) {
            chain.doFilter(request, response);
        }
        else {
            response.sendRedirect("/home");
        }
    }

    @Override
    public void destroy() {

    }
}
