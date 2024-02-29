package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.UserService;

import java.io.IOException;

public class SignInController {
    @FXML
    private AnchorPane signInPane;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        try {

            boolean isIn = userService.searchUser(userName, password);
            if (!isIn) {
                new Alert(Alert.AlertType.WARNING, "Invalid UserName or Password").show();
            } else {
                isAdmin(userName, password);
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void isAdmin(String userName, String password) {
        try {

            boolean isAdmin = userService.checkAdmin(userName,password);

            if(isAdmin){
                System.out.println("admin");
//                Parent rootNode = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
//
//                Scene scene = new Scene(rootNode);
//                Stage stage = (Stage) this.signInPane.getScene().getWindow();
//                stage.setTitle("Dashboard Form");
//                stage.setScene(scene);
//                stage.centerOnScreen();
            }else{
                System.out.println("customer");
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DriverSchedule.fxml"));
//
//                Parent rootNode = loader.load();
//
//                DriverScheduleController driverScheduleController = loader.getController();
//
//                List<ScheduleDTO> dtoList = userBO.getSchedule(userName);
//                System.out.println(dtoList);
//                driverScheduleController.setScheduleData(dtoList, userName);
//
//                Scene scene = new Scene(rootNode);
//                Stage stage = (Stage) this.rootNode.getScene().getWindow();
//                stage.setTitle("Driver Schedule Form");
//                stage.setScene(scene);
//                stage.centerOnScreen();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void OpenSignUpOnAction(MouseEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.signInPane.getScene().getWindow();
        stage.setTitle("REGISTER");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
