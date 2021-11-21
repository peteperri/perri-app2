/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Peter Perri
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryList {

    private final ObservableList<InventoryItem> inventory = FXCollections.observableArrayList();

    public boolean addItem(String name, String value, String serialNumber){
        if(InventoryItem.nameIsValid(name) && InventoryItem.valueIsValid(value) && InventoryItem.serialNumberIsValid(serialNumber)){
            inventory.add(new InventoryItem(name, value, serialNumber));
            return true;
        }
        else{
            return false;
        }
    }

    public void removeItem(InventoryItem item){
        inventory.remove(item);
    }

    public void clearList(){
        inventory.clear();
    }

    public ObservableList<InventoryItem> getInventory(){
        return inventory;
    }
}
