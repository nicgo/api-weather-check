import builder.Atmosphere_Builder;
import domain.Atmosphere;
import org.junit.Assert;
import org.junit.Test;
import persistence.DAOAtmosphere;

import java.sql.SQLException;

/**
 * Created by Nico on 7/10/2016.
 */
public class Atmosphere_Test {

    @Test
    public void test_Atmosphere_dataCreation(){
        Atmosphere_Builder atmosphere_builder=new Atmosphere_Builder().create_Default();
        Atmosphere atmosphere=atmosphere_builder.create();

        Assert.assertEquals(40,(long)atmosphere.getHumidity());
        Assert.assertEquals(70,(long)atmosphere.getPressure());
        Assert.assertEquals(95,(long)atmosphere.getVisibility());
        Assert.assertEquals(2,atmosphere.getRising());
    }


    /*@Test
    public void test_WriteAtmosphereOnDB() throws SQLException {
        Atmosphere_Builder atmosphere_builder=new Atmosphere_Builder().create_Default();
        Atmosphere atmosphere=atmosphere_builder.create();

    }*/
}
