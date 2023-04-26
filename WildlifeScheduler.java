package edu.ucalgary.oop;

import java.util.*;
import java.awt.EventQueue;

/**
 * @author Shanzi Ye <a href="mailto:Shanzi.Ye@ucalgary.ca">Shanzi.Ye@ucalgary.ca</a>
 * @author Connor HUNSZINGER <a href="mailto:Connor.HUNSZINGER@ucalgary.ca">Connor.HUNSArrayListZINGER@ucalgary.ca</a>
 * @author Katarina Markic <a href="mailto:Katarina.Markic@ucalgary.ca">Katarina.Markic@ucalgary.ca</a>
 * @author Nhi Nguyen <a href="mailto:Nicole.Nguyen@ucalgary.ca">Nicole.Nguyen@ucalgary.ca</a>
 * @version ????
 * @since 1.0
 * This class is the main class for our project
 */
public class WildlifeScheduler{

    private ArrayList<Task2> taskList;  //tasks are removed as they are scheduled
    private Schedule schedule = new Schedule();
    private ArrayList<Task2> treatments;

    public WildlifeScheduler(){

    }

    public Schedule getSchedule(){return this.schedule;}
    public ArrayList<Task2> getTaskList(){return this.taskList;}
    public void setTaskList(ArrayList<Task2> taskList){this.taskList = taskList;}
    public ArrayList<Task2> getTreatments(){return this.treatments;}
    

    /**
     * Determines the cleaning tasks for a list of animals and returns a list of corresponding Task2 objects.
     * @param animals List of Animal objects representing the animals in the database.
     * @return List of Task2 objects representing the cleaning tasks for each animal in the database.
     */
    public void determineTasks(ArrayList<Task> tasks, ArrayList<Treatment> treatments, ArrayList<Animal> animals){
        this.taskList = determineMedicalTasks(tasks, treatments, animals);
        this.taskList.addAll(determineCleaningTasks(animals));
        this.taskList.addAll(determineFeedingTasks(animals));
    }

    /**
     * Determines the medical tasks for a list of animals and returns a list of corresponding Task2 objects.
     * @param tasks List of Task objects representing the tasks in the databse.
     * @param treatments List of Treatment objects representing the treatments in the database.
     * @param animals List of Animal objects representing the animals in the databse.
     * @return List of Task2 objects representing the medical tasks for each animal in the database.
     */
    public ArrayList<Task2> determineMedicalTasks(ArrayList<Task> tasks, ArrayList<Treatment> treatments, ArrayList<Animal> animals){
        ArrayList<Task2> taskList = new ArrayList<>();
        this.treatments = new ArrayList<Task2>();
        for(int i =0; i < treatments.size(); i++){
            int startHour = treatments.get(i).getStartHour();
            int maxWindow = tasks.get(treatments.get(i).getTaskID() - 1).getMaxWindow();
            int taskTime = tasks.get(treatments.get(i).getTaskID() - 1).getDuration();
            String animalNickname = animals.get(treatments.get(i).getAnimalID() - 1).getAnimalNickname();
            String description = tasks.get(treatments.get(i).getTaskID() - 1).getDescription();
            int treatmentID = treatments.get(i).getTreatmentID();
            this.treatments.add(new Task2(animalNickname, startHour, maxWindow, taskTime, description, treatmentID));
            taskList.add(new Task2(animalNickname, startHour, maxWindow, taskTime, description, treatmentID));
        }
        return taskList;
    }

    /**
     * Determines the cleaning tasks for a list of animals and returns a list of corresponding Task2 objects.
     * @param animals List of Animal objects representing the animals in the database.
     * @return List of Task2 objects representing the cleaning tasks for each animal in the database.
     */
    public ArrayList<Task2> determineCleaningTasks(ArrayList<Animal> animals){
        ArrayList<Task2> taskList = new ArrayList<>();
        //cleaning
        for(int i =0; i < animals.size(); i++){
            int startHour = 0;
            int maxWindow = 24;
            int taskTime = animals.get(i).getCleaningTime(); // Get the cleaning time from the AnimalTypes enum
            String animalNickname = animals.get(i).getAnimalNickname();
            String description = "Clean cage"; //TODO: consider better description
            taskList.add(new Task2(animalNickname, startHour, maxWindow, taskTime, description));
        }
        return taskList;
    }


    /**
     * Determines the feeding tasks for a list of animals and returns a list of corresponding Task2 objects.
     * @param animals List of Animal objects representing the animals in the database.
     * @return List of Task2 objects representing the feeding tasks for each animal in the database.
     */
    public ArrayList<Task2> determineFeedingTasks(ArrayList<Animal> animals){
        HashMap<String, Task2> hm = new HashMap<String, Task2>();
        for(int i = 0; i < animals.size(); i++){
            Animal animal = animals.get(i);
            if(animal.getOrphan()){
                continue;
            }
            if(hm.containsKey(animal.getAnimalSpecies())){
                hm.get(animal.getAnimalSpecies()).addAnimal(animal.getAnimalNickname());
            }else{
                hm.put(animal.getAnimalSpecies(), new Task2(animal.getAnimalNickname(),
                        animal.getAnimalSpecies(), animal.getStartTime(), animal.getMaxWindow(),
                        animal.getFeedingPreparationTime(), animal.getFeedingTime(), "Feed")); //TODO: consider better description
            }
        }
        return new ArrayList<>(hm.values());
    }


    /**
     * schedules tasks into each hour
     * @param tasks
     * @return
     */
    public void makeSchedule() {
        for (int i = 0; i < 24; i++) {
            Hour hour = schedule.getHour(i);
            scheduleHour(hour, i);
        }
    }


    /**
     * Schedules the most high-priority tasks into the hour until no more fit
     * @param tasks
     * @param hour
     */

    public void scheduleHour(Hour hour, int currentHour){
        Task2 newTask;
        do{
            newTask = findNextTask(hour.getRemainingTime(), currentHour);
            if(newTask != null){
                hour.addTask(newTask);
            }
        }while(newTask != null);
        return;
    }


    /**
     * finds the best task to add it to the schedule, removes it from the task-list,
     * and returns the task. Returns null if no suitable task is possible.
     * @param tasks
     * @param timeRemaining
     * @return
     */
    public Task2 findNextTask(int remainingTime, int hour){
        int earliestDeadline = 9999;
        int numToDo;
        Task2 nextTask = null;
        Task2 newTask;


        //find next task to do (earliest deadline)
        for(int i = 0; i < taskList.size(); i++){
            int deadline = taskList.get(i).getStartHour() + taskList.get(i).getMaxWindow() - 1;
            int minTime = taskList.get(i).getTaskTime() + taskList.get(i).getPrepTime();
            int startTime = taskList.get(i).getStartHour();
            if(deadline < earliestDeadline && minTime <= remainingTime && startTime <= hour && deadline >= hour){
                earliestDeadline = deadline;
                nextTask = taskList.get(i);
            }
        }

        if(nextTask == null){
            return null;
        }

        if (remainingTime < 0){
            return null;
        }

        //determine how many to do
        remainingTime -= nextTask.getPrepTime();
        numToDo = Integer.min(remainingTime /nextTask.getTaskTime(), nextTask.getNumAnimals());

        //remove from task list
        if(nextTask.getNumAnimals() == numToDo){
            taskList.remove(nextTask);
            newTask = nextTask;
        }else{
            newTask = nextTask.split(numToDo);

        }

        return newTask;
    }

}
