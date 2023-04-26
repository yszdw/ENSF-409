package edu.ucalgary.oop;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Shanzi Ye <a href="mailto:Shanzi.Ye@ucalgary.ca">Shanzi.Ye@ucalgary.ca</a>
 * @author Connor HUNSZINGER <a href="mailto:Connor.HUNSZINGER@ucalgary.ca">Connor.HUNSZINGER@ucalgary.ca</a>
 * @author Katarina Markic <a href="mailto:Katarina.Markic@ucalgary.ca">Katarina.Markic@ucalgary.ca</a>
 * @author Nicole Nguyen <a href="mailto:Nicole.Nguyen@ucalgary.ca">Nicole.Nguyen@ucalgary.ca</a>
 * @version 2.0
 * @since 1.0
 * This class establishes a connection to the EWR database and provides methods to retrieve data from it.
 */

public class ConnectDatabase implements DatabaseOp{
    private Connection treatmentDB = openDatabase();
    //private String URL = "jdbc:mysql://localhost/EWR";
    //private String userName = "root";
    //private String passWord = "root";
    
    /**
     * Returns the URL of the database.
     * @return A String representing the URL of the database.
     */
    /*private String getURL(){
        return this.URL;
    }*/


    /**
     * Returns the username used to connect to the database.
     * @return A String representing the username used to connect to the database.
     */
    /*private String getUsername(){
        return this.userName;
    }*/

    /**
     * Returns the password used to connect to the database.
     * @return A String representing the password used to connect to the database.
     */
    /*private String getPassword(){
        return this.passWord;
    }*/

    /**
     * Establishes a connection to the EWR database using the URL, username, and password specified in the instance variables.
     * If the connection cannot be established, an error message is printed to the console.
     */
    /*public void openDatabase(){
        try{
            treatmentDB = DriverManager.getConnection(this.getURL(), this.getUsername(), this.getPassword());
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to connect to database");
        }
        System.out.println("Connected to database");

    }*/


    /**
     * Retrieves all the animals from the ANIMALS table in the EWR database and stores them in a List of Animal objects.
     * Each Animal object contains the animal's ID, nickname, and species.
     * If an error occurs during the retrieval process, an error message is printed to the console.
     * @return the lists of animals.
     */
    public ArrayList<Animal> retrieveAnimal(){
        ArrayList<Animal> animals = new ArrayList<>();

        try {
            Statement statement = treatmentDB.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ANIMALS");


            while (resultSet.next()) {
                int animalID = resultSet.getInt("AnimalID");
                String animalNickname = resultSet.getString("AnimalNickname");
                String animalSpecies = resultSet.getString("AnimalSpecies");
                //System.out.println(animalSpecies);
                animals.add(new Animal(animalID, animalNickname, animalSpecies));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animals;
    }



    /**
     * Retrieves all the tasks from the TASKS table in the EWR database and stores them in a List of Task objects.
     * Each Task object contains the task's ID, description, duration, and maximum window.
     * If an error occurs during the retrieval process, an error message is printed to the console.
     * @return the lists of tasks.
     */
    public ArrayList<Task> retrieveTask() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Statement statement = treatmentDB.createStatement();
            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM TASKS");

            while (resultSet1.next()) {
                int taskID = resultSet1.getInt("TaskID");
                String description = resultSet1.getString("Description");
                int duration = resultSet1.getInt("Duration");
                int maxWindow = resultSet1.getInt("MaxWindow");
                tasks.add(new Task(taskID, description, duration, maxWindow));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tasks;
    }



    /**
     * Retrieves all the treatments from the TREATMENTS table in the EWR database and stores them in a List of Treatment objects.
     * Each Treatment object contains the treatment's ID, the ID of the animal it is associated with, the ID of the task it represents, and the start hour of the treatment.
     * If an error occurs during the retrieval process, an error message is printed to the console.
     * @return the lists of treatments.
     */
    public ArrayList<Treatment> retrieveTreatment() {
        ArrayList<Treatment> treatments = new ArrayList<>();

        try {
            Statement statement = treatmentDB.createStatement();
            ResultSet resultSet2 = statement.executeQuery("SELECT * FROM TREATMENTS");

            while (resultSet2.next()) {
                int treatmentID = resultSet2.getInt("TreatmentID");
                int animalID = resultSet2.getInt("AnimalID");
                int taskID = resultSet2.getInt("TaskID");
                int startHour = resultSet2.getInt("StartHour");
                treatments.add(new Treatment(treatmentID, animalID, taskID, startHour));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return treatments;
    }


    /**
     * Closes the connection to the EWR database.
     * If an error occurs while attempting to close the connection, an error message is printed to the console.
     */
    public void closeDataBase() {
        try {
            treatmentDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDataBase(int newStartHour, int treatmentID){
        try {
            Statement statement = treatmentDB.createStatement();
            String query = String.format("UPDATE TREATMENTS SET StartHour = %d WHERE TreatmentID = %d;",
                                         newStartHour, treatmentID);
            statement.execute(query);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        ConnectDatabase db = new ConnectDatabase();
        db.openDatabase();
        db.updateDataBase(2, 2);
    }
}
