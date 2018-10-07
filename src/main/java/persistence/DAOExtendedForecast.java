package persistence;

import builder.ExtendedForecast_Builder;
import configuration.Singleton_Sql_Connection;
import domain.Day;
import domain.ExtendedForecast;
import domain.Forecast;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Nico on 13/10/2016.
 */


public class DAOExtendedForecast implements DataBaseTransactionAccess_Prototype<ExtendedForecast,String,Forecast>{

    Singleton_Sql_Connection singleton_sql_connection;

    @Override
    public void inserInto(ExtendedForecast persistence, Forecast reference) throws SQLException {
        Connection connection=Singleton_Sql_Connection.getInstance().getConnect();
        try {
            java.sql.Date dateSQL_ext = new java.sql.Date(persistence.getDate().getTime().getTime());
            java.sql.Date dateSQL_reference = new java.sql.Date(reference.getDay().getDate().getTime().getTime());
            String SQL = "INSERT INTO Pronostico_Extendido VALUES ('"+dateSQL_reference
                    +"','"+reference.getLocation().getCity()+"','"+reference.getLocation().getCountry()+"','"+reference.getLocation().getRegion()
                    +"',"+persistence.getLow()
                    +","+persistence.getHigh()
                    +","+persistence.getIdDescription()
                    +",'"+persistence.getDay().toString()
                    +"','"+dateSQL_ext+"')";
            Statement stmt = connection.createStatement();
            System.out.println(SQL);

            int count = stmt.executeUpdate(SQL);
            System.out.println("Extended Forecast Added to DB!, afected rows: " + count);
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
    public void deleteFrom(ExtendedForecast persistence) throws SQLException {

    }

    @Override
    public ExtendedForecast selectFromWhere(String date) throws SQLException {
return null;
    }

    @Override
    public ExtendedForecast select(ExtendedForecast pesistence) throws SQLException {
        return null;
    }

    @Override
    public List<ExtendedForecast> selectAllFrom() throws SQLException {
        return null;
    }

    @Override
    public List<ExtendedForecast> selectAllFromWhere(String dateKey) throws SQLException {
        List<ExtendedForecast>extended_List=new ArrayList<>();
        Connection connection=null;
        try {
            connection=singleton_sql_connection.getInstance().getConnect();
            String SQL = "select P.min,P.max,D.descripcion,P.dia,P.fecha from Pronostico_Extendido P\n" +
                    "join Descripcion D on P.descripcion=D.id_descripcion\n" +
                    "where fecha='"+dateKey+"'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);


            while (rs.next()) {
                ExtendedForecast extendedForecast;
                float low =rs.getFloat("min");
                float high =rs.getFloat("max");
                String description =rs.getString("descripcion");
                String day =rs.getString("dia");
                Date date=rs.getDate("fecha");

                Day thisDay=null;
                for (Day days:Day.values()) {
                    if (day.equals(days.name())){
                        thisDay=days.fromValue(day);
                    }
                }

                Calendar cal  = Calendar.getInstance();
                cal.setTime(date);

                ExtendedForecast_Builder builder=new ExtendedForecast_Builder().with_date(cal).with_day(thisDay).with_low(low).with_high(high).with_description(description);
                extendedForecast=builder.create();
                extended_List.add(extendedForecast);

            }



        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }

        return extended_List;
    }
}
