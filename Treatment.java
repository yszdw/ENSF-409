package edu.ucalgary.oop;

/**
* @author Shanzi Ye <a href="mailto:Shanzi.Ye@ucalgary.ca">Shanzi.Ye@ucalgary.ca</a>
* @author Connor HUNSZINGER <a href="mailto:Connor.HUNSZINGER@ucalgary.ca">Connor.HUNSZINGER@ucalgary.ca</a>
* @author Katarina Markic <a href="mailto:Katarina.Markic@ucalgary.ca">Katarina.Markic@ucalgary.ca</a>
* @author Nicole Nguyen <a href="mailto:Nicole.Nguyen@ucalgary.ca">Nicole.Nguyen@ucalgary.ca</a>
* @version 1.1
* @since 1.0
* The Treatment class consists of constructors for treatments, with properties such as its ID, the ID of the animal receiving the treatment,
* the ID of the task associated with the treatment, and the start hour of the treatment.
* The class also provides methods to access and retrieve these properties.
*/
public class Treatment{
    private int treatmentId;
    private int animalId;
    private int taskId;
    private int startHour;

    /**
    * Constructs a Treatment object with the specified treatment ID, animal ID, task ID, and start hour.
    * @param treatmentID the ID of the treatment.
    * @param animalID the ID of the animal receiving the treatment.
    * @param taskID the ID of the task associated with the treatment.
    * @param startHour the start hour of the treatment.
    */
    public Treatment(int treatmentID, int animalID, int taskID, int startHour) {
        this.treatmentId = treatmentID;
        this.animalId = animalID;
        this.taskId = taskID;
        this.startHour = startHour;
    }

    
    /**
    * Returns the ID of the treatment.
    * @return the ID of the treatment.
    */
    public int getTreatmentID() {
        return treatmentId;
    }

    
    /**
    * Returns the ID of the animal receiving the treatment.
    * @return the ID of the animal receiving the treatment.
    */
    public int getAnimalID() {
        return animalId;
    }

    /**
    * Returns the ID of the task associated with the treatment.
    * @return the ID of the task associated with the treatment.
    */
    public int getTaskID() {
        return taskId;
    }

    
    /**
    * Returns the start hour of the treatment.
    * @return the start hour of the treatment.
    */
    public int getStartHour() {
        return startHour;
    }
    
    /**
    * Sets the start hour of the treatment to the specified value.
    * @param startHour the new start hour of the treatment.
    */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }
}
