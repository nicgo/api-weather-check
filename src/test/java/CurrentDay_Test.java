import builder.CurrentDay_Builder;
import domain.CurrentDay;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import persistence.DAOCurrentDay;

import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by Nico on 11/10/2016.
 */
public class CurrentDay_Test {

    @Test
    public void test_CurrentDay_dataCreation(){
        CurrentDay_Builder currentDay_builder=new CurrentDay_Builder().create_Default();
        CurrentDay currentDay=currentDay_builder.create();


        Assert.assertEquals(Calendar.getInstance().get(Calendar.DAY_OF_MONTH),currentDay.getDate().get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(27,(long)currentDay.getTemp());
        Assert.assertThat(currentDay.getDescription(), Is.is("Sunny"));
    }

    /*@Test
    public void test_WriteCurrentDayOnDB() throws SQLException {
        CurrentDay_Builder currentDay_builder = new CurrentDay_Builder().create_Default();
        CurrentDay currentDay= currentDay_builder.create();

        DAOCurrentDay daoCurrentDay = new DAOCurrentDay();
        daoCurrentDay.inserInto(currentDay);

        System.out.println(daoCurrentDay.selectFromWhere("19/10/2016").toString());
    }*/
}
