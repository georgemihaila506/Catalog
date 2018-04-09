package com.company.ViewFXML;

import com.company.Domain.Teme;
import com.company.Services.FilterServices;
import com.company.Services.TemeServices;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FilterTeme {
    @FXML
    TableView<Teme> tableView;
    @FXML
    private TableColumn<Teme,String> numarColumn;
    @FXML
    private TableColumn<Teme,String> descriereColumn;
    @FXML
    private TableColumn<Teme,String> deadlineColumn;
    @FXML
    private ComboBox deadlineCombo;
    @FXML
    private ComboBox descriereCombo;
    private TemeServices ctrlT;
    private FilterServices ctrlF;
    private Stage dialogStage;
    @FXML
    public void handleReload()
    {
        if(descriereCombo.getValue()==null && deadlineCombo.getValue()==null)
        {
            Alert message=new Alert(Alert.AlertType.ERROR);
            message.setTitle("Mesaj informativ!");
            message.setContentText("Trebuie sa selectati descrierea sau deadline-ul");
            message.showAndWait();
        }
        if(descriereCombo.getValue()!=null && deadlineCombo.getValue()==null)
        {
            String descriere=descriereCombo.getValue().toString();
            try {
                model=FXCollections.observableArrayList(ctrlF.FilterTemeByDescription(descriere));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            tableView.setItems(model);
        }
        if(descriereCombo.getValue()==null && deadlineCombo.getValue()!=null)
        {
            Integer deadline=Integer.parseInt(deadlineCombo.getValue().toString());
            try {
                model=FXCollections.observableArrayList(ctrlF.FilterTemeByDeadline(deadline));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            tableView.setItems(model);
        }
        if(descriereCombo.getValue()!=null && deadlineCombo.getValue()!=null)
        {
            String descriere=descriereCombo.getValue().toString();
            Integer deadline=Integer.parseInt(deadlineCombo.getValue().toString());
            try {
                model=FXCollections.observableArrayList(ctrlF.FilterTemeByDeadlineAndDesciption(descriere,deadline));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            tableView.setItems(model);
        }
        descriereCombo.getSelectionModel().clearSelection();
        deadlineCombo.getSelectionModel().clearSelection();
    }
    @FXML
    public void handleIesire()
    {
        dialogStage.close();
    }
    private ObservableList<Teme> model;
    @FXML
    public void initialize()
    {
        numarColumn.setCellValueFactory(new PropertyValueFactory<Teme,String>("Numar"));
        descriereColumn.setCellValueFactory(new PropertyValueFactory<Teme,String>("Descriere"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<Teme,String>("Deadline"));

    }

    public List<String> getDescriere()
    {
        Map<String,String> elemente=new TreeMap<>();
        List<String> listaF=new ArrayList<>();
        try {
            List<Teme> lista=ctrlT.getAll();
            for(Teme t:lista)
                elemente.put(t.getDescriere(),t.getDescriere());
            for(String el:elemente.values())
                listaF.add(el);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaF;
    }
    public void setService(TemeServices ctrlT, FilterServices ctrlF, Stage dialogStage) {
        this.ctrlF=ctrlF;
        this.ctrlT=ctrlT;
        this.dialogStage=dialogStage;
        try {
            model= FXCollections.observableArrayList(ctrlT.getAll());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tableView.setItems(model);
        deadlineCombo.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14");
        descriereCombo.setItems(FXCollections.observableArrayList(getDescriere()));
    }
}
