package com.company.ViewFXML;

import com.company.Domain.Nota;
import com.company.Domain.Pereche;
import com.company.Domain.Student;
import com.company.Domain.Teme;
import com.company.Services.NoteServices;
import com.company.Services.StudentServices;
import com.company.Services.TemeServices;
import com.company.Validatoare.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;


public class EditNoteControler {
    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TableView<Teme> temeTableView;
    @FXML
    private TableColumn<Student, String> idStudentColumn;
    @FXML
    private TableColumn<Student, String> numeStudentColumn;
    @FXML
    private TableColumn<Teme, String> idTemaColumn;
    @FXML
    private TableColumn<Teme, String> descriereColumn;

    @FXML
    private TextField idField;
    @FXML
    private TextField numarField;
    @FXML
    private TextField notaField;
    @FXML
    private TextField saptaField;
    @FXML
    private TextArea observatiiField;
    @FXML
    private Button buttonSalvare;
    @FXML
    private Button buttonIesire;
    private NoteServices ctrlN;
    private Nota nota;
    private Stage dialogStage;
    private StudentServices ctrlS;
    private ObservableList<Student> modelStudent;
    private ObservableList<Teme> modelTeme;
    private TemeServices ctrlT;

    public void setService(NoteServices ctrlN, TemeServices ctrlT, StudentServices ctrlS, Stage dialogStage, Nota nota) {
        this.ctrlS = ctrlS;
        this.ctrlN = ctrlN;
        this.ctrlT = ctrlT;
        this.dialogStage = dialogStage;
        this.nota = nota;
        if (nota != null) {
            setFields(nota);
        }
        modelStudent = FXCollections.observableArrayList(ctrlS.getAllStudents());
        try {
            modelTeme = FXCollections.observableArrayList(ctrlT.getAll());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.studentTableView.setItems(modelStudent);
        this.temeTableView.setItems(modelTeme);
    }

    private void setFields(Nota nota) {
    }

    @FXML
    private void handleSave() {
        Student student = null;
        Teme tema = null;
        if (nota == null) {
            student = studentTableView.getSelectionModel().getSelectedItem();
            tema = temeTableView.getSelectionModel().getSelectedItem();
        } else {
            try {
                student = ctrlS.getStudent(nota.getIdStudent());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                tema = ctrlT.findOne(nota.getNrTemei());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (student != null && tema != null) {
            Integer notaaux = Integer.parseInt(noteCombo.getValue().toString());
            Nota nota1 = new Nota(new Pereche(student.getIdStudent(), tema.getNumar()), notaaux);
            if (nota == null) {
                saveNota(nota1);
            } else {
                updateNota(nota1);
            }
        } else {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setTitle("Mesaj eroare!");
            message.setContentText("Trebuie sa selectati un student si o tema");
            message.showAndWait();

        }
    }

    private void updateNota(Nota nota1) {
        try {
            this.ctrlN.updateNota(nota1, Integer.parseInt(saptamanaCombo.getValue().toString()), observatiiField.getText());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        dialogStage.close();
    }

    private void saveNota(Nota nota1) {
        try {
            if (ctrlN.findOne(nota1.getId()) != null) {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setTitle("Mesaj eroare!");
                message.setContentText("Exista deja!");
                message.showAndWait();
            } else {
                Nota saved = ctrlN.addNota(nota1, Integer.parseInt(saptamanaCombo.getValue().toString()), observatiiField.getText());
                //Student stud=ctrlS.getStudent(idField.getText());
                if (saved == null) {
                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setTitle("Mesaj informativ!");
                    message.setContentText("A fost adaugat cu succes!");
                    message.showAndWait();
                } else {
                    Alert message = new Alert(Alert.AlertType.ERROR);
                    message.setTitle("Mesaj eroare!");
                    message.setContentText("Nu a fost adaugat!");
                    message.showAndWait();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setTitle("Mesaj eroare!");
            message.setContentText("Nu a fost adaugat!");
            message.showAndWait();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }}

        @FXML
        private void handleIesire()
        {
            dialogStage.close();
        }
        @FXML
        private ComboBox noteCombo;
        @FXML
        private ComboBox saptamanaCombo;
        @FXML
        public void initialize()
        {
            idStudentColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
            numeStudentColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("nume"));
            idTemaColumn.setCellValueFactory(new PropertyValueFactory<Teme, String>("Numar"));
            descriereColumn.setCellValueFactory(new PropertyValueFactory<Teme, String>("Descriere"));
            noteCombo.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
            saptamanaCombo.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");

        }
    }

