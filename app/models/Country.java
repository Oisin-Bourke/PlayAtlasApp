package models;

import java.util.List;

import play.db.jpa.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * The Country model implements the comparable interface, used to
 * compare this Country to another destination Country for the purposes
 * of getting the bordering status. Each country has its own city list.
 * The Country's population is the sum of its city's populations (plus 100% to
 * account for rural).
 */

@Entity
public class Country extends Model implements Comparable <Country> {

    public int countryId;
    public String name;
    public int capital;
    public int population;

    @OneToMany(mappedBy = "parentCountry")
    public List <City> cityList;//each country has its own cities

    public Country(int countryId, String name, int capital, List<City> cityList) {
        this.countryId = countryId;
        this.name = name;
        this.capital = capital;
        this.cityList = cityList;

        setPopulation();//set total population when country instantiated
        population = getPopulation();
    }

    /*
    public int getId() {
        return countryId;
    }

    public String getName() {
        return name;
    }

    public int getCapitalId() {
        return capital;
    }

    public List<City> getCityList() {
        return cityList;
    }

    */
    public int getPopulation() {
        return population;
    }



    /**
     * Iterating through each country's city list to calculate
     * the total population, doubling the total city population to
     * account for rural areas.
     */

    public void setPopulation() {

        int totalPopulation = 0;

        for(City city:cityList){

            totalPopulation += city.getPopulation();
        }
        population = totalPopulation * 2;
    }



    /**
     * A method that returns the capital city
     * from the country's city list by finding
     * the matching countryId.
     */


    public City getCapitalCity(){

        City c = null;

        for (City city : cityList){

            if(city.getId() == capital) c = city;

        }

        return c;
    }


    /** Implementation of Comparable interface, which decides
     * the type of travel object required, by establishing whether
     * the origin and destination are in the same country (0),
     * a bordering country (1), or a non-bordering country (-1)
     * The method uses the AccessBordering static checkBordering method
     * that returns a boolean.
     * **/


    public int compareTo(Country destination) {

        /*
        if(name.equals(destination.name))
            return 0;//if in the same country ==> Bus()
        if(AccessBordering.checkBordering(this,destination))
            return 1;//if bordering true ==> Train()

        return -1;//must be non-bordering ==> Fly()
        */
        return 0;
    }


    /**
     * Using the string builder class to present a tidy representation
     * of the country for atlas list.
     */


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(countryId+") ");
        sb.append("Country: " + name + ",\t");
        sb.append("Population: " + population + ",\t");
        sb.append("Capital: " + getCapitalCity().name + ".\n");
        sb.append(name+"'s Cities: "+ "\n");
        for(City city:cityList){
            sb.append(city +"\n");
        }
        return sb.toString();
    }


}
