package edu.ucalgary.oop;
import java.sql.*;

/**
 * @author Shanzi Ye <a href="mailto:Shanzi.Ye@ucalgary.ca">Shanzi.Ye@ucalgary.ca</a>
 * @author Connor HUNSZINGER <a href="mailto:Connor.HUNSZINGER@ucalgary.ca">Connor.HUNSZINGER@ucalgary.ca</a>
 * @author Katarina Markic <a href="mailto:Katarina.Markic@ucalgary.ca">Katarina.Markic@ucalgary.ca</a>
 * @author Nhi Nguyen <a href="mailto:Nicole.Nguyen@ucalgary.ca">Nicole.Nguyen@ucalgary.ca</a>
 * @version 1.3
 * @since 1.0
 * interface is used to create a connection to the database using a default
 * method openDatabase()
 */

public interface DatabaseOp {
    static String URL = "jdbc:mysql://localhost/EWR";
    static String userName = "root";
    static String passWord = "root";


    public default Connection openDatabase(){
        try{
            Connection treatmentDB = DriverManager.getConnection(URL, userName, passWord);
            return treatmentDB;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to connect to database");
            return null;
        }

    }

}
