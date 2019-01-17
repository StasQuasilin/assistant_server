package controllers;

import entity.Contact;
import entity.User;
import services.Hibernator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

/**
 * Created by szpt_user045 on 17.01.2019.
 */
@WebServlet("/admin.j")
public class AdminController extends HttpServlet {

    private static final Hibernator hibernator = Hibernator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, String> res = new HashMap<>();
        for (User u : hibernator.Query(User.class, null)){
            String login = new String(Base64.getDecoder().decode(u.getBase64())).split(":")[0];
            res.put(String.valueOf(u.getId()), login);
        }
        req.setAttribute("users", res);
        req.setAttribute("contacts", hibernator.Query(Contact.class, null));
        req.getRequestDispatcher("/pages/admin.jsp").forward(req, resp);
    }
}
