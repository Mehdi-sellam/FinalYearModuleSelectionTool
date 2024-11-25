	package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Module;
import model.StudentProfile;
import view.FinalYearOptionsRootPane;
import view.CreateStudentProfilePane;
import view.FinalYearOptionsMenuBar;
import view.ReserveModulesTab;
import view.SelectModulesTab;
import view.OverviewSelectionTab;
import model.Block;
import model.Course;
import view.SelectModulesTab;


import java.io.*;
import java.util.Set;

public class FinalYearOptionsController {

    private FinalYearOptionsRootPane view;
    private StudentProfile model;

    private CreateStudentProfilePane cspp;
    private FinalYearOptionsMenuBar mstmb;
    private SelectModulesTab selectModulesTab;
    private ReserveModulesTab reserveModulesTab;
    private OverviewSelectionTab overviewTab;
    public int totalCredits=60;

    public FinalYearOptionsController(FinalYearOptionsRootPane view, StudentProfile model) {
        this.view = view;
        this.model = model;
        
        view.setPreferredHeight(500);

        cspp = view.getCreateStudentProfilePane();
        mstmb = view.getModuleSelectionToolMenuBar();
        selectModulesTab = view.getSelectModulesTab();
        reserveModulesTab = view.getReserveModulesTab();
        overviewTab = view.getOverviewSelectionTab();

        cspp.addCourseDataToComboBox(buildModulesAndCourses());
        

        attachEventHandlers();
    }
    
    

    private void attachEventHandlers() {
        cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());
        mstmb.addExitHandler(e -> System.exit(0));
        selectModulesTab.getBtnReset().setOnAction(new ResetHandler());
        selectModulesTab.getBtnSubmit().setOnAction(new SubmitModulesHandler());
        selectModulesTab.getBtnAdd().setOnAction(new AddHandler());
        selectModulesTab.getBtnRemove().setOnAction(new RemoveHandler());
        reserveModulesTab.getBtnAdd().setOnAction(new AddReserveHandler());
        reserveModulesTab.getBtnRemove().setOnAction(new RemoveReserveHandler());
        reserveModulesTab.getBtnConfirm().setOnAction(new ConfirmHandler());

        mstmb.addSaveHandler(new SaveProfileHandler(overviewTab, model));
        mstmb.addLoadHandler(new LoadStudentDataHandler());
        mstmb.addAboutHandler(new AboutHandler());
        overviewTab.getBtnSaveOverview().setOnAction(new SaveOverviewHandler(overviewTab, model));
    }

    private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            model.setStudentCourse(cspp.getSelectedCourse());
            model.setStudentProfile(cspp.createStudentProfile());
            selectModulesTab.populateModulesBasedOnCourse(model.getStudentCourse());
            view.changeTab(1);
        }
    }

    private class ResetHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            selectModulesTab.populateModulesBasedOnCourse(model.getStudentCourse());
            reserveModulesTab.clearUnselectedModules();
            reserveModulesTab.clearReservedModules();

            selectModulesTab.setTotalCredits(totalCredits);
            
        }
    }

    private class SubmitModulesHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            ObservableList<Module> selectedModules = selectModulesTab.getSelectedModules();
            if (selectedModules.size() == 2) {
                model.setSelectedModules(selectedModules);

                // Clear unselected modules in the ReserveModulesTab
                reserveModulesTab.clearUnselectedModules();

                // Update unselected modules in the ReserveModulesTab
                Set<Module> unselectedModules = model.getUnselectedModules();
                reserveModulesTab.setUnselectedModules(unselectedModules);

                view.changeTab(2);
                
                showSuccessPopOut("Modules Have been Submitted");
            }
        }
        
        
        private void showSuccessPopOut(String message) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }



    private class AddHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            System.out.println("AddHandler triggered");
            Module selectedModule = selectModulesTab.getSelectedUnselectedModule();
            if (selectedModule != null && totalCredits <= 90) {
            	totalCredits += 30;
            	selectModulesTab.setTotalCredits(totalCredits);
                System.out.println("Selected Module: " + selectedModule);
                model.addSelectedModule(selectedModule);
                selectModulesTab.addSelectedModule(selectedModule);
            } else {
                System.out.println("You have acceded the limite of 120 cridits");
            }
            
            
            
        }
    }



    
    
    
    
    
    
    
    
    
    





    private class RemoveHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            ObservableList<Module> selectedModules = selectModulesTab.getSelectedModules();
            if (!selectedModules.isEmpty()) {
                Module selectedModule = selectedModules.get(0);
                selectModulesTab.removeSelectedModule(selectedModule);
                reserveModulesTab.clearUnselectedModules();
                System.out.println("Unselected Modules in SubmitModulesHandler: " + model.getUnselectedModules());
                totalCredits -= 30;
                selectModulesTab.setTotalCredits(totalCredits);
            }
        }
    }



    private class AddReserveHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Module selectedModule = reserveModulesTab.getSelectedUnselectedModule();
            if (selectedModule != null && reserveModulesTab.getReservedModules().size() < 1) {
                reserveModulesTab.addReservedModule(selectedModule);
                model.addReservedModule(selectedModule);
                
            }
        }
    }


    private class RemoveReserveHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Module selectedModule = reserveModulesTab.getSelectedReservedModule();
            if (selectedModule != null) {
                reserveModulesTab.removeReservedModule(selectedModule);
            }
        }
    }

    
    private class ConfirmHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
        	Module selectedModule = reserveModulesTab.getreservedModule();


            model.addReservedModule(selectedModule);

                // Update the OverviewSelectionTab with the reserved module information
            overviewTab.displayOverview(model.getStudentProfile());

                // Change the tab to the OverviewSelectionTab
           view.changeTab(3);
            
        }
    }




    public class SaveOverviewHandler implements EventHandler<ActionEvent> {

        private OverviewSelectionTab overviewSelectionTab;
        private StudentProfile studentProfile;

        public SaveOverviewHandler(OverviewSelectionTab overviewSelectionTab, StudentProfile studentProfile) {
            this.overviewSelectionTab = overviewSelectionTab;
            this.studentProfile = studentProfile;
        }

        @Override
        public void handle(ActionEvent e) {
            try {
                overviewSelectionTab.saveOverviewData(studentProfile);
                System.out.println("Overview data saved successfully.");
            } catch (IOException ex) {
                System.err.println("Error saving overview data: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    
    
    public class SaveProfileHandler implements EventHandler<ActionEvent> {

        private OverviewSelectionTab overviewSelectionTab;
        private StudentProfile studentProfile;

        public SaveProfileHandler(OverviewSelectionTab overviewSelectionTab, StudentProfile studentProfile) {
            this.overviewSelectionTab = overviewSelectionTab;
            this.studentProfile = studentProfile;
        }

        @Override
        public void handle(ActionEvent e) {
            try {
                overviewSelectionTab.saveOverviewDataDat(studentProfile);
                System.out.println("Overview data saved successfully.");
            } catch (IOException ex) {
                System.err.println("Error saving overview data: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
    
    
    


    private class LoadStudentDataHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student_profile.dat"))) {
                StudentProfile loadedProfile = (StudentProfile) ois.readObject();
                model.setStudentProfile(loadedProfile);
                updateUIState(loadedProfile);
                showSuccessPopOut("Profile loaded successfully.");
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                showErrorPopOut("Error loading profile.");
            }
        }
        
        
        
        
        // Method to update UI state based on the loaded profile
        private void updateUIState(StudentProfile model) {
        	
        	overviewTab.displayOverview(model);
            // Example: Change tabs based on loaded profile information
            /*model.getStudentProfile();*/
            view.changeTab(3); // Display undergraduate information on the first tab

            // Example: Display loaded data on the UI
        }
    }


    private void showSuccessPopOut(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorPopOut(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    private class AboutHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText(null);
            alert.setContentText("\n"+"Contact admin at admin@Ad.dmu.St.com"+"\n"+"\n"+"For any query");
            alert.showAndWait();
        }
    }

    
    
	//helper method - builds modules and course data and returns courses within an array
	private Course[] buildModulesAndCourses() {
		Module ctec3701 = new Module("CTEC3701", "Software Development: Methods & Standards", 30, true, Block.BLOCK_1);

		Module ctec3702 = new Module("CTEC3702", "Big Data and Machine Learning", 30, true, Block.BLOCK_2);
		Module ctec3703 = new Module("CTEC3703", "Mobile App Development and Big Data", 30, true, Block.BLOCK_2);

		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Block.BLOCK_3_4);

		Module ctec3704 = new Module("CTEC3704", "Functional Programming", 30, false, Block.BLOCK_3_4);
		Module ctec3705 = new Module("CTEC3705", "Advanced Web Development", 30, false, Block.BLOCK_3_4);

		Module imat3711 = new Module("IMAT3711", "Privacy and Data Protection", 30, false, Block.BLOCK_3_4);
		Module imat3722 = new Module("IMAT3722", "Fuzzy Logic and Inference Systems", 30, false, Block.BLOCK_3_4);

		Module ctec3706 = new Module("CTEC3706", "Embedded Systems and IoT", 30, false, Block.BLOCK_3_4);


		Course compSci = new Course("Computer Science");

		compSci.addModule(ctec3451);
		compSci.addModule(ctec3704);
		compSci.addModule(ctec3705);
		compSci.addModule(imat3711);
		compSci.addModule(imat3722);

		Course softEng = new Course("Software Engineering");

		softEng.addModule(ctec3451);
		softEng.addModule(ctec3704);
		softEng.addModule(ctec3705);
		softEng.addModule(ctec3706);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}

	
	
	

}
