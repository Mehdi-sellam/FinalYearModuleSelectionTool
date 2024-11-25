package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.StudentProfile;

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
