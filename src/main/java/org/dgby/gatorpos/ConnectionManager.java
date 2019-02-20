package org.dgby.gatorpos;

import org.dgby.gatorpos.models.Employee;

import java.sql.*;

public class ConnectionManager {
    private static String url = "jdbc:sqlite:C:/sqlite/db/posdb.db";
    private static Connection con = null;

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(url);
            System.out.println("connection worked");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database.");
            con = null;
        }


        return con;
    }

    //Add an entry to a table
    public static void insertEmployee(int id, int login, String fname, String lname)
    {
        String sql = "INSERT INTO Employee(employee_id, employee_fname, employe_lname, employee_login) VALUES (?,?,?,?)";

        try(Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, id);
            pstmt.setString(2,fname);
            pstmt.setString(3, lname);
            pstmt.setInt(4, login);
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    //delete en employee entry table
    public static void deleteEmployee(int id)
    {
        String sql = "DELETE FROM Employee WHERE employee_id = ?";
        try(Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public static String[] populateTable()
    {
        String sql = "SELECT employee_id, employee_fname, employe_lname, employee_login FROM Employee";
        String[] returnStrings;
        returnStrings = new String[200];
        int i = 0;
        try(Connection con = getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next())
            {
                int eid = rs.getInt("employee_id");
                returnStrings[i] = Integer.toString(eid);
                returnStrings[i + 1] = rs.getString("employee_fname");
                returnStrings[i + 2] = rs.getString("employe_lname");
                int login = rs.getInt("employee_login");
                returnStrings[i + 3] = Integer.toString(login);

                //groups of 4
                i = i + 4;



            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return returnStrings;
    }

}