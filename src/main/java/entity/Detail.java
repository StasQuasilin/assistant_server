package entity;

import javax.persistence.*;

/**
 * Created by szpt_user045 on 11.01.2019.
 */
@Entity
@Table(name="details")
public class Detail {
    private int id;
    private Contact contact;
    private String key;
    private String value;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "contact")
    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Basic
    @Column(name = "detail_key")
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "detail_value")
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
