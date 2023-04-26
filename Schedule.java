package edu.ucalgary.oop;
import java.util.*;


/**
* @author Shanzi Ye <a href="mailto:Shanzi.Ye@ucalgary.ca">Shanzi.Ye@ucalgary.ca</a>
* @author Connor HUNSZINGER <a href="mailto:Connor.HUNSZINGER@ucalgary.ca">Connor.HUNSZINGER@ucalgary.ca</a>
* @author Katarina Markic <a href="mailto:Katarina.Markic@ucalgary.ca">Katarina.Markic@ucalgary.ca</a>
* @author Nicole Nguyen <a href="mailto:Nicole.Nguyen@ucalgary.ca">Nicole.Nguyen@ucalgary.ca</a>
* @version 2.0
* @since 1.0
* Class holding information for a daily schedule in a List of Hours.
* Has methods allowing the printing of the schedule, accessing any hour in the schedule, and scheduling a backup volunteer for a certain hour.
* 
*/
public class Schedule {
	private ArrayList<Hour> hourlySchedule;
	
	/**
	* Method that creates a List of Strings from hourlySchedule to be used when method in OutputFile is called.
	* Each String is produced using the method Hour.printHour().
	* The hours requiring backup volunteers are recorded and added at the end of the List.
	* @return	List<String>	returnSchedule	Each element is a String containing the scheduled tasks for that hour.
	*/
	public ArrayList<String> printSchedule() {
		ArrayList<String> returnSchedule = new ArrayList<>();
		String backupRequired = "These hours require a backup volunteer:\n";
		boolean backupNeeded = false;
		for (Hour thisHour : hourlySchedule) {
			String currentHourSchedule = "";
			currentHourSchedule += String.format("%d:00\n", hourlySchedule.indexOf(thisHour)); // Add newline character after the hour
			currentHourSchedule += thisHour.printHour() + "\n";
			currentHourSchedule += "\n";
			if (thisHour.getBackupVolunteerRequired()) {
				backupNeeded = true;
				backupRequired += String.format("%d\n", hourlySchedule.indexOf(thisHour));
			}
			returnSchedule.add(currentHourSchedule);
		}
		if (!backupNeeded) {

			backupRequired = "No backup volunteer required\n";
		}
		returnSchedule.add(backupRequired);
		return returnSchedule;
	}

	
	/**
	* Class constructor, initializes each hour in hourlySchedule
	*/
	public Schedule(){
		hourlySchedule = new ArrayList<Hour>();
		for (int i = 0; i < 24; i++){
			hourlySchedule.add(new Hour());
		}
	}
	/**
	* Getter for any hour in list when given index.
	* @param int index of the Hour that will be returned
	* @return Hour at the index
	*/
	public Hour getHour(int index){
		if (index >= 0 && index <= 23){
			return hourlySchedule.get(index);
		}else{
			return null;
		}
	}

	/**
	* From passed parameter, schedules backupVolunteer for hour at that index
	* @param int hour index of the hour where the backupVolunteer will be scheduled
	*/
	public void scheduleBackup(int hour){
		hourlySchedule.get(hour).scheduleBackup();
	}
	

}
