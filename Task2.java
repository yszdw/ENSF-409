package edu.ucalgary.oop;
import java.util.*;

/**
 * @author Shanzi Ye <a href="mailto:Shanzi.Ye@ucalgary.ca">Shanzi.Ye@ucalgary.ca</a>
 * @author Connor HUNSZINGER <a href="mailto:Connor.HUNSZINGER@ucalgary.ca">Connor.HUNSZINGER@ucalgary.ca</a>
 * @author Katarina Markic <a href="mailto:Katarina.Markic@ucalgary.ca">Katarina.Markic@ucalgary.ca</a>
 * @author Nicole Nguyen <a href="mailto:Nicole.Nguyen@ucalgary.ca">Nicole.Nguyen@ucalgary.ca</a>
 * @version 1.8
 * @since 1.0
 * Task objects record how long a task takes and what in entails.
 */
public class  Task2 {
    private final int STARTHOUR;
    private final int MAXWINDOW;
    private final String DESCRIPTION;
    private int taskTime;
    private ArrayList<String> animalNicknames;
    private final int PREPTIME;
    private final String ANIMALSPECIES;
    private int numAnimals;
    private final int TREATMENTID;

    public void setAnimalNicknames(ArrayList<String> animalNicknames){this.animalNicknames = animalNicknames;}
    public ArrayList<String> getAnimalNicknames() {return this.animalNicknames;}
    public void setNumAnimals(int numAnimals){this.numAnimals = numAnimals;}
    public int getNumAnimals(){return this.numAnimals;}
    public int getPrepTime() {return this.PREPTIME;}
    public String getSpecies() {return this.ANIMALSPECIES;}
    public int getStartHour() {return this.STARTHOUR;}
    public int getMaxWindow() {return this.MAXWINDOW;}
    public int getTaskTime() {return this.taskTime;}
    public String getDescription() {return this.DESCRIPTION;}
    public void setTaskTime(int taskTime) {this.taskTime = taskTime;}
    public int getTreatmentID() {return this.TREATMENTID;}

    /**
     * Constructor used for medical and cleaning tasks. Automatically sets preptime
     * to 0 and numanimals to 1 if unspecified. This constructor is intended for
     * non-feeding tasks
     */
    Task2(String animalNickname, int startHour, int maxWindow, int taskTime, String description){
        this.animalNicknames = new ArrayList<String>();
        this.animalNicknames.add(animalNickname);
        this.STARTHOUR = startHour;
        this.MAXWINDOW = maxWindow;
        this.taskTime = taskTime;
        this.DESCRIPTION = description;
        this.ANIMALSPECIES = null;
        this.PREPTIME = 0;
        this.numAnimals = 1;
        this.TREATMENTID = 0;
    }

    Task2(String animalNickname, int startHour, int maxWindow, int taskTime, String description, int treatmentID){
        this.animalNicknames = new ArrayList<String>();
        this.animalNicknames.add(animalNickname);
        this.STARTHOUR = startHour;
        this.MAXWINDOW = maxWindow;
        this.taskTime = taskTime;
        this.DESCRIPTION = description;
        this.ANIMALSPECIES = null;
        this.PREPTIME = 0;
        this.numAnimals = 1;
        this.TREATMENTID = treatmentID;
    }

    /**
     * Constructor used for creating feeding tasks.
     * Populates animalNicknames with a single animal. Use addAnimal to add more
     * if required.
     */
    Task2(String animalNickname, String animalSpecies, int startHour, int maxWindow, int prepTime, int taskTime, String description){
        this.animalNicknames = new ArrayList<String>();
        this.animalNicknames.add(animalNickname);
        this.STARTHOUR = startHour;
        this.MAXWINDOW = maxWindow;
        this.taskTime = taskTime;
        this.DESCRIPTION = description;
        this.ANIMALSPECIES = animalSpecies;
        this.numAnimals = 1;
        this.PREPTIME = prepTime;
        this.TREATMENTID = 0;
    }
    /**
     * Adds an animal to the list
     * @param animalNickname The name of the animal
     */
    public void addAnimal(String animalNickname){
        this.numAnimals += 1;
        animalNicknames.add(animalNickname);
    }

    /**
     * splits the object into two objects. Used to split a feeding task
     * into two seperate feeding tasks.
     * @param num number of animals in the new task.
     * @return  New task
     */
    public Task2 split(int num){
        Task2 task = new Task2(this.animalNicknames.get(0), this.ANIMALSPECIES,
                this.STARTHOUR, this.MAXWINDOW, this.PREPTIME, this.taskTime, this.DESCRIPTION);
        task.setNumAnimals(1);
        this.numAnimals = this.numAnimals - num;
        for(int i = 0; i < num-1; i++){
            task.addAnimal(animalNicknames.get(1));
            animalNicknames.remove(0);
        }
        animalNicknames.remove(0);
        return task;
    }

    /**
     * Converts the arraylist of strings to a single, formatted string
     * @return formatted string of names
     */
    public String namesToString(){
        String names = "";
        for(int i = 0; i < this.animalNicknames.size(); i++){
            names += this.animalNicknames.get(i);
            if(i < this.animalNicknames.size() - 2){
                names += ", ";
            }
            if(i == this.animalNicknames.size() -2){
                names += " and ";
            }
        }
        return names;
    }

}
