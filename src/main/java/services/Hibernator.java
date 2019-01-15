package services;

import entity.Contact;
import entity.Detail;
import org.hibernate.Session;

import javax.persistence.criteria.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by szpt_user045 on 11.01.2019.
 */
public class Hibernator {

    private static final Hibernator instance = new Hibernator();

    public static Hibernator getInstance() {
        return instance;
    }

    public <T>List<T> Query(Class<T> tClass, HashMap<String, Object> parameters){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(tClass);
        Root<T> from = query.from(tClass);

        if (parameters != null) {

            Predicate[] predicates = new Predicate[parameters.size()];

            int i = 0;
            for (Map.Entry<String, Object> entry : parameters.entrySet()){
                predicates[i] = criteriaBuilder.equal(from.get(entry.getKey()), entry.getValue());
            }

            query.where(predicates);
        }

        return session.createQuery(query).getResultList();
    }

    HashMap<String, Object> params = new HashMap<>();
    public <T> T get(Class<T> tClass, String key, String value) {
        params.clear();
        params.put(key, value);
        List<T> query = Query(tClass, params);

        if (query.size() > 0){
            return query.get(0);
        }
        return null;
    }

    public <T> List<T> Query(Class<T> tClass, String key, Object value) {
        params.clear();
        params.put(key, value);
        return Query(tClass, params);
    }
}
