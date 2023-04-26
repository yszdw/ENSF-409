package edu.ucalgary.oop;


/**
* @author Shanzi Ye <a href="mailto:Shanzi.Ye@ucalgary.ca">Shanzi.Ye@ucalgary.ca</a>
* @author Connor HUNSZINGER <a href="mailto:Connor.HUNSZINGER@ucalgary.ca">Connor.HUNSZINGER@ucalgary.ca</a>
* @author Katarina Markic <a href="mailto:Katarina.Markic@ucalgary.ca">Katarina.Markic@ucalgary.ca</a>
* @author Nicole Nguyen <a href="mailto:Nicole.Nguyen@ucalgary.ca">Nicole.Nguyen@ucalgary.ca</a>
* @version 1.2
* @since 1.0
* add description here!!!!!
*/
public class Task {
    private int ID;
    private String DESCRIPTION;
    private int DURATION;
    private int MAXWINDOW;

    
    /**
    * Constructs a Task object with the specified ID, description, duration, and maximum window.
    * @param id the ID of the task.
    * @param description the description of the task.
    * @param duration the duration of the task, in minutes.
    * @param maxWindow the maximum window of the task, in hours.
    */
    public Task(int id, String description, int duration, int maxWindow) {
        this.ID = id;
        this.DESCRIPTION = description;
        this.DURATION = duration;
        this.MAXWINDOW = maxWindow;
    }
    
    /**
    * Returns whether the task is flexible (i.e., has a maximum window greater than one hour).
    * @return whether the task is flexible.
    */
    public boolean isFlexible() {
        return MAXWINDOW > 1;
    }
    
    /**
    * Returns the ID of the task.
    * @return ID the ID of the task.
    */
    public int getID() {
        return ID;
    }
    
    /**
    * Returns the description of the task.
    * @return DESCRIPTION the description of the task.
    */
    public String getDescription() {
        return DESCRIPTION;
    }
    
    /**
    * Returns the duration of the task, in minutes.
    * @return DURATION the duration of the task, in minutes.
    */
    public int getDuration() {
        return DURATION;
    }

    
    /**
    * Returns the maximum window of the task, in hours.
    * @return MAXWINDOW the maximum window of the task, in hours.
    */
    public int getMaxWindow() {
        return MAXWINDOW;
    }
}
