package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;

public class FinalYearOptionsRootPane extends BorderPane {

    private CreateStudentProfilePane cspp;
    private FinalYearOptionsMenuBar mstmb;
    private SelectModulesTab selectModulesTab;
    private ReserveModulesTab reserveModulesTab;
    private TabPane tp;
    private OverviewSelectionTab overviewTab;  

    public FinalYearOptionsRootPane() {
        // create tab pane and disable tabs from being closed
        tp = new TabPane();
        tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        // create panes
        cspp = new CreateStudentProfilePane();
        selectModulesTab = new SelectModulesTab();
        reserveModulesTab = new ReserveModulesTab();

        // create tabs with panes added
        Tab t1 = new Tab("Create Profile", cspp);
        Tab t2 = new Tab("Select Modules", selectModulesTab);
        Tab t3 = new Tab("Reserve Modules", reserveModulesTab);

        // add tabs to tab pane
        tp.getTabs().addAll(t1, t2, t3);

        // create menu bar
        mstmb = new FinalYearOptionsMenuBar();

        // add menu bar and tab pane to this root pane
        this.setTop(mstmb);
        this.setCenter(tp);
        
        // Create Overview Selection Tab
        overviewTab = new OverviewSelectionTab();

        // Add the Overview Selection Tab to the TabPane
        tp.getTabs().add(overviewTab);
    }
    
    public void setPreferredHeight(double height) {
        setPrefHeight(height);
    }

    
    

    // methods allowing sub-containers to be accessed by the controller.
    public CreateStudentProfilePane getCreateStudentProfilePane() {
        return cspp;
    }

    public FinalYearOptionsMenuBar getModuleSelectionToolMenuBar() {
        return mstmb;
    }

    public SelectModulesTab getSelectModulesTab() {
        return selectModulesTab;
    }

    public ReserveModulesTab getReserveModulesTab() {
        return reserveModulesTab;
    }
    
    
    public OverviewSelectionTab getOverviewSelectionTab() {
        return overviewTab;
    }
    

    // method to allow the controller to change tabs
    public void changeTab(int index) {
        tp.getSelectionModel().select(index);
    }
}
