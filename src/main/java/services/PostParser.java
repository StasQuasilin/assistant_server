package services;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Created by szpt_user045 on 11.01.2019.
 */
public class PostParser {

    public static HashMap<String,String> parseHeaders(HttpServletRequest req) {
        HashMap<String,String> result = new HashMap<>();

        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String key = headerNames.nextElement();
            result.put(key, req.getHeader(key));
        }

        return result;
    }

    public static HashMap<String, String> parseBody(HttpServletRequest req) throws IOException {
        HashMap<String,String> result = new HashMap<>();

        String collect = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String[] split = collect.split("&");

        for (String s : split){
            String[] sub = s.split("=");
            result.put(sub[0],sub[1]);
        }
        return result;
    }
}
