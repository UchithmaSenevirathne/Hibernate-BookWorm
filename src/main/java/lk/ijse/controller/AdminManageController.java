package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.dto.tm.AdminTM;
import lk.ijse.dto.tm.BookTM;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.UserService;
import lk.ijse.validate.Validation;

import java.util.Optional;

public class AdminManageController {
    @FXML
    private AnchorPane admDashPane;

    @FXML
    private TableView<AdminTM> admTbl;

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

    private int index;

    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);
    private final ObservableList<AdminTM> adminTMS = FXCollections.observableArrayList();

    public void initialize(){
        setCellValueFactory();
        loadAllAdmins();
        initUi();
    }

    private void setCellValueFactory(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    private void loadAllAdmins() {
        adminTMS.clear();
        //get admins
        for (UserDTO userDTO : userService.getAdmins()) {
            adminTMS.add(new AdminTM(
                    userDTO.getName(),
                    userDTO.getUserName(),
                    userDTO.getPassword())
            );
        }
        admTbl.setItems(adminTMS);
    }

    private void initUi(){
        txtName.clear();
        txtPassword.clear();
        txtUserName.clear();

        btnSave.setText("SAVE");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if(type.orElse(no) == yes) {
            String username = txtUserName.getText();
            //delete admin
            userService.deleteAdmin(username);
            System.out.println("deleted");
        }
        loadAllAdmins();
        initUi();
    }

    @FXML
    void btnSaveOrUpdateOnAction(ActionEvent event) {
        String name = txtName.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        if (validateAdmin(name, userName, password)) {

            if (btnSave.getText().equals("SAVE")) {
                boolean saved = userService.saveUser(new UserDTO(userName, name, password, "ADM"));

                if (saved) {
                    System.out.println("saved user");
                }
            } else if (btnSave.getText().equals("UPDATE")) {
                userService.updateUser(new UserDTO(userName, name, password, "ADM"));
            }

            loadAllAdmins();
            initUi();
        }
    }

    private boolean validateAdmin(String name, String userName, String password) {
        if(!Validation.validation(name, txtName,"[A-Za-zA-Z]+")){
            return false;
        }
        if(!Validation.validation(userName, txtUserName,"[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
            return false;
        }
        if(!Validation.validation(password, txtPassword,"(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}")){
            return false;
        }
        return true;
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        FilteredList<AdminTM> filteredData = new FilteredList<>(adminTMS, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(AdminTM -> {

                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if(AdminTM.getName().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else if(AdminTM.getUserName().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else if(AdminTM.getPassword().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else
                    return false;
            });
        });

        SortedList<AdminTM> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(admTbl.comparatorProperty());

        admTbl.setItems(sortedData);
    }

    @FXML
    void tblOnAction(MouseEvent event) {
        index = admTbl.getSelectionModel().getSelectedIndex();

        if(index <= -1){
            return;
        }

        txtName.setText(colName.getCellData(index).toString());
        txtUserName.setText(colUserName.getCellData(index).toString());
        txtPassword.setText(colPassword.getCellData(index).toString());

        btnSave.setText("UPDATE");
    }
}
