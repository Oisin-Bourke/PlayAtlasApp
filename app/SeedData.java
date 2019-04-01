import play.*;
import play.jobs.*;
import play.test.*;

import models.*;

import java.util.List;

@OnApplicationStart
public class SeedData extends Job {

    public void doJob() {

        System.out.println("loading doJob for seed data...");

        // Check if the database is empty
        if(Country.count() == 0) {

            System.out.println("country count * is zero...");

            final List<Country> theAtlas;//a final local variable to hold the atlas

            //using the singleton SingleAtlas class to initialise the atlas
            SingleAtlas atlas = SingleAtlas.getInstance();
            theAtlas = atlas.initializeAtlasFromJSON();

            System.out.println("seed data loaded!");
        }else{
            System.out.println("database already seeded!");
        }
    }
}
