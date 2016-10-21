package persistence;

import builder.Atmosphere_Builder;
import configuration.Singleton_Sql_Connection;
import domain.Atmosphere;
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


public class DAOAtmosphere implements DataBaseAccess_Prototype<Atmosphere,String> {

    Singleton_Sql_Connection singleton_sql_connection;
    @Override
    public void inserInto(Atmosphere persistence) throws SQLException {
        Connection connection=null;
        try {
            connection= Singleton_Sql_Connection.getInstance().getConect();
            String SQL = "INSERT INTO Atmosfera (humedad,presion,visibilidad,ambiente_asc) VALUES ("
                    +persistence.getHumidity()+","
                    +persistence.getPressure()+","
                    +persistence.getVisibility()+","
                    +persistence.getRising()+")";
            Statement stmt = connection.createStatement();
            int count = stmt.executeUpdate(SQL);
            System.out.println("afected rows: " + count);
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
    public void deleteFrom(Atmosphere persistence) throws SQLException {

    }

    @Override
    public Atmosphere selectFromWhere(String date) throws SQLException {//Search a Atmosphere by date
        float humidity =0,pressure=0,visibility=0;
        int rising=0;
        Connection connection=null;
        Atmosphere atmosphere=null;
        try {
            connection=singleton_sql_connection.getInstance().getConect();
            String SQL = "select humedad,presion,visibilidad,ambiente_asc " +
                    "from Atmosfera A join Pronostico P on A.Id_Atmosfera=P.id_Atmosfera " +
                    "where P.fecha='"+date+"'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);


            while (rs.next()) {
                humidity =rs.getFloat("humedad");
                pressure =rs.getFloat("presion");
                visibility =rs.getFloat("visibilidad");
                rising =rs.getInt("ambiente_asc");

               /* System.out.println(rs.getFloat("humedad"));
                System.out.println(rs.getFloat("presion"));
                System.out.println(rs.getFloat("visibilidad"));
                System.out.println(rs.getInt("ambiente_asc"));*/
            }

            Atmosphere_Builder builder = new Atmosphere_Builder().with_Humidity(humidity).with_pressure(pressure).with_visibility(visibility).with_rising(rising);
            atmosphere=builder.create();
            rs.close();
            stmt.close();
            return atmosphere;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }

       return atmosphere;
    }

    @Override
    public Atmosphere select(Atmosphere pesistence) throws SQLException {
        return null;
    }

    @Override
    public List<Atmosphere> selectAllFrom() throws SQLException {
        return null;
    }

    @Override
    public List<Atmosphere> selectAllFromWhere(String id) throws SQLException {
        return null;
    }
}
