import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryList {
    public final ObservableList<InventoryItem> inventory = FXCollections.observableArrayList();

    public boolean addItem(String name, float value, String serialNumber){
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
