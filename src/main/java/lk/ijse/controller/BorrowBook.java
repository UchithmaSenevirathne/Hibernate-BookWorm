package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BorrowBook {
    @FXML
    private AnchorPane borrowPane;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtAvailability;

    @FXML
    private TextField txtBId;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtBranch;

    Stage stage;

    @FXML
    void BorrowOnAction(ActionEvent event) {

    }

    @FXML
    void CancleOnAction(ActionEvent event) {
        stage = (Stage) borrowPane.getScene().getWindow();
        stage.close();
    }


    public void setData(int bId, String title, String author, String genre, String availability, String branch) {
        txtBId.setText(String.valueOf(bId));
        txtTitle.setText(title);
        txtAuthor.setText(author);
        txtGenre.setText(genre);
        txtAvailability.setText(availability);
        txtBranch.setText(branch);
    }
}
