package models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A singleton class used to deserialize the json atlas data
 */

public class SingleAtlas {

    private static SingleAtlas ourInstance = new SingleAtlas();

    //a method to get ourInstance:
    public static SingleAtlas getInstance() {

        if(ourInstance == null) {
            ourInstance = new SingleAtlas();
        }
        return ourInstance;
    }

    private SingleAtlas() { }// a private constructor ensures only 'ourInstance' is made

    /**
     *  This method de-serializes the 'countries' data from the seed_data.json file into an array list.
     *  Three loops:
     *  1) to get the countries data
     *      2) get the city data for that country
     *          3) get the points of interest data for that city
     *  The method catches any anomalies in the json data and allows the program to continue.
     */
    public List<Country> initializeAtlasFromJSON() {

            List<Country> atlasList = new ArrayList<Country>();

            try{

                System.out.println("trying to deserialize seed_data...");

                InputStream inputStream = ourInstance.getClass().getResourceAsStream("/seed_data.json");
                JSONTokener jsonTokener = new JSONTokener(inputStream);

                JSONObject data = new JSONObject(jsonTokener);

            if (data.has("countries")) {

                Country country;

                List<City> cityList;

                JSONArray countriesArray = data.getJSONArray("countries");

                //1) deserialise country data:
                for (int i = 0; i < countriesArray.length(); i++) {

                    int id = countriesArray.getJSONObject(i).getInt("id");
                    String name = countriesArray.getJSONObject(i).getString("name");
                    int capital = countriesArray.getJSONObject(i).getInt("capital");
                    JSONArray citiesArray = countriesArray.getJSONObject(i).getJSONArray("cities");

                     cityList = new ArrayList<City>();

                    //2) deserialize city data:
                    for (int j = 0; j < citiesArray.length(); j++) {

                        City city;

                        int id2 = citiesArray.getJSONObject(j).getInt("id");
                        String name2 = citiesArray.getJSONObject(j).getString("name");
                        int population = citiesArray.getJSONObject(j).getInt("population");

                        //deserialize the city's point of interest:
                        String interest1 = citiesArray.getJSONObject(j).getJSONObject("interest").getString("interest1");
                        String interest2 = citiesArray.getJSONObject(j).getJSONObject("interest").getString("interest2");
                        String interest3 = citiesArray.getJSONObject(j).getJSONObject("interest").getString("interest3");
                        String interest4 = citiesArray.getJSONObject(j).getJSONObject("interest").getString("interest3");

                        Interest cityInterests = new Interest(interest1,interest2,interest3,interest4).save();

                        cityList.add(new City(id2, name2, population, cityInterests).save());
                    }

                    country = new Country(id,name, capital, cityList);
                    country.setPopulation();

                    for(City city: cityList){
                        city.parentCountry = country.save();
                    }
                    atlasList.add(country.save());
                }
            }

                System.out.println("finished de-serializing seed_data...");
        } catch (JSONException e){
            System.out.println("Error in json data @: "+e.getMessage());
        }

        return atlasList;//returning the full atlas array list
    }


}
