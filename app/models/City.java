package models;

import play.db.jpa.*;

import javax.persistence.*;

/**
 * The City model
 */

@Entity
public class City extends Model {

    public int cityId;
    public String name;
    public int population;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTRY_ID")
    public Country parentCountry;

    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "INTEREST_ID")
    public Interest pointsOfInterest;//each city has its own interest object

    public City(int cityId, String name, int population ,Interest pointsOfInterest) {
        this.cityId = cityId;
        this.name = name;
        this.population = population;
        this.pointsOfInterest = pointsOfInterest;
    }

    /*

        @Entity
        public class Interest extends Model{

            private City city;

            public String pointInterest1;
            public String pointInterest2;
            public String pointInterest3;
            public String pointInterest4;

            private Interest(City c){

                city = c;
            }

            public City getCity(){
                return city;
            }


        }

        */

    /*
    public void addInterest()throws InterestAlreadySetException {

        if(pointsOfInterest==null){
            this.pointsOfInterest = new Interest(this);
        }else{
            throw new InterestAlreadySetException("Points of interest already set...");
        }

    }
    */


    /*
    public int getId() {
        return cityId;
    }

    */

    public String getName() {
        return name;
    }



    public int getPopulation() {
        return population;
    }

    /*

    public List<String> getPointsOfInterest() {
        return pointsOfInterest;
    }

    /*

    /**
     * using a string builder to output the city attributes
     */


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(name + " ~ Population: " + population + " | ");
        //sb.append("Points of Interest: ");
        /*
        for(int i = 0; i < getPointsOfInterest().size();i++){
            sb.append(getPointsOfInterest().get(i));
            if(i == getPointsOfInterest().size()-1)
                sb.append(".");//adds full stop after the last point of interest
            else
                sb.append(", ");
        }
        */

        return sb.toString();
    }


}
