package com.company.ViewFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;



public class Main1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        try {
//            //Load root layout from fxml file.
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("LogIn.fxml")); //URL
            BorderPane rootLayout= (BorderPane) loader.load();
            LoginController controller=loader.getController();


            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Log In");
            controller.setScena(primaryStage);
            primaryStage.getIcons().addAll(new Image(Main1.class.getResourceAsStream("menu.png")));
            primaryStage.show();
            //            //Load root layout from fxml file.
//            FXMLLoader loader=new FXMLLoader();
//            loader.setLocation(getClass().getResource("MainView.fxml")); //URL
//            BorderPane rootLayout= (BorderPane) loader.load();
//            MainController controller=loader.getController();
//
//
//            // Show the scene containing the root layout.
//            Scene scene = new Scene(rootLayout);
//            primaryStage.setScene(scene);
//            primaryStage.setTitle("Interfata");
//            //primaryStage.setFullScreen(true);
//            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
