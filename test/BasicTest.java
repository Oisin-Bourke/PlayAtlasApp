import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    //delete DB before each test:
    @Before
    public void setup() {
        Fixtures.deleteDatabase();
    }

    //first iteration of mapping test:
    @Test
    public void createAndRetrieveCountry(){

        //create a new country and save:
        Interest cityInterest1 = new Interest().save();
        cityInterest1.pointInterest1="Parliament Building";
        cityInterest1.pointInterest2="National Museum";
        cityInterest1.pointInterest3="Cathedral";

        Interest cityInterest2 = new Interest().save();
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

        //test country
        assertNotNull(ireland);
        assertEquals("Ireland",ireland.name);

        //test city in country
        assertNotNull(ireland.cityList.get(0));
        assertEquals("Dublin",ireland.cityList.get(0).name);

        //test point of interest in city
        assertNotNull(ireland.cityList.get(0).pointsOfInterest);
        assertEquals("Parliament Building",ireland.cityList.get(0).pointsOfInterest.pointInterest1);

    }

    //test user connect credentials:
    @Test
    public void checkUserCredentials(){

        //create a new user
        new User("Luke","Junk100").save();

        //test
        assertNotNull(User.connect("Luke","Junk100"));
        assertNull(User.connect("Luke","WrongPassword"));
    }


}
