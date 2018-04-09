package com.company.ViewFXML;

import com.company.Domain.Student;
import com.company.Services.FilterServices;
import com.company.Services.StudentServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;


public class FilterStudentView {
    @FXML
    private ComboBox grupaCombo;
    @FXML
    private ComboBox tutoreCombo;
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
    private Stage dialogStage;
    private StudentServices ctrlS;
    private FilterServices ctrlF;
    private ObservableList<Student> model;
    @FXML
    public void handleReload()
    {
        if(grupaCombo.getValue()==null && tutoreCombo.getValue()==null)
        {
            Alert message=new Alert(Alert.AlertType.ERROR);
            message.setTitle("Mesaj informativ!");
            message.setContentText("Trebuie sa selectati grupa sau tutorele");
            message.showAndWait();
        }
        if(grupaCombo.getValue()==null && tutoreCombo.getValue()!=null)
        {
            try {
                model=FXCollections.observableArrayList(ctrlF.FilterStudentByTurore(tutoreCombo.getValue().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            tableView.setItems(model);
        }
        if(grupaCombo.getValue()!=null && tutoreCombo.getValue()==null)
        {
            Integer grupa= Integer.parseInt(grupaCombo.getValue().toString());
            try {
                model=FXCollections.observableArrayList(ctrlF.FilterStudentByGrupa(grupa));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            tableView.setItems(model);
        }
        if(grupaCombo.getValue()!=null && tutoreCombo.getValue()!=null)
        {
            Integer grupa= Integer.parseInt(grupaCombo.getValue().toString());
            try {
                model=FXCollections.observableArrayList(ctrlF.FilterStudentByTutoreAndGroup(grupa,tutoreCombo.getValue().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            tableView.setItems(model);
        }
        grupaCombo.getSelectionModel().clearSelection();
        tutoreCombo.getSelectionModel().clearSelection();
    }
    public void setService(StudentServices ctrlS,FilterServices ctrlF,Stage dialogStage)
    {
        this.ctrlF=ctrlF;
        this.ctrlS=ctrlS;
        this.dialogStage=dialogStage;
        model= FXCollections.observableArrayList(ctrlS.getAllStudents());
        tableView.setItems(model);
        grupaCombo.setItems(FXCollections.observableArrayList(getGrupe()));
        tutoreCombo.setItems(FXCollections.observableArrayList(getTutore()));

    }
    public List<String> getGrupe()
    {
        Map<String,String> lista=new TreeMap<>();
        List<String> listaF=new ArrayList<>();
        try {
            List<Student> studenti=ctrlS.getAll();
            for(Student el:studenti)
            {
                lista.put(Integer.toString(el.getGrupa()),Integer.toString(el.getGrupa()));
            }
            for(String el:lista.values())
            {
                listaF.add(el);
            }
            return listaF;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<String> getTutore()
    {
        Map<String,String> lista=new TreeMap<>();
        List<String> listaF=new ArrayList<>();
        try {
            List<Student> studenti=ctrlS.getAll();
            for(Student el:studenti)
            {
                lista.put(el.getCadruIndrumator(),el.getCadruIndrumator());
            }
            for(String el:lista.values())
            {
                listaF.add(el);
            }
            return listaF;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @FXML
    public void initialize()
    {

        idField.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
        numeField.setCellValueFactory(new PropertyValueFactory<Student,String>("nume"));
        grupaField.setCellValueFactory(new PropertyValueFactory<Student,String>("grupa"));
        emailField.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
        tutoreField.setCellValueFactory(new PropertyValueFactory<Student,String>("CadruIndrumator"));
    }
    @FXML
    public void handleExit()
    {
        dialogStage.close();
    }
}
