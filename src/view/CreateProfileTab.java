// CreateProfileTab.java
package view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import model.Course;
import model.Name;

public class CreateProfileTab extends GridPane {

    private ComboBox<Course> cboCourses;
    private TextField txtPNumber, txtFirstName, txtSurname, txtEmail, txtSubmissionDate;
    private Button btnCreateProfile;

    public CreateProfileTab() {
        initialize();
        addComponents();
    }

    private void initialize() {
        // Set up GridPane properties
        setVgap(5);
        setHgap(5);
        setAlignment(Pos.CENTER);

        ColumnConstraints column0 = new ColumnConstraints();
        column0.setHalignment(HPos.RIGHT);
        getColumnConstraints().addAll(column0);

        // Create components
        Label lblTitle = new Label("Select course: ");
        Label lblPNumber = new Label("Input P number: ");
        Label lblFirstName = new Label("Input first name: ");
        Label lblSurname = new Label("Input surname: ");
        Label lblEmail = new Label("Input email: ");
        Label lblSubmissionDate = new Label("Input submission date: ");

        cboCourses = new ComboBox<>();
        txtPNumber = new TextField();
        txtFirstName = new TextField();
        txtSurname = new TextField();
        txtEmail = new TextField();
        txtSubmissionDate = new TextField();
        btnCreateProfile = new Button("Create Profile");

        // Add components to the GridPane
        add(lblTitle, 0, 0);
        add(cboCourses, 1, 0);

        add(lblPNumber, 0, 1);
        add(txtPNumber, 1, 1);

        add(lblFirstName, 0, 2);
        add(txtFirstName, 1, 2);

        add(lblSurname, 0, 3);
        add(txtSurname, 1, 3);

        add(lblEmail, 0, 4);
        add(txtEmail, 1, 4);

        add(lblSubmissionDate, 0, 5);
        add(txtSubmissionDate, 1, 5);

        add(new HBox(), 0, 6);
        add(btnCreateProfile, 1, 6);

        // Populate combo box with courses
        addCourseDataToComboBox();
    }

    private void addComponents() {
        // Implement the logic to add additional components if needed
    }

    private void addCourseDataToComboBox() {
        // Add logic to populate cboCourses with available courses
        // (You can use a method similar to the one in CreateStudentProfilePane)
    }

    // Add methods to retrieve form input as needed

    // Add method to attach event handler for btnCreateProfile as needed
}
