package view;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import model.Name;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.StudentProfile;
import model.Course;
import model.Module;



public class OverviewSelectionTab extends Tab {

    private TextArea studentDetailsArea, selectedModulesArea, reservedModulesArea;
    private Button btnSaveOverview;
    private StudentProfile studentProfile;
    
    
    public OverviewSelectionTab() {
        initialize();
        
    }


    public OverviewSelectionTab(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
        initialize();
    }

    private void initialize() {
    	// Create text areas
    	studentDetailsArea = new TextArea();
    	selectedModulesArea = new TextArea();
    	reservedModulesArea = new TextArea();

    	// Create save button
    	btnSaveOverview = new Button("Save Overview");

    	// Set up layout
    	VBox vBox = new VBox(25, // Set the spacing to 10 (you can adjust this value)
    	    studentDetailsArea,
    	    new HBox(25, reservedModulesArea, selectedModulesArea),
    	    btnSaveOverview);
    	vBox.setAlignment(Pos.CENTER);

    	// Set HBox to grow for resizing
    	HBox.setHgrow(reservedModulesArea, Priority.ALWAYS);
    	HBox.setHgrow(selectedModulesArea, Priority.ALWAYS);

    	setContent(vBox);



        // Set tab title
        setText("Overview Selection");

        // Set the onAction handler for btnSaveOverview
        btnSaveOverview.setOnAction(new SaveOverviewHandler(this, studentProfile));
    }



    public TextArea getStudentDetailsArea() {
        return studentDetailsArea;
    }

    public TextArea getSelectedModulesArea() {
        return selectedModulesArea;
    }

    public TextArea getReservedModulesArea() {
        return reservedModulesArea;
    }

    public Button getBtnSaveOverview() {
        return btnSaveOverview;
    }

    public void displayOverview(StudentProfile studentProfile) {
        // Display student details
        studentDetailsArea.setText("Name: " + studentProfile.getStudentName() + "\n");
        studentDetailsArea.appendText("PNo: " + studentProfile.getStudentPnumber() + "\n");
        studentDetailsArea.appendText("Email: " + studentProfile.getStudentEmail() + "\n");
        studentDetailsArea.appendText("Date: " + studentProfile.getSubmissionDate() + "\n");
        studentDetailsArea.appendText("Course: " + studentProfile.getStudentCourse().getCourseName() + "\n");

        // Display selected modules
        selectedModulesArea.setText("Selected modules:\n");
        for (Module selectedModule : studentProfile.getAllSelectedModules()) {
            displayModuleDetails(selectedModule, selectedModulesArea);
        }
        
        // Display mandatory modules for the selected course
        Course selectedCourse = studentProfile.getStudentCourse();
        if (selectedCourse != null) {
            reservedModulesArea.appendText("\nMandatory modules for the course:\n");
            for (Module mandatoryModule : selectedCourse.getCompulsoryModulesForBlock1()) {
                displayModuleDetails(mandatoryModule, selectedModulesArea);
            }
            for (Module mandatoryModule : selectedCourse.getCompulsoryModulesForBlock2()) {
                displayModuleDetails(mandatoryModule, selectedModulesArea);
            }
            


            // Display reserved modules
            reservedModulesArea.appendText("Reserved modules:\n");
            for (Module reservedModule : studentProfile.getReservedModules()) {
            	System.out.println("hello");
            	System.out.println(reservedModule);
            	System.out.println("hh");
                displayModuleDetails(reservedModule, reservedModulesArea);
            }



        }
    }

    private void displayModuleDetails(Module module, TextArea area) {
        area.appendText("Module code: " + module.getModuleCode() + "\n");
        area.appendText("Module name: " + module.getModuleName() + "\n");
        area.appendText("Credits: " + module.getCredits() + "\n");
        area.appendText("Mandatory on your course? " + (module.isMandatory() ? "yes" : "no") + "\n");
        area.appendText("Block: " + module.getBlock() + "\n\n");
    }
    
    
    
    public void saveOverviewData(StudentProfile overviewProfile) throws IOException {
        String pNumber = overviewProfile.getStudentPnumber();
        Name studentName = overviewProfile.getStudentName();
        String email = overviewProfile.getStudentEmail();
        LocalDate submissionDate = overviewProfile.getSubmissionDate();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Student_profile.txt"))) {
            writer.write("PNumber: " + pNumber);
            writer.newLine();
            writer.write("Name: " + studentName.getFullName());
            writer.newLine();
            writer.write("Email: " + email);
            writer.newLine();
            writer.write("Submission Date: " + submissionDate.toString());
            writer.newLine();

            writer.write("Selected Modules:");
            writer.newLine();
            for (Module module : overviewProfile.getAllSelectedModules()) {
                writer.write(module.toString());
                writer.newLine();
            }

            // Add the logic to save reserved modules
            writer.write("Reserved Modules:");
            writer.newLine();
            for (Module reservedModule : overviewProfile.getReservedModules()) {
                writer.write(reservedModule.toString());
                writer.newLine();
            }
        }
    }  
        
        
        public void saveOverviewDataDat(StudentProfile overviewProfile) throws IOException {
            
        										
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Student_profile.dat"))) {
                    oos.writeObject(overviewProfile);
                    System.out.println("saved");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    
}
