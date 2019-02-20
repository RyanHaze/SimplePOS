package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dbconnect {

    public static String url;


    private static String sql = "CREATE TABLE IF NOT EXISTS employees (\n"
            + " employee_id integer PRIMARY KEY, \n"
            + " employee_fname text NOT NULL, \n"
            + " employee_lname text NOT NULL, \n"
            + " employee_login integer NOT NULL\n"
            + ");";



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







}
