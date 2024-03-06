package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.navigation.Navigation;

import java.io.IOException;

public class CustomerNavPane {
    @FXML
    private AnchorPane cusNavePane;

    @FXML
    private AnchorPane cusPane;

    public void initialize(){
        loadDiscover();
    }

    private void loadDiscover() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cusDiscover.fxml"));
        AnchorPane discover = null;
        try {
            discover = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        cusPane.getChildren().setAll(discover);
    }
    @FXML
    void discoverOnAction(MouseEvent event) {
        try {
            Navigation.navigate(Routes.CUSDASHBOARD, cusPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void libraryOnAction(MouseEvent event) {
        try {
            Navigation.navigate(Routes.LIBRARY, cusPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void logoutOnAction(MouseEvent event) {
        try {
            Navigation.navigate(Routes.LOGIN, cusNavePane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
