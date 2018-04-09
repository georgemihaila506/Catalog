package com.company.ViewFXML;

import com.company.Domain.Nota;
import com.company.Domain.Student;
import com.company.Services.FilterServices;
import com.company.Services.NoteServices;
import com.company.Services.StudentServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilterNoteView {
    @FXML
    private TableColumn<Nota,String> idField;
    @FXML
    private TableColumn<Nota,String> numarField;
    @FXML
    private TableColumn<Nota,String> notaField;
    @FXML
    private TableView<Nota> tableView;
    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TableColumn<Student,String> numeColumn;
    @FXML
    private ComboBox studentiCombo;
    @FXML
    private ComboBox temeCombo;
    private NoteServices ctrlN;
    private FilterServices ctrlF;
    private Stage dialogStage;
    private ObservableList<Nota> model;
    private ObservableList<Student> modelStudent;
    @FXML
    public void handleReload()
    {

    }
    @FXML
    public void handleIesire()
    {
        dialogStage.close();
    }
    public List<Student> cautaStudenti()
    {
        List<Student> lista=new ArrayList<>();
        try {
            List<Nota> listaNote=ctrlN.getAll();
            for(Nota n:listaNote)
                lista.add(ctrlS.findOne(n.getIdStudent()));
            return lista;
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
        idField.setCellValueFactory(new PropertyValueFactory<Nota,String>("IdStudent"));
        numarField.setCellValueFactory(new PropertyValueFactory<Nota,String>("NrTemei"));
        notaField.setCellValueFactory(new PropertyValueFactory<Nota,String>("Valoare"));
        numeColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("nume"));
    }
    private StudentServices ctrlS;
    public void setService(NoteServices ctrlN, StudentServices ctrlS, FilterServices ctrlF, Stage dialogStage) {
        this.ctrlF=ctrlF;
        this.ctrlN=ctrlN;
        this.ctrlS=ctrlS;
        this.dialogStage=dialogStage;
        try {
            model= FXCollections.observableArrayList(ctrlN.getAll());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        modelStudent=FXCollections.observableArrayList(cautaStudenti());
        System.out.println(cautaStudenti());
        studentTableView.setItems(modelStudent);
        tableView.setItems(model);
    }
}
