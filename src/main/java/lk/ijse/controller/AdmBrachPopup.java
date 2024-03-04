package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.dto.tm.BookTM;
import lk.ijse.dto.tm.BranchTM;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.BranchService;

public class AdmBrachPopup {
    @FXML
    private TextField branchId;

    @FXML
    private TextField branchName;

    @FXML
    private AnchorPane branchPane;

    @FXML
    private TableView<BranchTM> branchTbl;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colId;

    private final ObservableList<BranchTM> branchTMS = FXCollections.observableArrayList();

    BranchService branchService = (BranchService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BRANCH);

    private AdmBookController admBookController;
    public void initialize(){
        setCellValueFactory();
        loadAllBranches();
        initUi();
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branchName"));
    }

    private void loadAllBranches() {
        branchTMS.clear();
        //get books
        for (BranchDTO branchDTO : branchService.getAllBranches()) {
            branchTMS.add(new BranchTM(
                    branchDTO.getBranchId(),
                    branchDTO.getBranchName())
            );
        }
        branchTbl.setItems(branchTMS);
    }

    private void initUi() {
        branchId.clear();
        branchName.clear();
    }

    @FXML
    void addBranchOnAction(ActionEvent event) {
        int id = Integer.parseInt(branchId.getText());
        String branch = branchName.getText();

        boolean isSaved = branchService.addBranch(new BranchDTO(id, branch));

        if (isSaved){
            System.out.println("added");
        }

        loadAllBranches();
        initUi();
    }

    @FXML
    void closeBranchOnAction(ActionEvent event) {

    }
}
