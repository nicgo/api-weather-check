import builder.Wind_Builder;
import domain.Wind;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Nico on 11/10/2016.
 */
public class Wind_Test {


    @Test
    public void test_Wind_dataCreationByDefault(){
        Wind_Builder wind_builder= new Wind_Builder().create_Default();
        Wind wind=wind_builder.create();

        Assert.assertEquals(50,(long)wind.getDirection());
        Assert.assertEquals(100,(long)wind.getVelocity());
    }
}
