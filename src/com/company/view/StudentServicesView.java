package com.company.view;

import com.company.Domain.Student;
import com.company.Services.StudentServices;
import com.company.Utils.ListEvent;
import com.company.Utils.Observer;
import com.company.Validatoare.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StudentServicesView implements Observer<Student> {
    private StudentServices ctrlS;
    private ObservableList<Student> model;
    private StudentView view;
    public StudentServicesView(StudentServices ctrlS)
    {
        this.ctrlS=ctrlS;
        model= FXCollections.observableArrayList(ctrlS.getAllStudents());
    }
    public void setView(StudentView view)
    {
        this.view=view;

    }
    public ObservableList<Student> getModel()
    {
        return model;
    }
    public void showMessageDetails(Student newValue) {
        if(newValue != null) {
            view.idField.setText(newValue.getId());
            view.idField.setDisable(true);
            view.numeField.setText(newValue.getNume());
            String aux=Integer.toString(newValue.getGrupa());
            view.grupaField.setText(aux);
            view.emailField.setText(newValue.getEmail());
            view.tutoreField.setText(newValue.getCadruIndrumator());
        }
    }

    @Override
    public void notifyEvent(ListEvent<Student> e) {
        model.setAll(StreamSupport.stream(e.getList().spliterator(),false).collect(Collectors.toList()));
    }
    public void handleAddStudent(ActionEvent actionEvent)
    {
        try
        {
            if(ctrlS.add(getStudentFromFields())!=null)
            {
                showErrorMessage("Exista deja un student cu acest ID");
            }
            else
            {
                showMessage(Alert.AlertType.INFORMATION,"Salvare","Studentul a fost salvata cu succes!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            showErrorMessage(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void handleUpdateStudent(ActionEvent actionEvent)
    {
        try {
            Student s=getStudentFromFields();
            if(ctrlS.findOne(s.getId())!=null) {
                ctrlS.updateOne(s);
                showMessage(Alert.AlertType.INFORMATION, "Actualizare", "Studentul a fost actualizat cu succes!");
            }
            else
            {
                showErrorMessage("Nu exista  un student cu acest ID");
            }
            } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    public void handleDeleteStudent(ActionEvent actionEvent)
    {
        try {
            if(ctrlS.deleteOne(view.idField.getText())==null)
            {
                showErrorMessage("Nu exista  un student cu acest ID");
            }
            else
            {
                showMessage(Alert.AlertType.INFORMATION,"Stergere","Studentul a fost sters cu succes!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void handleClearAll(ActionEvent actionEvent)
    {
        view.idField.clear();
        view.idField.setDisable(false);
        view.tutoreField.clear();
        view.numeField.clear();
        view.grupaField.clear();
        view.emailField.clear();
    }

    private void showMessage(Alert.AlertType information, String salvare, String s) {
        Alert message=new Alert(information);
        message.setHeaderText(salvare);
        message.setContentText(s);
        message.showAndWait();
    }

    private void showErrorMessage(String s) {
        Alert message=new Alert(Alert.AlertType.ERROR);
        message.setTitle("Mesaj eroare!");
        message.setContentText(s);
        message.showAndWait();
    }

    public Student getStudentFromFields() {
        String id=view.idField.getText();
        String nume=view.numeField.getText();
        int grupa=Integer.parseInt(view.grupaField.getText());
        String email=view.emailField.getText();
        String tutore=view.tutoreField.getText();
        return new Student(id,nume,grupa,email,tutore);
    }
}
