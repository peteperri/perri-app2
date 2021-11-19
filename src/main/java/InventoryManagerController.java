import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.File;

public class InventoryManagerController {

    @FXML private TableView<InventoryItem> inventoryView;
    @FXML private TableColumn<InventoryItem, String> itemNameView;
    @FXML private TableColumn<InventoryItem, Float> valueView;
    @FXML private TableColumn<InventoryItem, String> serialNumView;
    @FXML private TextField nameField;
    @FXML private TextField valueField;
    @FXML private TextField serialField;
    @FXML private TextField searchBar;
    @FXML private RadioButton byNameButton;
    @FXML private RadioButton bySNButton;
    @FXML private Label infoLabel;
    private final InventoryList inventory = new InventoryList();

    @FXML
    void initialize(){
        //initializer to allow double-clicking and editing of cells
    }

    @FXML
    void addItemButtonClicked(ActionEvent event) {
        addItemToInventory(nameField.getText(), valueField.getText(), serialField.getText());
    }

    void addItemToInventory(String projectedName, String projectedValue, String projectedSerialNumber){
        //if projectedName, Value, and Serial Number are valid, create a new InventoryItem and add it to the list
        //parse float from projectedValue String
        //clear the text fields only if the item is successfully added
        clearTextFields();
    }

    void clearTextFields(){
        //clear all fields where the user can enter text
        nameField.clear();
        valueField.clear();
        serialField.clear();
    }

    @FXML
    void removeItemButtonClicked(ActionEvent event) {
        removeItemFromInventory();
    }

    void removeItemFromInventory(){
        //remove the selected item from the inventory
    }

    @FXML
    void clearListButtonClicked(ActionEvent event) {
        clearInventory();
    }

    void clearInventory(){
        //clear the inventory
    }

    @FXML
    void exportButtonClicked(ActionEvent event) {
        //FileChooser to select file path and type
        exportInventory(File selectedFile);
    }

    void exportInventory(File file){
        //export the File that the user selected; if branch for different types of files
    }


    @FXML
    void importButtonClicked(ActionEvent event) {
        //FileChooser to select what file to import
        importInventory(File selectedFile);
    }

    void importInventory(File file){
        //create a FileReader based on the file
        //clear current inventory
        //for each loop to populate the InventoryList and the TableView
    }

    @FXML
    void searchButtonClicked(ActionEvent event) {
        //determine if we are searching by name or by serial number
        //call displaySearchedItems while passing the contents of the search text field
        //populate the TableView with the returned searchedItems list instead of all the items
    }

    ObservableList<String> displaySearchedItems(String searchString, boolean searchingByName){
        ObservableList<String> searchedItems = FXCollections.observableArrayList();
        //for each loop to populate searchedItems based on the searchString based on if we are searching by name or by serialNumber
        return searchedItems;
    }

}
