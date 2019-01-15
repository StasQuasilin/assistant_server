package controllers;

import entity.RequestStatus;
import org.apache.log4j.Logger;
import services.Authorizator;
import services.DataParser;
import services.JsonParser;
import services.PostParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by szpt_user045 on 11.01.2019.
 */
@WebServlet("/whois")
public class MainController extends HttpServlet {

    Writer writer;
    static final String CHARSET_ENCODING = "UTF-8";
    final DataParser parser = new DataParser();
    final String authKey = "authorization";
    final String numberKey = "number";
    private static final Logger log = Logger.getLogger(MainController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, String> headers = PostParser.parseHeaders(req);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            log.info(entry.getKey() + ":" + entry.getValue());
        }
        HashMap<String, String> body = PostParser.parseBody(req);
        for (Map.Entry<String, String> entry : body.entrySet()) {
            log.info(entry.getKey() + ":" + entry.getValue());
        }

        String answer = JsonParser.toJSON(RequestStatus.UNAUTHORIZED).toJSONString();

        if (headers.containsKey(authKey)) {
            if(Authorizator.authorize(headers.get(authKey))){
                if (body.containsKey(numberKey)){
                    String number = body.get(numberKey);
                    answer = parser.WhoIs(number).toJSONString();
                } else {
                    answer = JsonParser.toJSON(RequestStatus.UNKNOWN).toJSONString();
                }
            }
        }

        resp.setCharacterEncoding(CHARSET_ENCODING);
        writer = resp.getWriter();
        writer.write(answer);
        writer.close();

    }
}
