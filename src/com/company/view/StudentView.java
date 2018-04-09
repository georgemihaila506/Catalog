package com.company.view;

import com.company.Domain.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class StudentView {
    private StudentServicesView ctrlS;
    TableView<Student> tableView=new TableView<>();
    TextField idField=createField();
    TextField numeField=createField();
    TextField grupaField=createField();
    TextField emailField=createField();
    TextField tutoreField=createField();


    Button buttonAdd=new Button("Add");
    Button buttonDelete=new Button("Delete");
    Button buttonUpdate=new Button("Update");
    Button buttonClearAll=new Button("Clear all");

    private BorderPane borderPane;
    public StudentView(StudentServicesView ctrlS)
    {
        this.ctrlS=ctrlS;
        initView();
    }
    private TextField createField() {
        TextField textField=new TextField();
        return textField;
    }
    private void initView()
    {
        borderPane=new BorderPane();

        borderPane.setTop(initTop());
        borderPane.setLeft(initLeft());

        borderPane.setCenter(initCenter());
    }

    private AnchorPane initLeft() {
        AnchorPane leftAnchorPane=new AnchorPane();
        TableView<Student> tableView=createTableView();

        leftAnchorPane.getChildren().add(tableView);
        AnchorPane.setTopAnchor(tableView,20d);
        AnchorPane.setLeftAnchor(tableView,20d);

        return leftAnchorPane;
    }

    private TableView<Student> createTableView() {
        TableColumn<Student,String> columnId=new TableColumn<>("Id");
        TableColumn<Student,String> columnNume=new TableColumn<>("Nume");
        TableColumn<Student,String> columnGrupa=new TableColumn<>("Grupa");
        TableColumn<Student,String> columnEmail=new TableColumn<>("Email");
        TableColumn<Student,String> columnTutore=new TableColumn<>("Tutore");
        tableView.getColumns().addAll(columnId,columnNume,columnGrupa,columnEmail,columnTutore);

        columnId.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
        columnNume.setCellValueFactory(new PropertyValueFactory<Student,String>("nume"));
        columnGrupa.setCellValueFactory(new PropertyValueFactory<Student,String>("grupa"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
        columnTutore.setCellValueFactory(new PropertyValueFactory<Student,String>("CadruIndrumator"));

        tableView.setItems(ctrlS.getModel());
        tableView.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<Student>() {
                    @Override
                    public void changed(ObservableValue<? extends Student> observable,
                                        Student oldValue,Student newValue) {
                        ctrlS.showMessageDetails(newValue);
                    }
                });
        return  tableView;
    }

    private AnchorPane initTop()
    {
        AnchorPane topAnchorPane=new AnchorPane();

        ImageView img=new ImageView(new Image("com/company/calculator.gif"));
        img.setFitHeight(150);
        img.setFitWidth(200);
        topAnchorPane.getChildren().add(img);

        Label titleLabel=new Label("Interfata Student");
        topAnchorPane.getChildren().add(titleLabel);
        AnchorPane.setTopAnchor(titleLabel,40d);
        AnchorPane.setLeftAnchor(titleLabel,280d);
        titleLabel.setFont(new Font(30));
        return topAnchorPane;

    }
    public BorderPane getView()
    {
        return borderPane;
    }
    private AnchorPane initCenter()
    {
        AnchorPane centerPane=new AnchorPane();

        GridPane gridPane=createGridPane();

        centerPane.getChildren().add(gridPane);

        HBox hBox=createHBox();
        centerPane.getChildren().add(hBox);

        AnchorPane.setTopAnchor(gridPane,20d);
        AnchorPane.setLeftAnchor(hBox,8d);
        AnchorPane.setRightAnchor(gridPane,20d);
        AnchorPane.setBottomAnchor(hBox,240d);
        return centerPane;
    }
    private HBox createHBox()
    {
        HBox hBox=new HBox();

        hBox.getChildren().addAll(buttonAdd,buttonUpdate,buttonDelete,buttonClearAll);
        buttonAdd.setOnAction(ctrlS::handleAddStudent);
        buttonDelete.setOnAction(ctrlS::handleDeleteStudent);
        buttonUpdate.setOnAction(ctrlS::handleUpdateStudent);
        buttonClearAll.setOnAction(ctrlS::handleClearAll);
        return hBox;
    }
    private GridPane createGridPane() {
        GridPane gridPane=new GridPane();
        gridPane.add(createLabel("Id"),0,0);
        gridPane.add(createLabel("Nume"),0,1);
        gridPane.add(createLabel("Grupa"),0,2);
        gridPane.add(createLabel("Email"),0,3);
        gridPane.add(createLabel("Tutore"),0,4);

        gridPane.add(idField,1,0);
        gridPane.add(numeField,1,1);
        gridPane.add(grupaField,1,2);
        gridPane.add(emailField,1,3);
        gridPane.add(tutoreField,1,4);

        ColumnConstraints c1=new ColumnConstraints();
        c1.setPrefWidth(100d);
        ColumnConstraints c2=new ColumnConstraints();
        c2.setPrefWidth(200d);

        gridPane.getColumnConstraints().addAll(c1,c2);

        return gridPane;

    }

    private Label createLabel(String s) {
        Label l=new Label(s);
        l.setFont(new Font(15));
        return l;
    }

}
