package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.dto.BorrowingDTO;
import lk.ijse.dto.LibraryDTO;
import lk.ijse.dto.tm.BorrowingTM;
import lk.ijse.dto.tm.LibraryTM;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.QueryService;

import javax.crypto.spec.PSource;

import static lk.ijse.controller.SignInController.LoginPageUserName;

public class CusLibraryController {

    @FXML
    private TableView<LibraryTM> bookTable;

    @FXML
    private TableColumn<?, ?> colBorrow;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colDue;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TextField txtSearch;

    private CustomerNavPane customerNavPane;

    private final ObservableList<LibraryTM> libraryTMS = FXCollections.observableArrayList();

    QueryService queryService = (QueryService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.QUERY);

    public void initialize(){
        setCellValueFactory();
        loadLibrary();
    }

    private void setCellValueFactory() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colBorrow.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colDue.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
    }

    private void loadLibrary() {
        libraryTMS.clear();

        System.out.println(LoginPageUserName);

        //get discovers
        for (LibraryDTO dto : queryService.getLibrary(LoginPageUserName)) {
            libraryTMS.add(new LibraryTM(
                    dto.getTitle(),
                    dto.getBranchName(),
                    dto.getBorrowDate(),
                    dto.getDueDate())
            );
        }
        bookTable.setItems(libraryTMS);
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void refreshPage(MouseEvent event) {

    }

    @FXML
    void tblOnAction(MouseEvent event) {

    }
}
