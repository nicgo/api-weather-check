import builder.Location_Builder;
import domain.Location;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import persistence.DAOLocation;
import java.sql.SQLException;

/**
 * Created by Nico on 7/10/2016.
 */
public class Location_Test {

    @Test
    public void test_Location_dataCreationByDefault(){

            Location_Builder builder=new Location_Builder().with_City("Vcp").with_Region("Cba").with_Country("Arg");
            Location location=builder.create();

            Assert.assertThat(location.getCity(), Is.is("Vcp"));
            Assert.assertThat(location.getRegion(), Is.is("Cba"));
            Assert.assertThat(location.getCountry(), Is.is("Arg"));

    }

    /*
    @Test
    public void test_WriteLocationOnDB() throws SQLException {
        Location_Builder location_builder = new Location_Builder().with_City("Carlos Paz").with_Country("Argentina").with_Region("Punilla");
        Location location = location_builder.create();

        DAOLocation daoLocation = new DAOLocation();
        daoLocation.inserInto(location);
    }
    */
}
