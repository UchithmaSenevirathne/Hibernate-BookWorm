package lk.ijse.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.controller.Routes;

import java.io.IOException;

public class Navigation {
    public static AnchorPane anchorPane;


    public static void navigate(Routes routes, AnchorPane anchorPane) throws IOException {
        Navigation.anchorPane = anchorPane;
        Navigation.anchorPane.getChildren().clear();
        Stage window = (Stage) Navigation.anchorPane.getScene().getWindow();

        switch (routes) {
            case LOGIN:
                initUI("SignIn.fxml");
                window.setTitle("LOGIN");
                break;
            case ADMDASHBOARD:
                initUI("AdmDash.fxml");
                window.setTitle("DASHBOARD");
                break;
            case BOOK:
                initUI("AdmBook.fxml");
                window.setTitle("BOOKS");
                break;
            case ADMIN:
                initUI("adminManage.fxml");
                window.setTitle("ADMIN");
                break;
            case CUSDASHBOARD:
                initUI("cusDiscover.fxml");
                window.setTitle("DISCOVER");
                break;
            case BORROWING:
                initUI("AdmBorrowingManage.fxml");
                window.setTitle("BORROWINGS");
                break;
            case OVERDUE:
                initUI("AdmOverDue.fxml");
                window.setTitle("OVERDUES");
                break;
            case LIBRARRY:
                initUI("CusLibrary.fxml");
                window.setTitle("LIBRARY");
                break;
            case PROFILE:
                initUI("CusProfile.fxml");
                window.setTitle("PROFILE");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable Window found!").show();
        }
    }

    private static void initUI(String location) throws IOException {
        Navigation.anchorPane.getChildren().add(FXMLLoader.load(
                Navigation.class.getResource("/view/" + location)
        ));
    }
}
