/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Peter Perri
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;


public class InventoryManagerController {

    @FXML private TableView<InventoryItem> inventoryView;
    @FXML private TableColumn<InventoryItem, String> itemNameView;
    @FXML private TableColumn<InventoryItem, String> valueView;
    @FXML private TableColumn<InventoryItem, String> serialNumView;
    @FXML private TextField nameField;
    @FXML private TextField valueField;
    @FXML private TextField serialField;
    @FXML private TextField searchBar;
    @FXML private RadioButton byNameButton;
    @FXML private CheckBox clearCheckBox;
    @FXML private Label infoLabel;
    private final InventoryList inventory = new InventoryList();
    private int index = 0;
    private static final String FILE_NOT_FOUND_MESSAGE = "File Not Found Exception";
    private static final String FILE_WROTE_MESSAGE = "Saved inventory successfully";
    private static final String IMPORT_SUCCESSFUL_MESSAGE = "Import Successful";



    @FXML
    void initialize(){
        //initializer to allow double-clicking and editing of cells

        //initialize fxml function handles setting up the TableView and all of its TableColumns
        //this means that editing the tableview is not testable, since editing is handled by javafx
        itemNameView.setCellFactory(TextFieldTableCell.forTableColumn());
        itemNameView.setOnEditCommit(event -> {
            //if the name entered is validated,
            if(InventoryItem.nameIsValid(event.getNewValue())){
                InventoryItem item = event.getRowValue();
                //then set the selected item's name to the value the user put in the tableview
                item.setName(event.getNewValue());
                infoLabel.setText("Item Name Updated");
            }
            //else don't update the name
            else{
                infoLabel.setText("Error: Name must be between 2\n and 256 characters.");
            }
            inventoryView.refresh();
        });
        valueView.setCellFactory(TextFieldTableCell.forTableColumn());
        valueView.setOnEditCommit(event -> {
            //if the value entered is validated,
            //if they put nothing, then value is set to 0
            if(InventoryItem.valueIsValid(event.getNewValue())){
                InventoryItem item = event.getRowValue();
                //then set the selected item's value to the value the user put in the tableview
                item.setValue(event.getNewValue());
                infoLabel.setText("Value successfully updated");
            }
            //else don't update the value and refresh the tableview
            else {
                infoLabel.setText("Error: invalid value. Must be a \nnumber greater than 0");
            }
            inventoryView.refresh();
        });
        serialNumView.setCellFactory(TextFieldTableCell.forTableColumn());
        serialNumView.setOnEditCommit(event -> {
            //if the serial number entered is validated,
            //then set the selected item's serial number to the value the user put in the tableview
            if(InventoryItem.serialNumberIsValid(event.getNewValue()) && serialIsNotDuplicate(event.getNewValue())){
                InventoryItem item = event.getRowValue();
                item.setSerialNumber(event.getNewValue());
                infoLabel.setText("Serial Number Updated");
            }
            //else don't update the serial number and refresh the tableview
            else{
                infoLabel.setText("""
                        Error: Serial Number must be in
                        format A-XXX-XXX-XXX, where A\s
                        is a letter and X is either a digit or\s
                        letter. Also cannot be duplicate.""");
                inventoryView.refresh();
            }
        });
    }

    @FXML
    void addItemButtonClicked(ActionEvent event) {
        //addItemToInventory attempts to add an item to the inventory and returns a string
        //based on if it was able to or not. if it wasn't able to, it gives a specific error
        //message
        String result = addItemToInventory(nameField.getText(), valueField.getText(), serialField.getText());
        infoLabel.setText(result);
        if(result.equals("Success!")){
            //if an item is successfully added to the list and the user chooses to,
            if(clearCheckBox.isSelected()){
                //clear the text fields
                clearTextFields();
            }
            displayItem();
        }
    }

    String addItemToInventory(String projectedName, String projectedValue, String projectedSerialNumber){
            //if projectedName, Value, and Serial Number are valid
            if(!(InventoryItem.nameIsValid(projectedName))){
                return "Error: Name must be between 2\n and 256 characters.";
            }
            else if(!(InventoryItem.valueIsValid(projectedValue))){
                return "Error: Value cannot be negative\n or empty.";
            }
            else if(!(InventoryItem.serialNumberIsValid(projectedSerialNumber))){
                return "Error: Serial Number must be in\n format A-XXX-XXX-XXX, where A \nis a letter and X is either a digit or \nletter.";
            }
            else if(!(serialIsNotDuplicate(projectedSerialNumber))){
                return "Error: Duplicate Serial Number";
            }
            else{
                //create a new InventoryItem and add it to the list
                inventory.getInventory().add(
                        new InventoryItem(projectedName, projectedValue, projectedSerialNumber.toUpperCase()));
                return "Success!";
            }
    }

    private void displayItem(){
        if(itemNameView != null && valueView != null && serialNumView != null && inventoryView != null){
            //display the created item and add to the index
            itemNameView.setCellValueFactory(new PropertyValueFactory<>("name"));
            valueView.setCellValueFactory(new PropertyValueFactory<>("value"));
            serialNumView.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
            inventoryView.getItems().add(inventory.getInventory().get(index));
            index++;
        }
    }

    boolean serialIsNotDuplicate(String serialNumber){
        //loop through the inventory before adding a new item to verify
        //that it does not have a duplicate serial number
        for (InventoryItem i : inventory.getInventory()){
            if(i.getSerialNumber().equals(serialNumber)){
                return false;
            }
        }
        return true;
    }

    void clearTextFields(){
        //clear all fields where the user can enter text
        nameField.clear();
        valueField.clear();
        serialField.clear();
    }

    @FXML
    void removeItemButtonClicked(ActionEvent event) {
        //remove the selected item from the inventory
        InventoryItem selectedItem = inventoryView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            //call the removeItem method to remove it from the list
            inventory.removeItem(selectedItem);
            //subtract from the index, which effectively counts how many items are in the list
            index--;
            //delete it from the TableView once it's been removed from the underlying data structure
            infoLabel.setText("Item removed from inventory");
            inventoryView.getItems().remove(selectedItem);
        }
        else{
            //if the user hasn't selected an item then do nothing and tell them that they didn't select anything
            infoLabel.setText("No item selected");
        }
    }

    @FXML
    void clearListButtonClicked(ActionEvent event) {
        //reset every data structure
        inventory.getInventory().clear();
        index = 0;
        inventoryView.getItems().clear();
        inventoryView.refresh();
    }

    @FXML
    void exportButtonClicked(ActionEvent event) {
        //FileChooser to select file path and type
        //create a new fileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save your Inventory");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Tab-Separated Values", "*.txt"),
                new FileChooser.ExtensionFilter("HyperText Markup Language", "*.html"),
                new FileChooser.ExtensionFilter("JavaScript Object Notation", "*.json"));
        //create a new file object based on where the user tells the fileChooser they want to save their file
        File selectedFile = fileChooser.showSaveDialog(inventoryView.getScene().getWindow());
        //pass the file to determineType to determine file type
        String fileType = determineType(selectedFile);
        //switch case to decide how to export the file
        switch (fileType) {
            case "TSV" -> exportTSV(selectedFile);
            case "HTML" -> exportHTML(selectedFile);
            case "JSON" -> exportJSON(selectedFile);
            default -> infoLabel.setText("File Type Error or \nFile Not Selected");
        }
    }

    String determineType(File file){
        //export the File that the user selected
        if(file != null){
            //use the last character of the fileName to determine the file type
            String lastChar = file.getName().substring(file.getName().length() - 1);

            //switch case to determine returned file type
            return switch (lastChar) {
                //tsv
                case "t" -> "TSV";

                //html
                case "l" -> "HTML";

                //json
                case "n" -> "JSON";

                default -> null;
            };
        }
        return null;
    }

    void exportTSV(File file){
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.printf("%s\t%s\t%s\t%n", "Serial Number", "Name", "Value");
            for(InventoryItem i : inventory.getInventory()){
                //for each item in the static arraylist todolist, write its description, dueDate, and completion
                //status to the file. separate each value with a comma, and put each item on its own line
                writer.printf("%s\t%s\t%s\t%n", i.getSerialNumber(), i.getName(), i.getValue());
            }
            if(this.infoLabel != null){
                infoLabel.setText(FILE_WROTE_MESSAGE);
            }
        } catch (FileNotFoundException e){
            //if there's an issue with writing to the file, update the infoLabel to tell the user there was an issue
            //we have to verify that it's not null so that this function (and the import function) can be tested.
            if(this.infoLabel != null){
                infoLabel.setText(FILE_NOT_FOUND_MESSAGE);
            }
        }
    }

    void exportHTML(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            //print the stuff that needs to be at the top of our document
            //to make it valid html + some formatting
            writer.println("""
                             <!DOCTYPE html>
                             <html>
                             <style>
                             table, th, td {
                               border:1px solid black;
                             }
                             tr:nth-child(even) {
                             background-color: #dddddd;
                             }
                             </style>
                             <body>
                             
                             <h2>Inventory</h2>
                             
                             <table style="width:100%">
                               <tr>
                                 <th>Item Name</th>
                                 <th>Value</th>
                                 <th>Serial Number</th>
                               </tr>""");
            //for loop adds table elements for each item in the inventory
            for(InventoryItem i : inventory.getInventory()){
                writer.printf("""
                        <tr>
                            <td>%s</td>
                            <td>%s</td>
                            <td>%s</td>
                          </tr>
                        """, i.getName(), i.getValue(), i.getSerialNumber());
            }
            //after for loop, write the stuff we need for this to be valid html +
            //an extra paragraph tag for fun
            writer.println("""
                    </table>
                                                     
                    <p>HTML Document produced by Inventory Management Application by @peteperri on GitHub</p>
                                                     
                    </body>
                    </html>
                             """);
            if(this.infoLabel != null){
                infoLabel.setText(FILE_WROTE_MESSAGE);
            }

        } catch (FileNotFoundException e){
            if(this.infoLabel != null){
                infoLabel.setText(FILE_NOT_FOUND_MESSAGE);
            }
        }
    }

    void exportJSON(File file){
        try (PrintWriter writer = new PrintWriter(file)){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //string json assigned to the return value of gson's toJson method when passed
            //the inventory
            String json = gson.toJson(inventory.getInventory());
            //write the json string to the file
            writer.println(json);
            if(this.infoLabel != null){
                infoLabel.setText(FILE_WROTE_MESSAGE);
            }
        } catch (FileNotFoundException e){
            if(this.infoLabel != null){
                infoLabel.setText(FILE_NOT_FOUND_MESSAGE);
            }
        }
    }


    @FXML
    void importButtonClicked(ActionEvent event) throws IOException {
        //create a new fileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import ToDoList File (.txt)");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(
                        "Inventory Supported File", "*.txt",
                        "*.html",
                        "*.json"));
        //create a new file object and assign it to whatever file the user selected
        File selectedFile = fileChooser.showOpenDialog(inventoryView.getScene().getWindow());
        String fileType = determineType(selectedFile);
        switch (fileType) {
            case "TSV" -> importInventoryFromTSV(selectedFile);
            case "HTML" -> importInventoryFromHTML(selectedFile);
            case "JSON" -> importInventoryFromJSON(selectedFile);
            default -> infoLabel.setText("File Type Error");
        }

        //display each item in the inventory
        for(int i = 0; i < inventory.getInventory().size(); i++) {
            displayItem();
        }
    }

    void importInventoryFromTSV(File file) throws FileNotFoundException {
        //create a FileReader based on the selected file
        FileReader reader = new FileReader(file);
        //clear the inventory to make sure it's empty before we import anything
        inventory.getInventory().clear();
        if(this.inventoryView != null){
            inventoryView.getItems().clear();
        }
        index = 0;

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            //while the file is not empty:
            while ((line = br.readLine()) != null) {
                //make an array of string values split by a tab
                String[] values = line.split("\t");
                //if that array's length is not 3, tell the user they imported an incompatible file
                //since that means the file has been tampered with, or
                // it did not originate from this application
                if(values.length != 3){
                    if(this.infoLabel != null){
                        infoLabel.setText("Incompatible file");
                    }
                    break;
                }
                //otherwise, add a new item to the to do list based on the array of string values read from the file
                else{
                    addItemToInventory(values[1], values[2], values[0]);
                }
                if(this.infoLabel != null){
                    infoLabel.setText(IMPORT_SUCCESSFUL_MESSAGE);
                }
            }
        } catch (IOException e) {
            //if the file can't be found for whatever reason, update the infoLabel to tell the user there was an issue
            if(this.infoLabel != null){
                infoLabel.setText(FILE_NOT_FOUND_MESSAGE);
            }
            e.printStackTrace();
        }
    }

    void importInventoryFromHTML(File file) throws IOException {
        //clear the inventory to make sure it's empty before we import anything
        inventory.getInventory().clear();
        if(this.inventoryView != null){
            inventoryView.getItems().clear();
        }
        index = 0;

        //convert the html file to a document using jsoup
        String str = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        Document htmlDoc = Jsoup.parse(str);
        Element table = htmlDoc.selectFirst("table");

        //parse the table
        if(table != null) {
            Iterator<Element> row = table.select("tr").iterator();
            //skipping table header <th>
            row.next();
            //while there are still rows in the table:
            while (row.hasNext()) {
                Iterator<Element> iterator = row.next().select("td").iterator();
                //add the contents of the table to the inventory
                addItemToInventory(iterator.next().text(), iterator.next().text(), iterator.next().text());
            }
            if (this.infoLabel != null) {
                infoLabel.setText(IMPORT_SUCCESSFUL_MESSAGE);
            }
        }
        else{
            if(this.infoLabel != null){
                infoLabel.setText("Incompatible File");
            }
        }
    }

    void importInventoryFromJSON(File file) {
        try {
            //clear the inventory to make sure it's empty before we import anything
            inventory.getInventory().clear();
            if (this.inventoryView != null) {
                inventoryView.getItems().clear();
            }
            index = 0;
            Gson gson = new Gson();
            //read in the file to a String
            String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            //convert the json to an array of inventory items
            InventoryItem[] inventoryItems = gson.fromJson(json, InventoryItem[].class);
            //add each item from the primitive array into our observable list
            for (InventoryItem i : inventoryItems) {
                inventory.getInventory().add(i);
            }
            if(this.infoLabel != null){
                infoLabel.setText(IMPORT_SUCCESSFUL_MESSAGE);
            }
        } catch (IOException e){
            if(this.infoLabel != null){
                infoLabel.setText(FILE_NOT_FOUND_MESSAGE);
            }
        }
    }

    @FXML
    void searchButtonClicked(ActionEvent event) {
        //determine if we are searching by name or by serial number
        boolean searchingByName = byNameButton.isSelected();
        //call displaySearchedItems while passing the contents of the search text field
        ObservableList<InventoryItem> searchedItems= displaySearchedItems(searchBar.getText(), searchingByName);
        //populate the TableView with the returned searchedItems list instead of all the items
        inventoryView.getItems().clear();
        inventoryView.setItems(searchedItems);
    }

    ObservableList<InventoryItem> displaySearchedItems(String searchString, boolean searchingByName){
        ObservableList<InventoryItem> searchedItems = FXCollections.observableArrayList();
        //for each loop to populate searchedItems based on the searchString
        //based on if we are searching by name or by serialNumber
        for(InventoryItem i : inventory.getInventory()){
            if(searchingByName){
                if(i.getName().contains(searchString)){
                    searchedItems.add(i);
                }
            }
            else{
                if(i.getSerialNumber().contains(searchString)){
                    searchedItems.add(i);
                }
            }
        }
        return searchedItems;
    }
}
