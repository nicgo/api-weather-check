package domain;

//import com.sun.org.apache.xpath.internal.operations.String;

import configuration.Singleton_Sql_Connection;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Nico on 26/9/2016.
 */
public class Main {


    public static void main(String []args) throws SQLException {
        System.out.println("..::Welcome to App Forecast::..");
        int option;//


        //New connection to DB
        Singleton_Sql_Connection singletonSql_connection = Singleton_Sql_Connection.getInstance();
        Connection connection= singletonSql_connection.getConect();
        System.out.println();


        System.out.println("Enter an option:Press\n1-To write a forecast\n2-To read DB");
        option=In.readInt();
        while (option!=1&&option!=2){
            System.out.println("Wrong Option!!\\nPress 1-Yes 2-No");
            option=In.readInt();
        }
        switch (option) {
            case 1 :
                    {
                        int ext_Forec_Counter=1;//extended forecast counter
                        GregorianCalendar calendar = new GregorianCalendar();//Current Date
                        Date date,extDate = new Date();
                        Day dayExt = null;//extended forecast day
                        List<ExtendedForecast> extendedForecastList=new ArrayList<ExtendedForecast>();

                        //SET DATA ABOUT LOCATION
                        System.out.print("LOCATION\n");
                        System.out.print("County?: ");
                        String country=In.readString();
                        //writes new contry on DB
                        try {
                            String SQL = "INSERT INTO Pais (nombre_Pais) VALUES ('"+country+"')";
                            Statement stmt = connection.createStatement();
                            int count = stmt.executeUpdate(SQL);
                            System.out.println("afected rows: " + count);
                            stmt.close();
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }

                        System.out.print("Region: ");
                        String region=In.readString();
                        //writes new region on DB
                        try {
                            String SQL = "INSERT INTO Region (nombre_Pais,nombre_Region) VALUES ('"+country+"','"+region+"')";
                            Statement stmt = connection.createStatement();
                            int count = stmt.executeUpdate(SQL);
                            System.out.println("afected rows: " + count);
                            stmt.close();
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }

                        System.out.print("City?: ");
                        String city=In.readString();
                        //writes new city on DB
                        try {
                            String SQL = "INSERT INTO Ciudad (nombre_Pais,nombre_Region,nombre_Ciudad) VALUES ('"+country+"','"+region+"','"+city+"')";
                            Statement stmt = connection.createStatement();
                            int count = stmt.executeUpdate(SQL);
                            System.out.println("afected rows: " + count);
                            stmt.close();
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }

                        //Location
                        Location location = new Location(city,region,country);

                        date = calendar.getTime();//instantiates current date
                        System.out.print("\nTODAY IS: "+date+"\n");
                        java.sql.Date dateSQL = new java.sql.Date(date.getTime());//date is converted to an SQL date


                        //SET DATA ABOUT CURRENT DAY
                        System.out.print("Temperature in °C ?: ");
                        float temp=In.readFloat();
                        System.out.print("Choose description's Day: ");
                        System.out.print("\n1- Sunny\n2- Hazy\n3- Partly cloudy\n4- Cloudy\n5- Rainy\n6- Stormy\n7- Snowy\n8- Windy");
                        int value=In.readInt();
                        String descrip="";
                        switch (value) {
                            case 1:
                                descrip = "Sunny";
                                break;
                            case 2:
                                descrip = "Hazy";
                                break;
                            case 3:
                                descrip = "Partly Cloudy";
                                break;
                            case 4:
                                descrip = "Cloudy";
                                break;
                            case 5:
                                descrip = "Rainy";
                                break;
                            case 6:
                                descrip = "Stormy";
                                break;
                            case 7:
                                descrip = "Snowy";
                                break;
                            case 8:
                                descrip = "Windy";
                                break;
                        }
                        //Current Day
                        CurrentDay currentDay = new CurrentDay(calendar,temp,descrip);
                        //writes Current Day on DB
                        try {
                            String SQL = "INSERT INTO DiaActual (fecha,temp,descripcion) VALUES (?,"+temp+","+value+")";
                            PreparedStatement stmt = connection.prepareStatement(SQL);
                            stmt.setDate(1,dateSQL);
                            int count = stmt.executeUpdate();

                            System.out.println("afected rows: " + count);
                            stmt.close();
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }


                        //SET DATA ABOUT ATMOSPHERE
                        System.out.print("\nATMOSPHERE\n");
                        System.out.print("Humidity?: ");
                        float humidity=In.readFloat();
                        System.out.print("Pressure: ");
                        float pressure=In.readFloat();
                        System.out.print("Visibility: ");
                        float visibility=In.readFloat();
                        System.out.print("Rising: ");
                        int rising=In.readInt();

                        //Atmosphere
                        Atmosphere atmosf = new Atmosphere(humidity,pressure,visibility,rising);
                        //writes new Atmosphere on DB
                        try {
                            String SQL = "INSERT INTO Atmosfera (humedad,presion,visibilidad,ambiente_asc) VALUES ("+humidity+","+pressure+","+visibility+","+rising+")";
                            Statement stmt = connection.createStatement();
                            int count = stmt.executeUpdate(SQL);
                            System.out.println("afected rows: " + count);
                            stmt.close();
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }


                        //SET DATA ABOUT WIND
                        System.out.print("\nWIND\n");
                        System.out.print("Direction: ");
                        float direction=In.readFloat();
                        System.out.print("Velocity: ");
                        float velocity=In.readFloat();

                        //Wind
                        Wind wind = new Wind(direction,velocity);
                        //writes new wind on DB
                        try {
                            String SQL = "INSERT INTO Viento (direccion,velocidad) VALUES ("+direction+","+velocity+")";
                            Statement stmt = connection.createStatement();
                            int count = stmt.executeUpdate(SQL);
                            System.out.println("afected rows: " + count);
                            stmt.close();
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }


                        //cycle to load the differents Extended Forecast
                        int load_another_option=0;

                        do {
                            calendar.add(Calendar.DAY_OF_MONTH, 1);//increase in a day, current day
                            extDate = calendar.getTime();//extended forecast day calculation
                            switch (calendar.get(Calendar.DAY_OF_WEEK)){//day calculation to set Enum Day
                                case 1:
                                    dayExt = Day.Sun;
                                    break;
                                case 2:
                                    dayExt = Day.Mon;
                                    break;
                                case 3:
                                    dayExt = Day.Tue;
                                    break;
                                case 4:
                                    dayExt = Day.Wed;
                                    break;
                                case 5:
                                    dayExt = Day.Thu;
                                    break;
                                case 6:
                                    dayExt = Day.Fri;
                                    break;
                                case 7:
                                    dayExt = Day.Sun;
                                    break;

                            }

                            System.out.print("\nEXTENDED FORECAST "+ext_Forec_Counter+"\n");
                            System.out.println("Forecast for day: "+extDate);
                            System.out.print("Low?: ");
                            float low=In.readFloat();
                            System.out.print("High?: ");
                            float high=In.readFloat();
                            System.out.print("Choose description's Day: ");
                            System.out.print("\n1- Sunny\n2- Hazy\n3- Partly cloudy\n4- Cloudy\n5- Rainy\n6- Stormy\n7- Snowy\n8- Windy");
                            int value2=In.readInt();
                            String descrip2="";
                            switch (value2) {
                                case 1:
                                    descrip2 = "Sunny";
                                    break;
                                case 2:
                                    descrip2 = "Hazy";
                                    break;
                                case 3:
                                    descrip2 = "Partly Cloudy";
                                    break;
                                case 4:
                                    descrip2 = "Cloudy";
                                    break;
                                case 5:
                                    descrip2 = "Rainy";
                                    break;
                                case 6:
                                    descrip2 = "Stormy";
                                    break;
                                case 7:
                                    descrip2 = "Snowy";
                                    break;
                                case 8:
                                    descrip2 = "Windy";
                                    break;
                            }
                            extendedForecastList.add(new ExtendedForecast(calendar, dayExt,low,high,descrip2));

                            ext_Forec_Counter++;
                            System.out.println("Do you want to load another forecast? 1-Yes 2-No: ");
                            load_another_option=In.readInt();
                        }
                        while (load_another_option==1);


                        Forecast forecast = new Forecast(location, currentDay,atmosf, wind,extendedForecastList);
                        System.out.println(forecast.toString());

                        //writes new Forecast on DB
                        try {
                            String SQL = "INSERT INTO Pronostico VALUES (?"
                                    +",'"+location.getCity()+"','"+location.getCountry()+"','"+location.getRegion()
                                    +"',1,1)";
                            PreparedStatement stmt = connection.prepareStatement(SQL);
                            stmt.setDate(1,dateSQL);
                            int count = stmt.executeUpdate();
                            System.out.println("Forecast Added to DB!, afected rows: " + count);
                            stmt.close();
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }



                        for (ExtendedForecast ext_fore: extendedForecastList
                                 ) {
                            try {
                                java.sql.Date dateSQL_ext = new java.sql.Date(ext_fore.getDate().getTime().getTime());//cast for Extended Forecast date
                                java.sql.Date dateSQL_forecast = new java.sql.Date(forecast.getDay().getDate().getTime().getTime());// cast for Forecast date
                                String SQL = "INSERT INTO Pronostico_Extendido VALUES ('"+dateSQL_forecast+"','"
                                        +location.getCity()+"','"+location.getCountry()+"','"+location.getRegion()
                                        +"',"+ext_fore.getLow()
                                        +","+ext_fore.getHigh()
                                        +","+ext_fore.getIdDescription()
                                        +",'"+ext_fore.getDay().toString()
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

                        }

                    }
            case 2:

                System.out.println("\nEnter an option: \n1-Read DB \n2-Exit");
                int opt=In.readInt();
                do {
                    switch (opt){
                        case 1: {
                            try {
                                String SQL = "Select * From Pronostico";
                                Statement stmt = connection.createStatement();
                                ResultSet rs = stmt.executeQuery(SQL);


                                while (rs.next()) {
                                    System.out.println(rs.getString(1) + ", "
                                            + rs.getString(2)+ ", "
                                            +rs.getString(3) + ", "
                                            +rs.getString(4) + ", "
                                            +rs.getString(5) + ", "
                                            +rs.getString(6));

                                }

                                rs.close();
                                stmt.close();
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;}
                        case 2: break;



                    }
                    System.out.println("\nEnter an option: \n1-Read DB \n2-Exit");
                    opt=In.readInt();
                }
                while (opt!=2);
        }
    }
}
