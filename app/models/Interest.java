package models;

import play.db.jpa.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Interest extends Model {

    public String pointInterest1;
    public String pointInterest2;
    public String pointInterest3;
    public String pointInterest4;


}
