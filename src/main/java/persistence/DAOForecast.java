package persistence;

import builder.CurrentDay_Builder;
import configuration.Singleton_Sql_Connection;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Nico on 14/10/2016.
 */

@Repository(value ="daoFore")
public class DAOForecast implements DataBaseAccess_Prototype<Forecast,String>{

    @Autowired
    Singleton_Sql_Connection singleton_sql_connection;

    @Override
    public void inserInto(Forecast persistence) throws SQLException {
        Connection connection=null;
        java.sql.Date dateSQL = new java.sql.Date(persistence.getDay().getDate().getTime().getTime());
        try {
            connection= Singleton_Sql_Connection.getInstance().getConect();
            String SQL = "INSERT INTO Pronostico VALUES ('"+dateSQL
                    +"','"+persistence.getLocation().getCity()+"','"+persistence.getLocation().getCountry()+"','"+persistence.getLocation().getRegion()
                    +"',1,1)";// harcode for Atmosphere and Wind Id
            Statement stmt = connection.createStatement();
            int count = stmt.executeUpdate(SQL);
            System.out.println("Forecast Added to DB!, afected rows: " + count);
            stmt.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void deleteFrom(Forecast persistence) throws SQLException {

    }

    @Override
    public Forecast selectFromWhere(String cityKey) throws SQLException {//only search by City for the moment
        Connection connection=null;

        Atmosphere atmosphere;
        CurrentDay currentDay;
        Location location;
        Wind wind;
        List<ExtendedForecast> extendedForecastList;
        Forecast forecast=null;

        String country="",region="",city="";
        Date date=null;
        String dateString="";

        try {
            connection=singleton_sql_connection.getConect();
            String SQL = "select * from Pronostico where nombre_Ciudad like '"+cityKey+"'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);


            while (rs.next()) {
                date =rs.getDate("fecha");
                dateString=rs.getString("fecha");
                city =rs.getString("nombre_Ciudad");
                region =rs.getString("nombre_Region");
                country=rs.getString("nombre_Pais");



            }

            Calendar cal  = Calendar.getInstance();
            cal.setTime(date);

            currentDay=new DAOCurrentDay().selectFromWhere(dateString);
            location=new  DAOLocation().select(new Location(city,region,country));
            atmosphere=new DAOAtmosphere().selectFromWhere(dateString);
            wind=new DAOWind().selectFromWhere(dateString);
            extendedForecastList=new DAOExtendedForecast().selectAllFromWhere(dateString);

            forecast=new Forecast(location,currentDay,atmosphere,wind,extendedForecastList);

            return forecast;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
        return forecast;
    }

    @Override
    public Forecast select(Forecast pesistence) throws SQLException {
        return null;
    }

    @Override
    public List<Forecast> selectAllFrom() throws SQLException {
        return null;
    }

    @Override
    public List<Forecast> selectAllFromWhere(String id) throws SQLException {
        return null;
    }
}
