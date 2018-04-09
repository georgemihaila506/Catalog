package com.company.ViewFXML;

import com.company.Domain.*;
import com.company.Repository.RepoFileNote;
import com.company.Repository.RepoInFile;
import com.company.Services.NoteServices;
import com.company.Services.StudentServices;
import com.company.Services.TemeServices;
import com.company.Validatoare.NotaValidator;
import com.company.Validatoare.StudentValidator;
import com.company.Validatoare.TemeValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {



    RepoInFile<Student,String> repo=new RepoInFile<Student,String>("file1.txt",new StudentValidator());
    RepoInFile<Teme,Integer> repo2=new RepoInFile<Teme,Integer>("file2.txt",new TemeValidator());
    RepoInFile<Nota,Pereche> repo3=new RepoInFile<Nota,Pereche>("file3.txt",new NotaValidator());
    RepoFileNote repo4=new RepoFileNote("./src/com/company/NoteS.txt",new NotaValidator());
    StudentServices ctrlS=new StudentServices(repo);
    TemeServices ctrlT=new TemeServices(repo2);
    NoteServices ctrlN=new NoteServices(repo4,repo2);
    private int k=0;
    private User log=new User("admin","admin");

    @FXML
    private Text textResponse;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    private Stage primaryStage;
    public int getK()
    {
        return k;

    }    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        User u=new User(usernameField.getText().toString(),passwordField.getText().toString());
        if(u.getPassw().equals(log.getPassw())&& u.getUsername().equals(log.getUsername()))
        {
            Alert message=new Alert(Alert.AlertType.INFORMATION);
            message.setHeaderText("Se conecteaza!");
            message.setTitle("Informatii");
           // message.setContentText("Se conecteaza!");
            message.showAndWait();
            k=1;
            initialize();}
        else {
            textResponse.setText("Date incorecte!");
            usernameField.clear();
            passwordField.clear();
        }
    }



    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        if(k==1)
        {
            try {
                // create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(LoginController.class.getResource("MainView.fxml"));
                BorderPane root=(BorderPane) loader.load();
                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit Student");
                dialogStage.initModality(Modality.NONE);
                //dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(root);
                dialogStage.setScene(scene);

                setScena(dialogStage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void setScena(Stage primaryStage) {
        try {
            this.primaryStage.close();
        } catch (Exception e) {
            this.primaryStage=primaryStage;
            primaryStage.setFullScreen(false);
            primaryStage.setResizable(false);
            this.primaryStage.show();
        }
        //this.primaryStage.close();
        this.primaryStage=primaryStage;
        this.primaryStage.getIcons().addAll(new Image(LoginController.class.getResourceAsStream("menu.png")));
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        this.primaryStage.show();


    }
}
