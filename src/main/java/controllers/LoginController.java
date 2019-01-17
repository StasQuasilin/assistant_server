package controllers;

import entity.User;
import filters.LoginFilter;
import services.Hibernator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by szpt_user045 on 17.01.2019.
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final Hibernator hibernator = Hibernator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        System.out.println(login + ":" + password);

        String base64 = Base64.getEncoder().encodeToString((login + ":" + password).getBytes());

        System.out.println(base64);

        User user = hibernator.get(User.class, "base64", base64);

        if (user != null) {
            LoginFilter.addUser(user, req);
            resp.sendRedirect(req.getContextPath() + "/admin.j");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
