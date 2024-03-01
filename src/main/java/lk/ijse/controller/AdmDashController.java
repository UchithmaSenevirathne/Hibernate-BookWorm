package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AdmDashController {
    @FXML
    private AnchorPane admDashPane;

    @FXML
    private TableView<?> borrowTbl;

    @FXML
    private TableColumn<?, ?> colBId;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colCustomer;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }
}
