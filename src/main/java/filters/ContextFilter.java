package filters;

import org.apache.log4j.Logger;
import services.HibernateSessionFactory;
import services.Hibernator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Quasilin on 09.09.2018.
 */
@WebFilter(value = "*")
public class ContextFilter implements Filter {

    private static final Logger log = Logger.getLogger(ContextFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Start context filter");
        HibernateSessionFactory.getSessionFactory();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        req.setAttribute("CONTEXT", req.getContextPath());
        req.setCharacterEncoding("UTF-8");
        filterChain.doFilter(req, servletResponse);
    }

    public void destroy() {
        HibernateSessionFactory.shutdown();
    }
}
