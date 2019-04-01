package models;

import play.db.jpa.*;

import javax.persistence.Entity;


@Entity
public class Interest extends Model {

    public String pointInterest1;
    public String pointInterest2;
    public String pointInterest3;
    public String pointInterest4;

    public Interest(){}

    public Interest(String pointInterest1, String pointInterest2, String pointInterest3, String pointInterest4) {
        this.pointInterest1 = pointInterest1;
        this.pointInterest2 = pointInterest2;
        this.pointInterest3 = pointInterest3;
        this.pointInterest4 = pointInterest4;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "pointInterest1='" + pointInterest1 + '\'' +
                ", pointInterest2='" + pointInterest2 + '\'' +
                ", pointInterest3='" + pointInterest3 + '\'' +
                ", pointInterest4='" + pointInterest4 + '\'' +
                '}';
    }
}

