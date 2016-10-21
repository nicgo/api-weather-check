package persistence;

import builder.Location_Builder;
import configuration.Singleton_Sql_Connection;
import domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Nico on 13/10/2016.
 */


public class DAOLocation implements DataBaseAccess_Prototype<Location,String> {


    Singleton_Sql_Connection singleton_sql_connection;


    @Override
    public void inserInto(Location persistence) throws SQLException {
        Connection connection=null;
        try{
            connection = singleton_sql_connection.getInstance().getConect();
            //writes new country on DB
            try {

                String SQL = "INSERT INTO Pais (nombre_Pais) VALUES ('" + persistence.getCountry() + "')";
                Statement stmt = connection.createStatement();
                int count = stmt.executeUpdate(SQL);
                System.out.println("afected rows: " + count);
                stmt.close();
            } catch (Exception e) {
                System.out.println(e);
            }

            //writes new region on DB
            try {
                String SQL = "INSERT INTO Region (nombre_Pais,nombre_Region) VALUES ('" +persistence.getCountry()
                        + "','" + persistence.getRegion() + "')";
                Statement stmt = connection.createStatement();
                int count = stmt.executeUpdate(SQL);
                System.out.println("afected rows: " + count);
                stmt.close();
            } catch (Exception e) {
                System.out.println(e);
            }


            //writes new city on DB
            try {
                String SQL = "INSERT INTO Ciudad (nombre_Pais,nombre_Region,nombre_Ciudad) VALUES ('"
                        + persistence.getCountry() + "','"
                        + persistence.getRegion() + "','"
                        + persistence.getCity() + "')";
                Statement stmt = connection.createStatement();
                int count = stmt.executeUpdate(SQL);
                System.out.println("afected rows: " + count);
                stmt.close();
            } catch (Exception e) {
                System.out.println(e);
            }
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
    public void deleteFrom(Location persistence) throws SQLException {

    }

    @Override
    public Location selectFromWhere(String id) throws SQLException {
        return null;
    }

    @Override
    public Location select(Location pesistence) throws SQLException {
        String country="",region="",city="";
        Location location=null;
        Connection connection=null;
        try {
            connection=singleton_sql_connection.getInstance().getConect();
            String SQL = "select C.nombre_Pais, C.nombre_Region,C.nombre_Ciudad\n" +
                    "from Pais P join Region R on P.nombre_Pais=R.nombre_Pais\n" +
                    "join Ciudad C on R.nombre_Region=C.nombre_Region\n" +
                    "where C.nombre_Pais like '"+pesistence.getCountry()+"' and " +
                    "C.nombre_Region like '"+pesistence.getRegion()+"' and " +
                    "C.nombre_Ciudad like '"+pesistence.getCity()+"'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);


            while (rs.next()) {
                country =rs.getString("nombre_Pais");
                region =rs.getString("nombre_Region");
                city =rs.getString("nombre_Ciudad");


            }

            Location_Builder builder = new Location_Builder().with_Country(country).with_Region(region).with_City(city);
            location =builder.create();
            rs.close();
            stmt.close();
            return location;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }

        return location;


    }

    @Override
    public List<Location> selectAllFrom() throws SQLException {

        return null;
    }

    @Override
    public List<Location> selectAllFromWhere(String id) throws SQLException {
        return null;
    }


}
