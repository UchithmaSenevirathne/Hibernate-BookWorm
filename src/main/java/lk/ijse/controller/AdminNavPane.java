package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.navigation.Navigation;

import java.io.IOException;

public class AdminNavPane {
    @FXML
    private AnchorPane admPane;

    @FXML
    private AnchorPane navPane;

    public void initialize(){
        loadDashboard();
    }

    private void loadDashboard() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdmDash.fxml"));
        AnchorPane dashboard = null;
        try {
            dashboard = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        admPane.getChildren().setAll(dashboard);
    }

    @FXML
    void adminOnAction(MouseEvent event) {

    }

    @FXML
    void bookOnAction(MouseEvent event) {
        try {
            Navigation.navigate(Routes.BOOK, admPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void borrowingOnAction(MouseEvent event) {

    }

    @FXML
    void branchOnAction(MouseEvent event) {

    }

    @FXML
    void dashBoardOnAction(MouseEvent event) {
        try {
            Navigation.navigate(Routes.ADMDASHBOARD, admPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void logoutOnAction(MouseEvent event) {
        try {
            Navigation.navigate(Routes.LOGIN, navPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
