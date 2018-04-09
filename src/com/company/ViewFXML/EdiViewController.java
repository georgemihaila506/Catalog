package com.company.ViewFXML;

import com.company.Domain.Student;
import com.company.Services.StudentServices;
import com.company.Validatoare.ValidationException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class EdiViewController {
    @FXML
    private TextField idField;
    @FXML
    private TextField numeField;
    @FXML
    private TextField grupaField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField tutoreField;
    @FXML
    private Button buttonIesire;
    @FXML
    private Button buttonSalvare;
    private StudentServices ctrl;
    private Stage dialogStage;
    private Student student;
    @FXML
    private void initialize()
    {}
    @FXML
    public void handleSave()
    {
        String id=idField.getText();
        String nume=numeField.getText();
        int grupa=Integer.parseInt(grupaField.getText());
        String email=emailField.getText();
        String tutore=tutoreField.getText();
        Student s=new Student(id,nume,grupa,nume,email);
        if(student==null)
        {
            saveStudent(s);
        }
        else
            updateStudent(s);
    }

    private void updateStudent(Student s) {
        try
        {
            this.ctrl.updateOne(s);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dialogStage.close();
    }
    @FXML
    public void handleIesire()
    {
        dialogStage.close();
    }
    private void saveStudent(Student s) {

        try
        {
            Student saved=ctrl.add(s);
            if(saved==null)
            {
                Alert message=new Alert(Alert.AlertType.INFORMATION);
                message.setTitle("Mesaj informativ!");
                message.setContentText("A fost adaugat cu succes!");
                message.showAndWait();
            }
            else
            {
                Alert message=new Alert(Alert.AlertType.ERROR);
                message.setTitle("Mesaj eroare!");
                message.setContentText("Nu a fost adaugat!");
                message.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setService(StudentServices ctrlS, Stage dialogStage, Student student) {
        this.ctrl=ctrlS;
        this.dialogStage=dialogStage;
        this.student=student;
        if(student!=null)
        {
            setFields(student);
            idField.setEditable(false);
        }
    }

    private void setFields(Student student) {
        idField.setText(student.getId());
        numeField.setText(student.getNume());
        String grupa= Integer.toString(student.getGrupa());
        grupaField.setText(grupa);
        emailField.setText(student.getEmail());
        tutoreField.setText(student.getCadruIndrumator());
    }
}
