package filters;

import entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by szpt_user045 on 17.01.2019.
 */
@WebFilter(value = {"*.j", "/"})
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Object oldToken = request.getSession().getAttribute("token");
        if (oldToken != null) {

            if (USER_HASH_MAP.containsKey(oldToken.toString())){
                User remove = USER_HASH_MAP.remove(oldToken.toString());
                String token = getToken();
                put(request, remove, token);
                USER_HASH_MAP.put(token, remove);
                put(request, remove, token);
                filterChain.doFilter(request, servletResponse);
            } else {
                redirect(request, servletResponse);
            }

        } else {
            redirect(request, servletResponse);
        }
    }

    private void redirect(HttpServletRequest request, ServletResponse servletResponse) throws IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect(request.getContextPath() + "/login");
    }

    private static void put(HttpServletRequest request, User user, String token) {
        request.getSession().setAttribute("token", token);
        USER_HASH_MAP.put(token, user);
    }

    @Override
    public void destroy() {

    }
    private static final HashMap<String, User> USER_HASH_MAP = new HashMap<>();

    public static void addUser(User user, HttpServletRequest req) {
        String token = getToken();
        put(req, user, token);
    }

    private static String getToken() {
        String token = UUID.randomUUID().toString();

        if (USER_HASH_MAP.containsKey(token)){
            token = getToken();
        }

        return token;
    }
}
