package view;

import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Course;
import model.Module;


public class ReserveModulesTab extends GridPane {

    private ListView<Module> lvUnselectedModules, lvReservedModules;
    private Button btnAdd, btnRemove, btnConfirm;

    public ReserveModulesTab() {
        initialize();
    }

    private void initialize() {
		//styling
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);

        ColumnConstraints column0 = new ColumnConstraints();
        column0.setHalignment(HPos.RIGHT);
        getColumnConstraints().addAll(column0);

        Label lblUnselected = new Label("Unselected Modules:");
        Label lblReserved = new Label("Reserved Modules:");

        lvUnselectedModules = new ListView<>();
        lvReservedModules = new ListView<>();

        btnAdd = new Button("Add ->");
        btnRemove = new Button("<- Remove");
        btnConfirm = new Button("Confirm");

        add(new VBox(lblUnselected, lvUnselectedModules), 0, 0);
        add(new VBox(lblReserved, lvReservedModules), 1, 0);

        HBox buttonBox = new HBox(10, btnAdd, btnRemove, btnConfirm);
        buttonBox.setAlignment(Pos.CENTER);
        add(buttonBox, 0, 1, 2, 1);
    }

    public void setUnselectedModules(ObservableList<Module> modules) {
        lvUnselectedModules.setItems(modules);
    }

    public void setReservedModules(ObservableList<Module> modules) {
        lvReservedModules.setItems(modules);
    }

    public Button getBtnAdd() {
        return btnAdd;
    }

    public Button getBtnRemove() {
        return btnRemove;
    }

    public Button getBtnConfirm() {
        return btnConfirm;
    }

    public Module getSelectedUnselectedModule() {
        return lvUnselectedModules.getSelectionModel().getSelectedItem();
    }
    
    public Module getreservedModule() {
        return lvReservedModules.getSelectionModel().getSelectedItem();
    }

    public Module getSelectedReservedModule() {
        return lvReservedModules.getSelectionModel().getSelectedItem();
    }

    public void addReservedModule(Module module) {
        lvReservedModules.getItems().add(module);
        lvUnselectedModules.getItems().remove(module);
    }

    public void removeReservedModule(Module module) {
        lvReservedModules.getItems().remove(module);
        lvUnselectedModules.getItems().add(module);
    }

    public void populateReserveModulesList(Course studentCourse) {
        ObservableList<Module> allModules = FXCollections.observableArrayList(studentCourse.getAllModules());
        lvUnselectedModules.setItems(allModules);
        lvReservedModules.getItems().clear();  // Assuming you clear the reserved list
    }

    
    public void clearReservedModules() {
        lvReservedModules.getItems().clear();
    }



    
    
    public void setUnselectedModules(Set<Module> unselectedModules) {
        lvUnselectedModules.getItems().addAll(unselectedModules);
    }

    public void setReservedModules(Set<Module> reservedModules) {
        lvReservedModules.getItems().addAll(reservedModules);
    }
    
    
    
    
    public ObservableList<Module> getReservedModules() {
        return lvReservedModules.getItems();
    }
    
    
    
    
    
    public void clearUnselectedModules() {
        lvUnselectedModules.getItems().clear();
    }



}
