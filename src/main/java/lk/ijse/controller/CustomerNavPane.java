package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.navigation.Navigation;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.UserService;

import java.io.IOException;

public class CustomerNavPane {
    @FXML
    private AnchorPane cusNavePane;

    @FXML
    private AnchorPane cusPane;

//    private String username;

    private String user_name;

    @FXML
    public Label lblUserName;

    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);

    public void initialize(){
//        user_name = lblUserName.getText();
        loadDiscover();
    }

    private void loadDiscover() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cusDiscover.fxml"));
        AnchorPane discover = null;
        try {
            discover = loader.load();

            CusDiscoverController cusDiscoverController = loader.getController();

//            name = lblUserName.getText();

//            System.out.println(user_name);

            cusDiscoverController.setUserName(
                    this
            );

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
            Navigation.navigate(Routes.LIBRARRY, cusPane);
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

    @FXML
    void profileOnAction(MouseEvent event) {
        try {
            Navigation.navigate(Routes.PROFILE, cusPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUserName(String username) {
        String name = userService.getName(username);
        lblUserName.setText(name);
    }
}
