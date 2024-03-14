package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.BorrowingDTO;
import lk.ijse.dto.tm.BorrowingTM;
import lk.ijse.dto.tm.DashboardTM;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.CountService;
import lk.ijse.service.custom.QueryService;

public class AdmDashController {
    @FXML
    private AnchorPane admDashPane;

    @FXML
    private TableView<DashboardTM> borrowTbl;

    @FXML
    private TableColumn<?, ?> colBId;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colCustomer;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TextField txtSearch;

    @FXML
    private Label lblBook;

    @FXML
    private Label lblBorrow;

    @FXML
    private Label lblBranch;

    @FXML
    private Label lblReturn;

    @FXML
    private Label lblUser;

    private final ObservableList<DashboardTM> dashboardTMS = FXCollections.observableArrayList();

    CountService countService = (CountService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.COUNT);

    QueryService queryService = (QueryService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.QUERY);

    public void initialize(){
        setCellValueFactory();
        loadAllBorrowings();
        setLabels();
    }

    private void setLabels() {
        String user = String.valueOf(countService.getUserCount());
        lblUser.setText(user);

        String book = String.valueOf(countService.getBookCount());
        lblBook.setText(book);

        String borrow = String.valueOf(countService.getBorrowCount());
        lblBorrow.setText(borrow);

        String returned = String.valueOf(countService.getReturnCount());
        lblReturn.setText(returned);

        String branch = String.valueOf(countService.getBranchCount());
        lblBranch.setText(branch);
    }

    private void setCellValueFactory(){
        colBId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
    }

    private void loadAllBorrowings() {
        dashboardTMS.clear();
        //get discovers
        for (BorrowingDTO dto : queryService.getAllBorrowings()) {
            dashboardTMS.add(new DashboardTM(
                    dto.getBookID(),
                    dto.getTitle(),
                    dto.getName(),
                    dto.getBorrowDate(),
                    dto.getDueDate())
            );
        }
        borrowTbl.setItems(dashboardTMS);
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        FilteredList<DashboardTM> filteredData = new FilteredList<>(dashboardTMS, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(BorrowingTM -> {

                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                String id = String.valueOf(BorrowingTM.getBookID());
                String borrow = String.valueOf(BorrowingTM.getBorrowDate());
                String due = String.valueOf(BorrowingTM.getDueDate());

                if(id.toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else if(BorrowingTM.getName().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else if(BorrowingTM.getTitle().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if(borrow.toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if(borrow.toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if(due.toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else
                    return false;
            });
        });

        SortedList<DashboardTM> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(borrowTbl.comparatorProperty());

        borrowTbl.setItems(sortedData);
    }
}
