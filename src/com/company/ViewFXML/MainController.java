package com.company.ViewFXML;

import com.company.Domain.Nota;
import com.company.Domain.Pereche;
import com.company.Domain.Student;
import com.company.Domain.Teme;
import com.company.Repository.RepoFileNote;
import com.company.Repository.RepoInFile;
import com.company.Services.FilterServices;
import com.company.Services.NoteServices;
import com.company.Services.StudentServices;
import com.company.Services.TemeServices;
import com.company.Validatoare.NotaValidator;
import com.company.Validatoare.StudentValidator;
import com.company.Validatoare.TemeValidator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.io.IOException;


public class MainController {
    RepoInFile<Student,String> repo=new RepoInFile<Student,String>("file1.txt",new StudentValidator());
    RepoInFile<Teme,Integer> repo2=new RepoInFile<Teme,Integer>("file2.txt",new TemeValidator());
    RepoInFile<Nota,Pereche> repo3=new RepoInFile<Nota,Pereche>("file3.txt",new NotaValidator());
    RepoFileNote repo4=new RepoFileNote("./src/com/company/NoteS.txt",new NotaValidator());
    private StudentServices ctrlS;
    private TemeServices ctrlT;
    private NoteServices ctrlN;
    private FilterServices ctrlF;
    //@FXML
    //Image newGame = new Image("studentIcon.png");
    public MainController()
    {
        this.ctrlS=new StudentServices(repo);
        this.ctrlT=new TemeServices(repo2);
        this.ctrlN=new NoteServices(repo3,repo2);
        this.ctrlF=new FilterServices(repo,repo2,repo3);
    }
    @FXML
    private Button buttonStudenti;
    @FXML
    private Button buttonTeme;
    @FXML
    private Button buttonNote;
    @FXML
    private BorderPane borderPane;
    @FXML
    private VBox vBox;
    @FXML
    public void handleButtonStudenti()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("StudentViewFXML.fxml"));
        try
        {
            //BorderPane rootL=(BorderPane) loader.load();
            HBox rootL=(HBox) loader.load();
            StudentViewController ctrlV=loader.getController();
            ctrlV.setService(ctrlS,ctrlF);
            borderPane.setRight(rootL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleButtonTeme()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("TemeViewFXML.fxml"));
        try
        {
            //BorderPane rootL=(BorderPane) loader.load();
            HBox rootL=(HBox) loader.load();
            borderPane.setRight(rootL);
            TemeViewController temeCtrl=loader.getController();
            temeCtrl.setService(ctrlT,ctrlF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleButtonNote()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("NoteViewFXML.fxml"));
        try
        {
            //BorderPane rootL=(BorderPane) loader.load();
            HBox rootL=(HBox) loader.load();
            NoteViewControler noteViewControler=loader.getController();
            noteViewControler.setService(ctrlN,ctrlF,ctrlT,ctrlS);
            borderPane.setRight(rootL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize()
    {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("Welcome.fxml")); //URL
//            try {
//                BorderPane rootLayout = (BorderPane) loader.load();
//                //borderPane=rootLayout;
//                borderPane.setCenter(rootLayout);
//                borderPane.getCenter()
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

    }

}
