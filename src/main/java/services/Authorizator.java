package services;

import entity.User;
import org.apache.log4j.Logger;

import java.util.Base64;

/**
 * Created by szpt_user045 on 11.01.2019.
 */
public class Authorizator {

    static final Hibernator hibernator = Hibernator.getInstance();
    private static final Logger log = Logger.getLogger(Authorizator.class);

    public static boolean authorize(String value){
        log.info(value);

        String[] split = value.split(" ");

        AuthorizationType authType = AuthorizationType.valueOf(split[0].toLowerCase());

        switch (authType){
            case basic:
                String base64 = split[1];

                return hibernator.get(User.class, "base64", base64) != null;
        }

        return false;
    }

    enum AuthorizationType {
        basic
    }
}
