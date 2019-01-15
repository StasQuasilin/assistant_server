package services;

import entity.Contact;
import entity.Detail;
import entity.RequestStatus;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by szpt_user045 on 11.01.2019.
 */
public class DataParser {

    Hibernator hibernator = Hibernator.getInstance();

    public JSONObject WhoIs(String number){

        if (number.length() > 10) {
            number = number.substring(number.length() - 10);
        }

        Contact contact = hibernator.get(Contact.class, "number", number);

        if (contact == null) {
            return JsonParser.toJSON(RequestStatus.UNKNOWN);
        }

        List<Detail> details = hibernator.Query(Detail.class, "contact", contact);

        return JsonParser.toJSON(RequestStatus.OK, contact, details);
    }
}
