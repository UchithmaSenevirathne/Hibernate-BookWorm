package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.UserService;

import static lk.ijse.controller.SignInController.LoginPageUserName;

public class CusProfileController {
    @FXML
    private Label lblName;

    @FXML
    private TextField txtNewPwd;

    @FXML
    private TextField txtOldPassword;

    @FXML
    private TextField txtOldUserName;

    @FXML
    private TextField txtRePassword;

    @FXML
    private TextField txtUserName;

    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);

    public void initialize(){
        String name = userService.getName(LoginPageUserName);
        lblName.setText(name);
    }
    @FXML
    void confirmButtonOnAction(ActionEvent event) {
        String name = lblName.getText();
        String oldPwd = txtOldPassword.getText();
        String newPwd = txtNewPwd.getText();
        String rePwd = txtRePassword.getText();
        String newUserName = txtUserName.getText();
        String olsUserName = txtOldUserName.getText();

        boolean isOld = userService.checkOldPwd(name, olsUserName, oldPwd);

        if (isOld){
            if (newPwd.equals(rePwd)) {
                boolean updateUser = userService.updateProfile(olsUserName, newUserName, rePwd);

                if (updateUser){
                    System.out.println("User Profile Updated");
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "New Password and ReEntered Password not Same").show();
            }
        }
    }
}
