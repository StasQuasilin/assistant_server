package entity;

import javax.persistence.*;

/**
 * Created by szpt_user045 on 11.01.2019.
 */
@Entity
@Table(name = "users")
public class User {
    private int id;
    private String base64;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "base64")
    public String getBase64() {
        return base64;
    }
    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
