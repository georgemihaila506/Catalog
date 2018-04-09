package com.company.ViewFXML;

import com.company.Domain.Teme;
import com.company.Services.FilterServices;
import com.company.Services.TemeServices;
import com.company.Utils.ObservableP;
import com.company.Utils.ObserverP;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TemeViewController implements ObserverP<Teme> {
    private TemeServices ctrlT;
    public TemeViewController()
    {

    }
    private ObservableList<Teme> model;
    @FXML
    TableView<Teme> tableView;
    @FXML
    private TableColumn<Teme,String> numarColumn;
    @FXML
    private TableColumn<Teme,String> descriereColumn;
    @FXML
    private TableColumn<Teme,String> deadlineColumn;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonDeadline;
    @FXML
    private Button buttonFiltrari;
    @FXML
    private FilterServices ctrlF;
    public void setService(TemeServices ctrlT,FilterServices ctrlF)
    {
        this.ctrlT=ctrlT;
        this.ctrlF=ctrlF;
        this.ctrlT.addObserver(this);
        try {
            this.model= FXCollections.observableArrayList(ctrlT.getAll());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tableView.setItems(model);

    }
    @FXML
    public void handleUpdateTema()
    {
        Teme tema1=tableView.getSelectionModel().getSelectedItem();
        if(tema1!=null)
        {
            showTemaEditDialog(tema1);
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
    public void handleAdd()
    {
     showTemaEditDialog(null);
    }
    private void showTemaEditDialog(Teme tema1) {
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StudentViewController.class.getResource("EditViewTeme.fxml"));
            BorderPane root =(BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            EditTemeControler editTemeControler=loader.getController();
            editTemeControler.setService(ctrlT, dialogStage,tema1);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDeleteTema()
    {
        Teme tema1=tableView.getSelectionModel().getSelectedItem();
        if(tema1!=null)
        {
            try {
                ctrlT.deleteOne(tema1.getNumar());
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
    public void initialize()
    {
        numarColumn.setCellValueFactory(new PropertyValueFactory<Teme,String>("Numar"));
        descriereColumn.setCellValueFactory(new PropertyValueFactory<Teme,String>("Descriere"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<Teme,String>("Deadline"));
    }
    public  void showFilter()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TemeViewController.class.getResource("filtrareTeme.fxml"));
            BorderPane root = (BorderPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Filtrare Teme");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            FilterTeme controller = loader.getController();
            controller.setService(ctrlT, ctrlF, dialogStage);
            dialogStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleFilter()
    {
        showFilter();
    }
    @Override
    public void update(ObservableP<Teme> observable) {
        TemeServices services=(TemeServices)observable;
        try {
            model.setAll(services.getAll());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.tableView.setItems(model);

    }
}
