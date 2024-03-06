package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AdminManageController {
    @FXML
    private AnchorPane admDashPane;

    @FXML
    private TableView<?> admTbl;

    @FXML
    private AnchorPane adminPane;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOrUpdateOnAction(ActionEvent event) {
        String name = txtName.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void tblOnAction(MouseEvent event) {

    }
}
