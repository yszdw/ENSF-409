package edu.ucalgary.oop;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;

/**
* @author Shanzi Ye <a href="mailto:Shanzi.Ye@ucalgary.ca">Shanzi.Ye@ucalgary.ca</a>
* @author Connor HUNSZINGER <a href="mailto:Connor.HUNSZINGER@ucalgary.ca">Connor.HUNSZINGER@ucalgary.ca</a>
* @author Katarina Markic <a href="mailto:Katarina.Markic@ucalgary.ca">Katarina.Markic@ucalgary.ca</a>
* @author Nicole Nguyen <a href="mailto:Nicole.Nguyen@ucalgary.ca">Nicole.Nguyen@ucalgary.ca</a>
* @version 1.3
* @since 1.0
* The OutputFile class provides functionality to write a schedule to a file.
* The writeSchedule method takes a list of schedule lines and a file name, and writes the schedule to the file.
*/
public class OutputFile {
	
    /**
    * Writes a schedule to a file on disk.
    * @param scheduleLines the list of schedule lines to write to the file.
    * @param fileName the name of the file to write the schedule to.
    */
    public void writeSchedule(List<String> scheduleLines, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            LocalDate date = LocalDate.now();
            writer.write("Schedule for " + date.toString() + "\n\n");

            for (String line : scheduleLines) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error writing schedule to file: " + e.getMessage());
        }
    }
}
