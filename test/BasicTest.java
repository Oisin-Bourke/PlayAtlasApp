import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    //first iteration of mapping test
    @Test
    public void createAndRetrieveCountry(){

        //create a new country and save:
        Interest cityInterest1 = new Interest();
        cityInterest1.pointInterest1="Parliament Building";
        cityInterest1.pointInterest2="National Museum";
        cityInterest1.pointInterest3="Cathedral";

        Interest cityInterest2 = new Interest();
        cityInterest2.pointInterest1="Tourist Information";
        cityInterest2.pointInterest2="Pub";

        List<City> cityList = new ArrayList<>();

        cityList.add(new City(101,"Dublin",1000000,cityInterest1));
        cityList.add(new City(102,"Galway",100000,cityInterest2));
        cityList.add(new City(103,"Cork",200000,cityInterest2));
        cityList.add(new City(104,"Limerick",100000,cityInterest2));

        new Country (1,"Ireland",101,cityList).save();

        //retrieve country by countryId
        Country ireland = Country.find("byCountryId",1).first();

        //test
        assertNotNull(ireland);
        assertEquals("Ireland",ireland.name);

    }


}
