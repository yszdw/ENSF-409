package edu.ucalgary.oop;

import java.security.InvalidParameterException;

/**
* @author Shanzi Ye <a href="mailto:Shanzi.Ye@ucalgary.ca">Shanzi.Ye@ucalgary.ca</a>
* @author Connor HUNSZINGER <a href="mailto:Connor.HUNSZINGER@ucalgary.ca">Connor.HUNSZINGER@ucalgary.ca</a>
* @author Katarina Markic <a href="mailto:Katarina.Markic@ucalgary.ca">Katarina.Markic@ucalgary.ca</a>
* @author Nicole Nguyen <a href="mailto:Nicole.Nguyen@ucalgary.ca">Nicole.Nguyen@ucalgary.ca</a>
* @version 1.4
* @since 1.0
* The Animal class consists of constructors for animals, with properties such as its ID, nickname, species, feeding and cleaning times, and feeding preparation time.
*/
public class Animal {
    private final int ANIMALID;
    
    private final String ANIMALNICKNAME;
    
    private final String ANIMALSPECIES;

    private final int FEEDINGTIME;

    private final int FEEDINGPREPERATIONTIME;

    private final int CLEANINGTIME;

    private final boolean ISORPHAN;

    private final int FEEDINGWINDOW;

    private final int FEEDINGSTARTTIME;

    /**
    * Constructs an Animal object with the specified ID, nickname, and species.
    * Throws an InvalidParameterException if the species is not recognized.
    * @param animalID the ID of the animal.
    * @param animalNickname the nickname of the animal.
    * @param animalSpecies the species of the animal.
    * @throws InvalidParameterException if the species is not recognized.
    */
    public Animal(int animalID, String animalNickname, String animalSpecies) {
        this.ANIMALID = animalID;
        this.ANIMALNICKNAME = animalNickname;
        this.ANIMALSPECIES = animalSpecies;
        this.ISORPHAN = animalNickname.contains(" and ");
        this.FEEDINGWINDOW = 3;

        switch (ANIMALSPECIES) {
            case "coyote":
                this.FEEDINGSTARTTIME = 19;
                this.CLEANINGTIME = 5;
                this.FEEDINGTIME = 5;
                this.FEEDINGPREPERATIONTIME = 10;
                break;
            case "fox":
                this.FEEDINGSTARTTIME = 12;
                this.CLEANINGTIME = 5;
                this.FEEDINGTIME = 5;
                this.FEEDINGPREPERATIONTIME = 5;
                break;
            case "porcupine":
                this.FEEDINGSTARTTIME = 19;
                this.CLEANINGTIME = 10;
                this.FEEDINGTIME = 5;
                this.FEEDINGPREPERATIONTIME = 0;
                break;
            case "raccoon":
                this.FEEDINGSTARTTIME = 12;
                this.CLEANINGTIME = 5;
                this.FEEDINGTIME = 5;
                this.FEEDINGPREPERATIONTIME = 0;
                break;
            case "beaver":
                this.FEEDINGSTARTTIME = 8;
                this.CLEANINGTIME = 5;
                this.FEEDINGTIME = 5;
                this.FEEDINGPREPERATIONTIME = 0;
                break;
            default:
                throw new InvalidParameterException("Species not found.");
        
        }

    }
    
    
    /**
    * Returns the ID of the animal.
    * @return ANIMALID The ID of the animal.
    */
    public int getAnimalID() {
        return this.ANIMALID;
    }

    
    /**
    * Returns the nickname of the animal.
    * @return ANIMALNICKNAME The nickname of the animal.
    */
    public String getAnimalNickname() {
        return this.ANIMALNICKNAME;
    }

    
    /**
    * Returns the species of the animal.
    * @return ANIMALSPECIES The species of the animal.
    */
    public String getAnimalSpecies() {
        return this.ANIMALSPECIES;
    }

    /**
    * Returns the duration in minutes it takes to clean the animal's habitat.
    * @return CLEANINGTIME The duration in minutes it takes to clean the animal's habitat.
    */
    public int getCleaningTime() {
        return this.CLEANINGTIME;
    }
    
    /**
    * Returns the duration in minutes it takes to prepare the animal's food. 
    * @return FEEDINGPREPERATIONTIME The duration in minutes it takes to prepare the animal's food.
    */
    public int getFeedingPreparationTime() {
        return this.FEEDINGPREPERATIONTIME;
    }
    
    
    /**
    * Returns the duration in minutes it takes to feed the animal.
    * @return FEEDINGTIME The duration in minutes it takes to feed the animal.
    */
    public int getFeedingTime(){
        return this.FEEDINGTIME;
    }
    
    /**
    * Returns a boolean indicating whether the animal is an orphan.
    * @return ISORPHAN A boolean indicating whether the animal is an orphan.
    */

    public boolean getOrphan(){
        return this.ISORPHAN;
    }

    /**
    * Returns the time of day (in 24-hour format) when the animal's feeding schedule begins.
    * @return FEEDINGSTARTTIME	The time of day (in 24-hour format) when the animal's feeding schedule begins.
    */
    public int getStartTime(){ 
        return this.FEEDINGSTARTTIME;
    }

    
    /**
    * Returns the length of the feeding window, in hours.
    * @return FEEDINGWINDOW The length of the feeding window, in hours.
    */
    public int getMaxWindow(){ 
        return this.FEEDINGWINDOW;
    }

}