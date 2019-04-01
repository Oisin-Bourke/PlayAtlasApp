package controllers;

import play.*;
import play.db.Model;
import play.db.jpa.JPA;
import play.mvc.*;

import java.util.*;
import java.util.concurrent.Callable;

import models.*;

import javax.persistence.Query;

public class Application extends Controller {

    public static void index() {

        //System.out.println("a message");

        List<Country> countryList = Country.find("order by countryId asc").from(0).fetch();

        render(countryList);
    }

    /*
    public static void myName(String myName){
        render(myName);
    }

    */

    public static void search(Long id){

        //id = 9l;

        Country country = Country.findById(id);

        /*
        Query query = JPA.em().createQuery("Select * from City");

        List <City> cities = query.getResultList();
        */

        //List <City> cities = City.all().fetch(); ==> gets all

        //List<City> cities = City.find("country_id = 9").fetch();

        //List<City> cities = City.find("country_id = 9").from(0).fetch();

       //List<City> cities = (List<City>) City.find("select * from City where country_id = 9");

        //List<City> cities = City.find("country_id = 9").fetch();


        //System.out.println("is empty: "+cities.isEmpty());

        /*
        for (City city : cities){
            System.out.println(city);
        }
        */

        render(country);
    }

    public static void searchCity(Long id){

        id = 2l;

        City city = City.findById(id);

        render(city);
    }


    public static void ShowCountryById(long Id){

        Id = 23;

        Country selected = Country.findById(Id);

        render(selected);

    }

    public static void showCountry(long id){

        Country country = Country.findById(id);

        System.out.println("the id"+id);

        render(country);


    }









}