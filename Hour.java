package edu.ucalgary.oop;

import java.util.*;

/**
 * @author Shanzi Ye <a href="mailto:Shanzi.Ye@ucalgary.ca">Shanzi.Ye@ucalgary.ca</a>
 * @author Connor HUNSZINGER <a href="mailto:Connor.HUNSZINGER@ucalgary.ca">Connor.HUNSZINGER@ucalgary.ca</a>
 * @author Katarina Markic <a href="mailto:Katarina.Markic@ucalgary.ca">Katarina.Markic@ucalgary.ca</a>
 * @author Nicole Nguyen <a href="mailto:Nicole.Nguyen@ucalgary.ca">Nicole.Nguyen@ucalgary.ca</a>
 * @version 2.0
 * @since 1.0
 * Hour represents a single hour in the daily schedule.
 * It contains tasks to be performed within that hour, total time spent on tasks, remaining time,
 * and information about backup volunteers if required.
 */

public class Hour {
	private ArrayList<Task2> tasks;
	private int remainingTime;
	private boolean backupVolunteerRequired;

	/**
	 * Returns a formatted string representation of the tasks scheduled within the hour.
	 * Includes information about backup volunteers if required, task description, species, and animal names.
	 * @return a formatted string representation of the tasks scheduled within the hour.
	 */
	public String printHour() {
		if (tasks.isEmpty()) {
			return "* No tasks scheduled\n";
		}

		String returnString = "";
		if (this.backupVolunteerRequired) {
			returnString += "[+ backup volunteer required]\n";
		}
		for (Task2 thisTask : tasks) {
			if (thisTask.getDescription().equals("Feed")) {
				returnString += String.format("* %s - %s (%d: %s)\n",
						thisTask.getDescription(), thisTask.getSpecies(),
						thisTask.getNumAnimals(), thisTask.namesToString());
			} else {
				returnString += String.format("* %s (%s)\n",
						thisTask.getDescription(), thisTask.namesToString());
			}
		}

		return returnString;
	}

	/**
	 * Constructs an Hour object with no tasks initially, total time set to 0, and remaining time set to 60.
	 */
	public Hour (){
		tasks = new ArrayList<Task2>();
		remainingTime = 60;
		backupVolunteerRequired = false;
	}

	/**
	 * Adds a new task to the hour and updates the remaining time.
	 * If the total time spent on tasks exceeds 60 minutes, a backup volunteer is marked as required.
	 * @param newTask the task to be added to the hour.
	 */
	public void addTask(Task2 newTask){
		tasks.add(newTask);
		int totalTime = newTask.getTaskTime() * newTask.getNumAnimals() + newTask.getPrepTime();
		this.remainingTime -= totalTime;
		if (totalTime > 60){
			backupVolunteerRequired = true;
		}
	}


	/**
	 * Schedules a backup volunteer if one is not already required.
	 * Sets the backup volunteer required flag to true and increases the remaining time by 60 minutes.
	 */
	public void scheduleBackup(){
		if(!this.backupVolunteerRequired){
			this.backupVolunteerRequired = true;
			this.remainingTime += 60;
		}
	}
	
	/**
	 * Returns the remaining time in minutes for the hour.
	 * @return the remaining time in minutes.
	 */
	public int getRemainingTime(){return this.remainingTime;}

	/**
	 * Returns whether a backup volunteer is required for the hour.
	 * @return true if a backup volunteer is required, false otherwise.
	 */
	
	public boolean getBackupVolunteerRequired(){return this.backupVolunteerRequired;}

}
