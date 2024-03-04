package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.UserDTO;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.UserService;

import java.io.IOException;

public class SignUpController {
    @FXML
    private AnchorPane signUpPane;

    @FXML
    private TextField txtConfirmPwd;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String name = txtName.getText();
        String userName = txtUserName.getText();
        String password = txtConfirmPwd.getText();

        try {
            boolean saveUser = userService.saveUser(new UserDTO(userName, name, password, "CUS"));

            if (saveUser){
                Parent rootNode = null;
                try {
                    rootNode = FXMLLoader.load(getClass().getResource("/view/SignIn.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Scene scene = new Scene(rootNode);
                Stage stage = (Stage) this.signUpPane.getScene().getWindow();
                stage.setTitle("LOGIN");
                stage.setScene(scene);
                stage.centerOnScreen();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
