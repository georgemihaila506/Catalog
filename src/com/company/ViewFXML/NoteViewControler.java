package com.company.ViewFXML;

import com.company.Domain.Nota;
import com.company.Services.FilterServices;
import com.company.Services.NoteServices;
import com.company.Services.StudentServices;
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


public class NoteViewControler implements ObserverP<Nota> {
    @FXML
    private TableColumn<Nota,String> idField;
    @FXML
    private TableColumn<Nota,String> numarField;
    @FXML
    private TableColumn<Nota,String> notaField;
    @FXML
    private TableView<Nota> tableView;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonFiltrare;
    public void showNotaEditDialog(Nota nota) {
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StudentViewController.class.getResource("EditViewNote.fxml"));
            BorderPane root =(BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Nota");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            EditNoteControler editViewController = loader.getController();
            editViewController.setService(ctrlN,ctrlT,ctrlS, dialogStage,nota);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleAdd()
    {
        showNotaEditDialog(null);
    }
    @FXML
    public void handleUpdate()
    {
        Nota nota1=tableView.getSelectionModel().getSelectedItem();
        if(nota1!=null)
        {
            showNotaEditDialog(nota1);
        }
        else
        {
            Alert message=new Alert(Alert.AlertType.ERROR);
            message.setTitle("Mesaj informativ!");
            message.setContentText("Nu a fost selectat nici un element!");
            message.showAndWait();
        }
    }

    private NoteServices ctrlN;
    private TemeServices ctrlT;
    private ObservableList<Nota> model;
    private StudentServices ctrlS;

    public NoteViewControler()
    {}
    public void setService(NoteServices ctrlN, FilterServices ctrlF,TemeServices ctrlT,StudentServices ctrlS)
    {
        this.ctrlS=ctrlS;
        this.ctrlN=ctrlN;
        this.ctrlT=ctrlT;
        this.ctrlF=ctrlF;
        this.ctrlN.addObserver(this);
        try {
            this.model= FXCollections.observableArrayList(ctrlN.getAll());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tableView.setItems(model);

    }
    @FXML
    public void handleFilter()
    {
        showFilter();
    }
    private FilterServices ctrlF;
    private void showFilter() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(NoteViewControler.class.getResource("filterNote.fxml"));
            BorderPane root = (BorderPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Filtrare Note");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            FilterNoteView controller = loader.getController();
            controller.setService(ctrlN, ctrlS,ctrlF, dialogStage);
            dialogStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize()
    {
        idField.setCellValueFactory(new PropertyValueFactory<Nota,String>("IdStudent"));
        numarField.setCellValueFactory(new PropertyValueFactory<Nota,String>("NrTemei"));
        notaField.setCellValueFactory(new PropertyValueFactory<Nota,String>("Valoare"));
    }

    @Override
    public void update(ObservableP<Nota> observable) {
        NoteServices noteServicesS=(NoteServices) observable;
        try {
            this.model.setAll(noteServicesS.getAll());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
