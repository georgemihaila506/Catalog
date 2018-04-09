package com.company.ViewFXML;

import com.company.Domain.Student;
import com.company.Services.FilterServices;
import com.company.Services.StudentServices;
import com.company.Utils.ObservableP;
import com.company.Utils.ObserverP;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentViewController implements ObserverP<Student> {
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student,String> idField;
    @FXML
    private TableColumn<Student,String> numeField;
    @FXML
    private TableColumn<Student,String> grupaField;
    @FXML
    private TableColumn<Student,String> emailField;
    @FXML
    private TableColumn<Student,String> tutoreField;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonFiltrari;
    @FXML
    private AnchorPane viewAdd;
    @FXML
    private TextField idView;
    @FXML
    private TextField numeView;
    @FXML
    private TextField grupaView;
    @FXML
    private TextField emailView;
    @FXML
    private TextField tutoreView;
    @FXML
    private Button buttonNext;
    @FXML
    private Button buttonBack;
    private StudentServices ctrlS;
    private ObservableList<Student> model;
    private FilterServices ctrlF;

    public StudentViewController()
    {

    }
    public void showStudentEditDialog(Student student) {
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StudentViewController.class.getResource("ViewMainStudent.fxml"));
            BorderPane root =(BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            EdiViewController editStudentViewController = loader.getController();
            editStudentViewController.setService(ctrlS, dialogStage,student);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showFilter()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StudentViewController.class.getResource("filtrareStudent.fxml"));
            BorderPane root = (BorderPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Filtrare Studenti");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            FilterStudentView controller = loader.getController();
            controller.setService(ctrlS, ctrlF, dialogStage);
            dialogStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setService(StudentServices ctrlS, FilterServices ctrlF)
    {
        this.ctrlS=ctrlS;
        this.ctrlF=ctrlF;
        this.ctrlS.addObserver(this);
        this.model=FXCollections.observableArrayList(ctrlS.getAllStudents());
        tableView.setItems(model);

    }
    @FXML
    public void handleUpdateStndent()
    {
        Student student1=tableView.getSelectionModel().getSelectedItem();
        if(student1!=null)
        {
            showStudentEditDialog(student1);
        }
        else
        {
            Alert message=new Alert(Alert.AlertType.ERROR);
            message.setTitle("Mesaj informativ!");
            message.setContentText("Nu a fost selectat nici un element!");
            message.showAndWait();
        }
    }
    @FXML
    public void handleDeleteStudent()
    {
        Student student1=tableView.getSelectionModel().getSelectedItem();
        if(student1!=null)
        {
            try {
                ctrlS.deleteOne(student1.getIdStudent());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Alert message=new Alert(Alert.AlertType.ERROR);
            message.setTitle("Mesaj informativ!");
            message.setContentText("Nu a fost selectat nici un element!");
            message.showAndWait();
        }
    }
    @FXML
    private void handleFilter()
    {
        showFilter();
    }
    @FXML
    private void initialize()
    {
        //this.buttonAdd.setOnAction(this::handleAddStudent);
        idField.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
        numeField.setCellValueFactory(new PropertyValueFactory<Student,String>("nume"));
        grupaField.setCellValueFactory(new PropertyValueFactory<Student,String>("grupa"));
        emailField.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
        tutoreField.setCellValueFactory(new PropertyValueFactory<Student,String>("CadruIndrumator"));
    }
    @FXML
    private void handleAddStudent() {

        showStudentEditDialog(null);
    }


    @Override
    public void update(ObservableP<Student> observable) {
        StudentServices services=(StudentServices) observable;
        try {
            model.setAll(services.getAll());
            this.tableView.setItems(model);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
