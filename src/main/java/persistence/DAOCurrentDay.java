package persistence;

import builder.CurrentDay_Builder;
import configuration.Singleton_Sql_Connection;
import domain.CurrentDay;

import java.sql.*;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Nico on 13/10/2016.
 */


public class DAOCurrentDay implements DataBaseAccess_Prototype<CurrentDay,String> {

    Singleton_Sql_Connection singleton_sql_connection;

    @Override
    public void inserInto(CurrentDay persistence) throws SQLException {
        Connection connection=null;
        java.sql.Date dateSQL = new java.sql.Date(persistence.getDate().getTime().getTime());//date is converted to an SQL date
        try {
            connection= Singleton_Sql_Connection.getInstance().getConnect();
            String SQL = "INSERT INTO DiaActual (fecha,temp,descripcion) VALUES (?,"+persistence.getTemp()+","+persistence.getIdDescription()+")";
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setDate(1,dateSQL);
            int count = stmt.executeUpdate();

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
    public void deleteFrom(CurrentDay persistence) throws SQLException {

    }

    @Override
    public CurrentDay selectFromWhere(String dateKey) throws SQLException {
        Date date=null;
        String description="";
        float temperature=0;
        CurrentDay currentDay=null;
        Connection connection=null;
        try {
            connection=singleton_sql_connection.getInstance().getConnect();
            String SQL = "select fecha,temp,descripcion " +
                    "from DiaActual where fecha='"+dateKey+"'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);


            while (rs.next()) {
                date =rs.getDate("fecha");
                temperature =rs.getFloat("temp");
                description =rs.getString("descripcion");

               /* System.out.println(rs.getDate("fecha"));
                System.out.println(rs.getFloat("temp"));
                System.out.println(rs.getFloat("descripcion"));*/


            }

            Calendar cal  = Calendar.getInstance();
            cal.setTime(date);

            CurrentDay_Builder builder = new CurrentDay_Builder().with_date(cal).whit_temp(temperature).whit_description(description);
            currentDay =builder.create();
            rs.close();
            stmt.close();
            return currentDay;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }

        return currentDay;

    }

    @Override
    public CurrentDay select(CurrentDay pesistence) throws SQLException {
        return null;
    }

    @Override
    public List<CurrentDay> selectAllFrom() throws SQLException {
        return null;
    }

    @Override
    public List<CurrentDay> selectAllFromWhere(String id) throws SQLException {
        return null;
    }
}
