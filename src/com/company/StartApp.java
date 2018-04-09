package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage=primaryStage;
        primaryStage.setTitle("Student Management System");
        Scene scene = new Scene(initRootLayout());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Initializes the root layout.
     */
    public BorderPane initRootLayout() {
        try {
            //Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(StartApp.class.getResource("./ViewFXML/StudentViewFXML.fxml"));
            loader.setLocation(StartApp.class.getResource("./ViewFXML/NoteViewFXML.fxml"));
            //loader.setLocation(StartApp.class.getResource("./ViewFXML/LogIn.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();
            //RootLayoutController rootController=loader.getController();
            return rootLayout;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}