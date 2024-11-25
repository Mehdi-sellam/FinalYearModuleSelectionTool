	package view;
	
	import java.util.Collection;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
	import model.Block;
	import model.Course;
	import model.Module;
	import controller.FinalYearOptionsController;
	
	public class SelectModulesTab extends GridPane {
	
	    private ListView<Module> lvBlock1, lvBlock2, lvBlock3_4Unselected, lvBlock3_4Selected;
	    private Label lblCredits;
	    private Button btnAdd, btnRemove, btnReset, btnSubmit;
	
	    public SelectModulesTab() {
	        initialize();
	        totalCredits = 0;
	    }
	
	    private void initialize() {
	        // Set up GridPane properties
	        // styling
	        this.setVgap(15);
	        this.setHgap(20);
	        this.setAlignment(Pos.CENTER);

	        ColumnConstraints column0 = new ColumnConstraints();
	        column0.setHalignment(HPos.RIGHT);
	        getColumnConstraints().addAll(column0);

	        // Create components
	        Label lblBlock1 = new Label("Block 1 modules:");
	        Label lblBlock2 = new Label("Block 2 modules:");
	        Label lblBlock3_4Unselected = new Label("Block 3/4 - Unselected modules:");
	        Label lblBlock3_4Selected = new Label("Block 3/4 - Selected modules:");

	        lvBlock1 = new ListView<>();
	        lvBlock2 = new ListView<>();
	        lvBlock3_4Unselected = new ListView<>();
	        lvBlock3_4Selected = new ListView<>();

	        lblCredits = new Label("Total Credits: 0");

	        btnAdd = new Button("Add ->");
	        btnRemove = new Button("<- Remove");
	        btnReset = new Button("Reset");
	        btnSubmit = new Button("Submit");

	        // Add components to the GridPane
	        // Top left
	        add(new VBox(lblBlock3_4Unselected, lvBlock3_4Unselected), 0, 0);

	        // Top right
	        add(new VBox(lblBlock1, lvBlock1), 1, 0);

	        // Bottom left
	        add(new VBox(lblBlock3_4Selected, lvBlock3_4Selected), 0, 1);

	        // Bottom right
	        add(new VBox(lblBlock2, lvBlock2), 1, 1);

	        add(lblCredits, 1, 2, 3, 1);

	        // Center buttons
	        HBox buttonBox = new HBox(10, btnAdd, btnRemove, btnReset, btnSubmit);
	        buttonBox.setAlignment(Pos.CENTER);
	        add(buttonBox, 0, 3, 2, 2);

	        // Make components resizable
	        setHgrow(lblBlock1, Priority.ALWAYS);
	        setHgrow(lvBlock1, Priority.ALWAYS);
	        setHgrow(lblBlock2, Priority.ALWAYS);
	        setHgrow(lvBlock2, Priority.ALWAYS);
	        setHgrow(lblBlock3_4Unselected, Priority.ALWAYS);
	        setHgrow(lvBlock3_4Unselected, Priority.ALWAYS);
	        setHgrow(lblBlock3_4Selected, Priority.ALWAYS);
	        setHgrow(lvBlock3_4Selected, Priority.ALWAYS);
	        setHgrow(lblCredits, Priority.ALWAYS);
	        setHgrow(buttonBox, Priority.ALWAYS);
	    }


	
	    public void setBlock1Modules(ObservableList<Module> modules) {
	        lvBlock1.setItems(modules);
	    }	
	
	    public void setBlock2Modules(ObservableList<Module> modules) {
	        lvBlock2.setItems(modules);
	    }
	
	    public void setBlock3_4ModulesUnselected(ObservableList<Module> modules) {
	        lvBlock3_4Unselected.setItems(modules);
	    }
	
	    public void setBlock3_4ModulesSelected(ObservableList<Module> modules) {
	        lvBlock3_4Selected.setItems(modules);
	    }
	
	    public void setTotalCredits(int credits) {
	        lblCredits.setText("Total Credits: " + credits);
	    }
	
	    public Button getBtnAdd() {
	        return btnAdd;
	    }
	
	    public Button getBtnRemove() {
	        return btnRemove;
	    }
	
	    public Button getBtnReset() {
	        return btnReset;
	    }
	
	    public Button getBtnSubmit() {
	        return btnSubmit;
	    }
	
	    public ObservableList<Module> getSelectedModules() {
	        return lvBlock3_4Selected.getItems();
	    }
	
	    public Module getSelectedUnselectedModule() {
	        return lvBlock3_4Unselected.getSelectionModel().getSelectedItem();
	    }
	
	    public void addSelectedModule(Module module) {
	        lvBlock3_4Selected.getItems().add(module);
	        lvBlock3_4Unselected.getItems().remove(module);
	    }
	
	    public void removeSelectedModule(Module module) {
	        lvBlock3_4Selected.getItems().remove(module);
	        lvBlock3_4Unselected.getItems().add(module);
	    }
	    public void populateModulesBasedOnCourse(Course selectedCourse) {
	        // Clear existing items
	        lvBlock1.getItems().clear();
	        lvBlock2.getItems().clear();
	        lvBlock3_4Unselected.getItems().clear();
	        lvBlock3_4Selected.getItems().clear();
	
	        // Populate modules based on the selected course for BLOCK_1
	        lvBlock1.getItems().addAll(selectedCourse.getModulesForBlock1());
	
	        // Populate modules based on the selected course for BLOCK_2
	        lvBlock2.getItems().addAll(selectedCourse.getModulesForBlock2());
	
	        // Populate modules based on the selected course for BLOCK_3_4
	        lvBlock3_4Unselected.getItems().addAll(selectedCourse.getModulesForBlock3_4());
	
	        // Add compulsory modules for BLOCK_1
	        lvBlock1.getItems().addAll(selectedCourse.getCompulsoryModulesForBlock1());
	
	        // Add compulsory modules for BLOCK_2
	        lvBlock2.getItems().addAll(selectedCourse.getCompulsoryModulesForBlock2());
	
	
	        
	    }
	
	
	
	
	
	
	
	
	    
	    public Module getSelectedModule() {
	        return lvBlock3_4Unselected.getSelectionModel().getSelectedItem();
	    }
	
	
	
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    private int totalCredits;
	
	
	
	
	
	    private void setTotalCreditsLabel() {
	        lblCredits.setText("Total Credits: " + totalCredits);
	    }
	
	    public void resetForm() {
	        totalCredits = 0;
	        setTotalCreditsLabel();
	        lvBlock1.getItems().clear();
	        lvBlock2.getItems().clear();
	        lvBlock3_4Unselected.getItems().clear();
	        lvBlock3_4Selected.getItems().clear();
	        btnSubmit.setDisable(true);  // Disable the submit button after reset
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	}
