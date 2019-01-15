package services;

import entity.Contact;
import entity.Detail;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by szpt_user045 on 11.01.2019.
 */
public class JsonParser {
    public static JSONObject toJSON(int status, Contact contact, List<Detail> details) {

        JSONObject json = toJSON(status, contact);

        JSONArray array = new JSONArray();

        for (Detail detail : details){
            JSONObject detailJson = new JSONObject();

            detailJson.put(detail.getKey(), detail.getValue());
            array.add(detailJson);

        }

        json.put("details", array);

        return json;
    }

    public static JSONObject toJSON(int status, Contact contact) {

        JSONObject json = toJSON(status);
        json.put("contact", contact.getName());
        return json;
    }

    public static JSONObject toJSON(int status) {
        JSONObject json = new JSONObject();
        json.put("status", status);
        return json;
    }
}
