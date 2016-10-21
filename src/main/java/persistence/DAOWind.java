package persistence;

import builder.Wind_Builder;
import configuration.Singleton_Sql_Connection;
import domain.Wind;
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


public class DAOWind implements DataBaseAccess_Prototype<Wind,String> {

    Singleton_Sql_Connection singleton_sql_connection;

    @Override
    public void inserInto(Wind persistence) throws SQLException {
        Connection connection=null;
        try {
            connection= Singleton_Sql_Connection.getInstance().getConect();
            String SQL = "INSERT INTO Viento (id_viento,direccion,velocidad) VALUES (1,"
                    +persistence.getDirection()+","
                    +persistence.getVelocity()+")";
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
    public void deleteFrom(Wind persistence) throws SQLException {

    }

    @Override
    public Wind selectFromWhere(String date) throws SQLException {
        float direction =0,velocity=0;
        Connection connection=null;
        Wind wind=null;
        try {
            connection=singleton_sql_connection.getInstance().getConect();
            String SQL = "select direccion, velocidad\n" +
                    "from Viento V join Pronostico P on V.Id_Viento=P.id_Viento\n" +
                    "where P.fecha='"+date+"'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);


            while (rs.next()) {
                velocity =rs.getFloat("velocidad");
                direction =rs.getFloat("direccion");

            }

            Wind_Builder builder = new Wind_Builder().with_direction(direction).with_velocity(velocity);
            wind=builder.create();
            rs.close();
            stmt.close();
            return wind;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }

        return wind;
    }

    @Override
    public Wind select(Wind pesistence) throws SQLException {
       return null;
    }

    @Override
    public List<Wind> selectAllFrom() throws SQLException {
        return null;
    }

    @Override
    public List<Wind> selectAllFromWhere(String id) throws SQLException {
        return null;
    }
}
