/*
Copyright Ann Barcomb and Emily Marasco, 2021
Licensed under GPL v3
See LICENSE.txt for more information.
*/

package edu.ucalgary.oop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.*;

/**
* @author Shanzi Ye <a href="mailto:Shanzi.Ye@ucalgary.ca">Shanzi.Ye@ucalgary.ca</a>
* @author Connor HUNSZINGER <a href="mailto:Connor.HUNSZINGER@ucalgary.ca">Connor.HUNSZINGER@ucalgary.ca</a>
* @author Katarina Markic <a href="mailto:Katarina.Markic@ucalgary.ca">Katarina.Markic@ucalgary.ca</a>
* @author Nhi Nguyen <a href="mailto:Nicole.Nguyen@ucalgary.ca">Nicole.Nguyen@ucalgary.ca</a>
* @version 1.3
* @since 1.0
* The GUI class provides a gui for the user to see schedule info and make decisions.
*/
public class GUI extends JFrame implements ActionListener, MouseListener{

    
    private String sHour;
    private int backupHour;
    private String sTo;
    private int to;
    private String sID;
    private int iD;

    private String schedule = "";
    private String unscheduled = "";
    private String treatments = "";
    private String backupHours;
    
    private JLabel instructions1;
    private JLabel backupLabel;
    private JLabel idLabel;
    private JLabel toLabel;

    private JLabel instructions2;
    private JTextField backupInput;
    private JTextField idInput;
    private JTextField toInput;

    private WildlifeScheduler sch = new WildlifeScheduler();
    private JTextArea textArea = new JTextArea(35, 40);
    private JTextArea textArea2 = new JTextArea(35, 40); 
    private JTextArea textArea3 = new JTextArea(35, 40);
    
    /**
     * Construtor for GUI objects
     */
    public GUI(){
        //super("Wildlife Shelter Scheduler");
        setupGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
    }
    
    /**
     * Runs when the GUI first starts. Makes 4 tabs with scrollable text
     */
    public void setupGUI(){
        
        JFrame frame = new JFrame();
        
        //create and add panels to tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panel1, panel2, panel3, panel4;
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        tabbedPane.addTab("Schedule", panel2);
        tabbedPane.addTab("Unscheduled", panel3);
        tabbedPane.addTab("Call Backup", panel1);
        tabbedPane.addTab("Reschedule", panel4);

        //text fields
        instructions1 = new JLabel("Call the backup volunteer:");
        backupLabel = new JLabel("Hour for Backup:");
        backupInput = new JTextField("e.g. 14", 4); 
        instructions2 = new JLabel("Reschedule a task:");
        idLabel = new JLabel("ID:");
        idInput = new JTextField("e.g. 1", 4); 
        toLabel = new JLabel("New Start Hour:");
        toInput = new JTextField("e.g. 14", 4); 

        backupInput.addMouseListener(this);
        idInput.addMouseListener(this);
        toInput.addMouseListener(this);

        //buttons
        JButton submitInfo = new JButton("Call Backup");
        submitInfo.addActionListener(this);

        JButton submitReschedule = new JButton("Reschedule Treatment");
        submitReschedule.addActionListener(this);

        JButton saveexit = new JButton("Save and Exit");
        saveexit.addActionListener(this);

        //add elements to panels
        panel1.add(instructions1);
        panel1.add(backupLabel);
        panel1.add(backupInput);
        panel1.add(submitInfo);
        panel4.add(instructions2);
        panel4.add(idLabel);
        panel4.add(idInput); 
        panel4.add(toLabel); 
        panel4.add(toInput);
        panel4.add(submitReschedule);                 

        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);

        JScrollPane scrollPane2 = new JScrollPane(textArea2);
        textArea2.setEditable(false);

        JScrollPane scrollPane3 = new JScrollPane(textArea3);
        textArea3.setEditable(false);

        frame.setSize(550,700);
        frame.setVisible(true);

        panel2.add(scrollPane);
        panel2.add(saveexit);
        panel3.add(scrollPane2);
        panel4.add(scrollPane3);
        frame.add(tabbedPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /*
     * Figure out which button is hit and take the approppriate action
     */
    public void actionPerformed(ActionEvent event){

        if(event.getActionCommand().equals("Call Backup")){
            this.sHour = backupInput.getText();
            if(validateBackup(sHour) != -1){
                JOptionPane.showMessageDialog(this, "Backup volunteer scheduled for " + sHour + ":00");
                sch.getSchedule().scheduleBackup(backupHour);
                this.updateScheduled();
                this.updateUnscheduled();
            }
        }

        if(event.getActionCommand().equals("Reschedule Treatment")){
            this.sID = idInput.getText();
            this.sTo = toInput.getText();
            if(validateID(sID) != -1 && validateTo(sTo) != -1){
                ConnectDatabase newDatabase = new ConnectDatabase();
                newDatabase.openDatabase();
                newDatabase.updateDataBase(to, iD);
                newDatabase.closeDataBase();
                this.update();
            }
                      
        }

        if(event.getActionCommand().equals("Save and Exit")){
            if(!backupHours.contains("No backup")){
                JOptionPane.showMessageDialog(this, "Do not forget to call backup volunteer for the following hour(s): \n" + backupHours);
            }
            OutputFile newFile = new OutputFile();
            newFile.writeSchedule(this.sch.getSchedule().printSchedule(), "Schedule.txt");
            JOptionPane.showMessageDialog(this, "File saved as Schedule.txt. Good-bye");  
            System.exit(0);
        }
    

        
    }
    
    /**
     * Clear the text inputs when clicked
     */
    public void mouseClicked(MouseEvent event){
        
        if(event.getSource().equals(backupInput))
            backupInput.setText("");

        if(event.getSource().equals(idInput))
            idInput.setText("");

        if(event.getSource().equals(toInput))
            toInput.setText("");

        }
                
    
    public void mouseEntered(MouseEvent event){
        
    }

    public void mouseExited(MouseEvent event){
        
    }

    public void mousePressed(MouseEvent event){
        
    }

    public void mouseReleased(MouseEvent event){
        
    }
    
    /*
     * Check that the backup volunteer is scheduled for a proper hour
     */
    private int validateBackup(String input){
        

        try{
            backupHour = (Integer.parseInt(input));
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Please enter an integer between 0 and 23");
            return -1;
        }
        
        if(backupHour < 0 || backupHour > 23){
            JOptionPane.showMessageDialog(this, "Please enter an integer between 0 and 23");
            return -1;
        }

        if(sch.getSchedule().getHour(backupHour).getBackupVolunteerRequired()){
            JOptionPane.showMessageDialog(this, "A volunteer is already scheduled for that hour. Try again.");
            return -1;
        }
        
        return backupHour;
        
    }

    /*
     * Check that the treatment id being rescheduled is valid
     */
    private int validateID(String input){


        try{
            iD = (Integer.parseInt(input));
        }catch(Exception e){
            return -1;
        }
        
        if(iD > sch.getTreatments().size() || iD < 0){
            JOptionPane.showMessageDialog(this, "Invalid treatment ID. Select a treatment ID from the list.");
            return -1;
        }
        
        return iD;
        
    }

    /*
     * Check that the time the treatment is being shifted to is valid
     */
    private int validateTo(String input){
        

        try{
            to = (Integer.parseInt(input));
        }catch(Exception e){
            return -1;
        }
        
        if( to < 0 || to > 23){
            JOptionPane.showMessageDialog(this, "Please enter an integer between 0 and 23");
            return -1;
        }
        
        return to;
        
    }

    /*
     * Run the scheduler again (done at the beginning, and when database changes)
     */
    private void update(){

        //connect to database
        ConnectDatabase newDatabase = new ConnectDatabase();
        newDatabase.openDatabase();
        ArrayList<Animal> animals = newDatabase.retrieveAnimal();
        ArrayList<Treatment> treatments = newDatabase.retrieveTreatment();
        ArrayList<Task> dbTasks = newDatabase.retrieveTask();
        this.sch = new WildlifeScheduler();
        this.sch.determineTasks(dbTasks, treatments, animals);

        //Write list of medical tasks
        ArrayList<Task2> medicalTasks = sch.determineMedicalTasks(dbTasks, treatments, animals);
        this.treatments = "";
        for(Task2 thisTask : medicalTasks){
			this.treatments += String.format("(Index: %d) %s (%s), Start time: %d:00. Window: %d hours\n",
					thisTask.getTreatmentID(), thisTask.getDescription(), thisTask.namesToString(), thisTask.getStartHour(), thisTask.getMaxWindow());
        }

        //update other fields
        updateScheduled();
        updateUnscheduled(); 

        //update text areas
        textArea.setText(this.schedule);
        textArea2.setText(this.unscheduled);
        textArea3.setText(this.treatments);
        newDatabase.closeDataBase();
    }

    /*
     * Try to schedule tasks again (done after backup volunteer is assigned)
     */
    private void updateScheduled(){
        sch.makeSchedule();
        ArrayList<String> finalSchedule = new ArrayList<String>();
        finalSchedule= sch.getSchedule().printSchedule();
        this.schedule = "";
        for(String line : finalSchedule){
            if(!line.contains("No backup volunteer required")){
                this.schedule += line + "\n";
            }
            backupHours = line; 
        }
    }

    /*
     * Update the unscheduled task list
     */
    private void updateUnscheduled(){
        if(sch.getTaskList().size() != 0){
            JOptionPane.showMessageDialog(this, "Not all tasks could be scheduled. Check Unscheduled tab.");
        }else{
            JOptionPane.showMessageDialog(this, "Schedule generated succesfully. Check Schedule tab");
        }
            this.unscheduled = "";
            for (Task2 thisTask : sch.getTaskList()){
                if(thisTask.getDescription()=="Feed"){ //change if we end up changing 'feed task' description
                    this.unscheduled += String.format("* %s - %s (%d: %s) Start time: %d:00. Window: %d hours\n"
                            , thisTask.getDescription(), thisTask.getSpecies(),
                            thisTask.getNumAnimals(),thisTask.namesToString(), thisTask.getStartHour(), thisTask.getMaxWindow());
                } else{
                    this.unscheduled += String.format("* %s (%s). Start time: %d:00. Window: %d hours\n",
                            thisTask.getDescription(), thisTask.namesToString(), thisTask.getStartHour(), thisTask.getMaxWindow());
                }
            }
        
        textArea.setText(schedule);
        textArea2.setText(unscheduled);
    }

    

    
    public static void main(String[] args) {
        
        GUI gui = new GUI();
        gui.sch = new WildlifeScheduler();
        gui.update();

    }

}
    