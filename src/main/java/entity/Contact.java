package entity;

import javax.persistence.*;

/**
 * Created by szpt_user045 on 11.01.2019.
 */
@Entity
@Table(name = "contacts")
public class Contact {
    private int id;
    private String number;
    private String name;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name="number")
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
