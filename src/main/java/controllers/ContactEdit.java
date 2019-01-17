package controllers;

import entity.Contact;
import entity.Detail;
import services.Hibernator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by szpt_user045 on 17.01.2019.
 */
@WebServlet("/contact/edit.j")
public class ContactEdit extends HttpServlet {
    private static final Hibernator hibernator = Hibernator.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("contact", hibernator.get(Contact.class, "id", id));
            req.setAttribute("details", hibernator.Query(Detail.class,"contact", id));
        } catch (Exception ignored){}

        req.getRequestDispatcher("/pages/edit/edit.contact.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Contact contact;
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            contact = hibernator.get(Contact.class, "id", id);
        }catch (Exception ignored){
            contact = new Contact();
        }

        contact.setNumber(req.getParameter("number"));
        contact.setName(req.getParameter("name"));

        hibernator.save(contact);

        String[] detail_ids = req.getParameterValues("detail_id");
        String[] keys = req.getParameterValues("key");
        String[] values = req.getParameterValues("value");

        List<Detail> details = hibernator.Query(Detail.class, "contact", contact);
        List<Detail> saveList = new LinkedList<>();

        for (int i = 0; i < keys.length; i++) {
            Detail detail = null;
            try {
                int detailId = Integer.parseInt(detail_ids[i]);
                for(Detail d : details){
                    if (d.getId() == detailId){
                        detail = d;
                        break;
                    }
                }
            }catch (Exception ignored){
                detail = new Detail();
            }
            details.remove(detail);

            assert detail != null;
            saveList.add(detail);
            detail.setContact(contact);
            detail.setKey(keys[i]);
            detail.setValue(values[i]);
        }

        details.forEach(hibernator::remove);
        saveList.forEach(hibernator::save);
        resp.sendRedirect(req.getContextPath() + "/admin.j");

    }
}
