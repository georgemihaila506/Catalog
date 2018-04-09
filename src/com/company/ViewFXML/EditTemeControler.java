package com.company.ViewFXML;

import com.company.Domain.Teme;
import com.company.Services.TemeServices;
import com.company.Validatoare.ValidationException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditTemeControler {
    @FXML
    private TextField numarField;
    @FXML
    private TextField descriereField;
    @FXML
    private TextField deadlineField;
    @FXML
    private TextField saptamanField;
    @FXML
    private Button buttonIesire;
    @FXML
    private Button buttonSalvare;
    private TemeServices ctrl;
    private Stage dialogStage;
    private Teme tema;
    @FXML
    private void initialize()
    {
    }
    @FXML
    public void handleSave()
    {
        int numar=Integer.parseInt(numarField.getText());
        String  desc=descriereField.getText();
        int deadline=Integer.parseInt(deadlineField.getText());
        Teme s=new Teme(numar,desc,deadline);
        if(tema==null)
        {
            saveTema(s);
        }
        else
            updateDeadline(s);
    }

    private void updateDeadline(Teme s) {
        try
        {
            this.ctrl.modificareDeadLine(Integer.parseInt(numarField.getText()),Integer.parseInt(saptamanField.getText()),Integer.parseInt(deadlineField.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        dialogStage.close();
    }
    @FXML
    public void handleIesire()
    {
        dialogStage.close();
    }
    private void saveTema(Teme s) {

        try
        {
            Teme saved=ctrl.add(s);
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

    public void setService(TemeServices ctrlT, Stage dialogStage, Teme tema) {
        this.ctrl=ctrlT;
        this.dialogStage=dialogStage;
        this.tema=tema;
        if(tema!=null)
        {
            setFields(tema);
            numarField.setEditable(false);
        }
    }

    private void setFields(Teme tema) {
        numarField.setText(Integer.toString(tema.getNumar()));
        descriereField.setText(tema.getDescriere());
        String dead= Integer.toString(tema.getDeadline());
        deadlineField.setText(dead);

    }
}
