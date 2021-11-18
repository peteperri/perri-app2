import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ListEditorController {

    @FXML
    private Button addItemButton;

    @FXML
    private RadioButton byNameButton;

    @FXML
    private RadioButton bySNButton;

    @FXML
    private Button clearListButton;

    @FXML
    private Button exportButton;

    @FXML
    private Button importButton;

    @FXML
    private Label infoLabel;

    @FXML
    private TableView<?> inventoryView;

    @FXML
    private TableColumn<?, ?> itemNameView;

    @FXML
    private TextField nameField;

    @FXML
    private Button removeItemButton;

    @FXML
    private TextField searchBar;

    @FXML
    private TextField serialField;

    @FXML
    private TableColumn<?, ?> serialNumView;

    @FXML
    private ToggleGroup toggleGroup1;

    @FXML
    private TextField valueField;

    @FXML
    private TableColumn<?, ?> valueView;

    @FXML
    void addItemButtonClicked(ActionEvent event) {

    }

    @FXML
    void clearListButtonClicked(ActionEvent event) {

    }

    @FXML
    void exportButtonClicked(ActionEvent event) {

    }

    @FXML
    void importButtonClicked(ActionEvent event) {

    }

    @FXML
    void removeItemButtonClicked(ActionEvent event) {

    }

    @FXML
    void searchButtonClicked(ActionEvent event) {

    }

}
