import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagerControllerTest {

    //Tests for requirement 2: The GUI SHALL have a control that allows the user to add a new inventory item
    @Test
    void addItemToInventory() {
        InventoryManagerController testController = new InventoryManagerController();
        //Test for requirement 3: Each inventory item SHALL have a name between 2 and 256 characters
        assertEquals("Error: Name must be between 2\n and 256 characters.",
                testController.addItemToInventory("e", "50", "A-123-123-123"));

        //Successful item add
        assertEquals("Success!",
                testController.addItemToInventory("eeee", "$0", "A-123-123-123"));

        //The application SHALL display an error message if the user enters an existing serial number for the new item
        assertEquals("Error: Duplicate Serial Number",
                testController.addItemToInventory("eeee", "$50", "A-123-123-123"));

        //Successful item add
        assertEquals("Success!",
                testController.addItemToInventory("eeee", "$50", "A-123-123-124"));

        //The application SHALL display an error message if the user enters an invalid item value
        assertEquals("Error: Value cannot be negative",
                testController.addItemToInventory("eeee", "-34", "A-123-123-125"));

        //Test for requirement 3: Each inventory item SHALL have a name between 2 and 256 characters
        assertEquals("Error: Name must be between 2\n and 256 characters.",
                testController.addItemToInventory("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",
                        "50", "A-123-123-123"));
    }

    //The application SHALL display an error message if the user enters an existing serial number for the new item
    @Test
    void serialIsNotDuplicate() {
        InventoryManagerController testController = new InventoryManagerController();
        assertTrue(testController.serialIsNotDuplicate("A-123-123-123"));
        testController.addItemToInventory("eee", "0", "A-123-123-123");
        assertFalse(testController.serialIsNotDuplicate("A-123-123-123"));
    }

    //Test for the function that returns a string based on the type of file it is passed,
    //so we can know what type of file we are working with and therefore which function to call
    //to import/export files
    @Test
    void determineType() {
        InventoryManagerController testController = new InventoryManagerController();
        File tsvTest = new File("tsvTest1.txt");
        File htmlTest = new File("htmlTest2.html");
        File jsonTest = new File("jsonTest3.json");
        assertEquals("TSV", testController.determineType(tsvTest));
        assertEquals("HTML", testController.determineType(htmlTest));
        assertEquals("JSON", testController.determineType(jsonTest));
    }

    //Test for requirement 13: The GUI SHALL have a control that allows the user to save their inventory items to a file
    @Test
    void importExportTest() throws IOException {
        //fill the inventory with 1025 dummy items
        InventoryManagerController testController = new InventoryManagerController();
        for (int i = 0; i < 1025; i++){
            String name = "item" + i;
            String serialNumFirstPart = "A-XYZ-00";
            String serialNumSecondPart = "";
            if(i < 10){
                serialNumSecondPart = "0-00" + i;
            }
            else if(i < 100){
                serialNumSecondPart = "0-0" + i;
            }
            else if(i < 1000){
                serialNumSecondPart = "0-" + i;
            }
            else if (i < 1010){
                serialNumFirstPart = "B-XYZ-00";
                serialNumSecondPart = "0-00" + (i - 1000);
            }
            else{
                serialNumFirstPart = "B-XYZ-00";
                serialNumSecondPart = "0-0" + (i - 1000);
            }
            assertEquals("Success!", testController.addItemToInventory(name, "", serialNumFirstPart + serialNumSecondPart));
        }

        //create test files and export them to their respective formats
        File tsvTest = new File("src/test/resources/tsvExportTest1.txt");
        File htmlTest = new File("src/test/resources/htmlExportTest2.html");
        File jsonTest = new File("src/test/resources/jsonExportTest3.json");
        testController.exportTSV(tsvTest);
        testController.exportHTML(htmlTest);
        testController.exportJSON(jsonTest);

        //TSV import test
        //clear the inventory to verify that we're checking the inventory that we got from the file we created earlier
        testController.getInventory().getInventory().clear();
        testController.importInventoryFromTSV(tsvTest);
        for (int i = 0; i < 1025; i++){
            String name = "item" + i;
            String serialNumFirstPart = "A-XYZ-00";
            String serialNumSecondPart = "";
            if(i < 10){
                serialNumSecondPart = "0-00" + i;
            }
            else if(i < 100){
                serialNumSecondPart = "0-0" + i;
            }
            else if(i < 1000){
                serialNumSecondPart = "0-" + i;
            }
            else if (i < 1010){
                serialNumFirstPart = "B-XYZ-00";
                serialNumSecondPart = "0-00" + (i - 1000);
            }
            else{
                serialNumFirstPart = "B-XYZ-00";
                serialNumSecondPart = "0-0" + (i - 1000);
            }
            assertEquals(testController.getInventory().getInventory().get(i).getName(), name);
            assertEquals(testController.getInventory().getInventory().get(i).getSerialNumber(), serialNumFirstPart + serialNumSecondPart);
        }

        //HTML import test
        //clear the inventory to verify that we're checking the inventory that we got from the file we created earlier
        testController.getInventory().getInventory().clear();
        testController.importInventoryFromHTML(htmlTest);
        for (int i = 0; i < 1025; i++){
            String name = "item" + i;
            String serialNumFirstPart = "A-XYZ-00";
            String serialNumSecondPart = "";
            if(i < 10){
                serialNumSecondPart = "0-00" + i;
            }
            else if(i < 100){
                serialNumSecondPart = "0-0" + i;
            }
            else if(i < 1000){
                serialNumSecondPart = "0-" + i;
            }
            else if (i < 1010){
                serialNumFirstPart = "B-XYZ-00";
                serialNumSecondPart = "0-00" + (i - 1000);
            }
            else{
                serialNumFirstPart = "B-XYZ-00";
                serialNumSecondPart = "0-0" + (i - 1000);
            }
            assertEquals(testController.getInventory().getInventory().get(i).getName(), name);
            assertEquals(testController.getInventory().getInventory().get(i).getSerialNumber(), serialNumFirstPart + serialNumSecondPart);
        }


        //JSON import test
        //clear the inventory to verify that we're checking the inventory that we got from the file we created earlier
        testController.getInventory().getInventory().clear();
        testController.importInventoryFromJSON(jsonTest);
        for (int i = 0; i < 1025; i++){
            String name = "item" + i;
            String serialNumFirstPart = "A-XYZ-00";
            String serialNumSecondPart = "";
            if(i < 10){
                serialNumSecondPart = "0-00" + i;
            }
            else if(i < 100){
                serialNumSecondPart = "0-0" + i;
            }
            else if(i < 1000){
                serialNumSecondPart = "0-" + i;
            }
            else if (i < 1010){
                serialNumFirstPart = "B-XYZ-00";
                serialNumSecondPart = "0-00" + (i - 1000);
            }
            else{
                serialNumFirstPart = "B-XYZ-00";
                serialNumSecondPart = "0-0" + (i - 1000);
            }
            assertEquals(testController.getInventory().getInventory().get(i).getName(), name);
            assertEquals(testController.getInventory().getInventory().get(i).getSerialNumber(), serialNumFirstPart + serialNumSecondPart);
        }

    }

    //test for requirements 11 and 12: the GUI SHALL have a control that allows the user to search for
    //and inventory item by serial number/by name
    @Test
    void displaySearchedItems() {
        //fill the inventory with 1025 dummy items
        InventoryManagerController testController = new InventoryManagerController();
        for (int i = 0; i < 1025; i++){
            String name = "item" + i;
            String serialNumFirstPart = "A-XYZ-00";
            String serialNumSecondPart = "";
            if(i < 10){
                serialNumSecondPart = "0-00" + i;
            }
            else if(i < 100){
                serialNumSecondPart = "0-0" + i;
            }
            else if(i < 1000){
                serialNumSecondPart = "0-" + i;
            }
            else if (i < 1010){
                serialNumFirstPart = "B-XYZ-00";
                serialNumSecondPart = "0-00" + (i - 1000);
            }
            else{
                serialNumFirstPart = "B-XYZ-00";
                serialNumSecondPart = "0-0" + (i - 1000);
            }
            assertEquals("Success!", testController.addItemToInventory(name, "", serialNumFirstPart + serialNumSecondPart));
        }

        //assert that for each item in the list has a name than contains "2"
        ObservableList<InventoryItem> itemsWith2InTheName = testController.displaySearchedItems("2", true);
        for(InventoryItem i : itemsWith2InTheName){
            assertTrue(i.getName().contains("2"));

        }
        //there should be 278 items with a "2" in the name
        assertEquals(278, itemsWith2InTheName.size());


        //assert that for each item in the list has a serial number than contains "B"
        ObservableList<InventoryItem> itemsWithBInTheSerialNumber = testController.displaySearchedItems("B", false);
        for(InventoryItem i : itemsWithBInTheSerialNumber){
            assertTrue(i.getSerialNumber().contains("B"));
            System.out.println(i.getSerialNumber());
        }
        //there should be 25 items with a "B" in the serial number
        assertEquals(25, itemsWithBInTheSerialNumber.size());

    }
}