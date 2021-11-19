/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Peter Perri
 */
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import java.util.Objects;

public class InventoryManagementApplication extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //set up the stage, load fxml files, and set the title of the window
        JMetro jMetro = new JMetro(Style.LIGHT);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InventoryManager.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Inventory Manager");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        jMetro.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        //launch the application
        launch(args);
    }
}
