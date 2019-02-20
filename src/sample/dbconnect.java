package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnect {

    public static String url;
    //establish a connection to the db Need to create a new db if not found
    public static void connect()
    {
        Connection connect = null;
        try
        {
            //db parameters
            url = "jdbc:sqlite:C:/sqlite/db/pos";
            //create a connection to the database
            connect = DriverManager.getConnection(url);

            System.out.println("Connected to pos.db successfully");
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }finally
        {
            try
            {
                if (connect != null)
                {
                    connect.close();
                }

            }catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    //add a new table to the database
    public static void createNewTable()
    {


    }





}
