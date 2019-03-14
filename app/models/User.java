package models;

import play.db.jpa.*;

import javax.persistence.Entity;

@Entity
public class User extends Model {

    public String name;
    public String password;
    public boolean isAdmin;

    public User(String name, String password) {
        this.password = password;
        this.name = name;
    }

    public static User connect(String name, String password) {
        return find("byNameAndPassword", name, password).first();
    }
}
