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
import lk.ijse.dto.BorrowingDTO;
import lk.ijse.dto.tm.BookTM;
import lk.ijse.dto.tm.BorrowingTM;
import lk.ijse.dto.tm.DiscoverTM;
import lk.ijse.entity.BorrowingDetails;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.BorrowingDetailsService;
import lk.ijse.service.custom.QueryService;
import lk.ijse.service.custom.impl.QueryServiceImpl;

import java.sql.Timestamp;
import java.util.Optional;

public class AdmBorrowingManageController {
    @FXML
    private AnchorPane AdmBorrowPane;

    @FXML
    private TableView<BorrowingTM> BorrowTable;

    @FXML
    private TableColumn<BorrowingTM, Integer> colBID;

    @FXML
    private TableColumn<?, ?> colBorrow;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colDue;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colReturn;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TextField txtSearch;

    private int index;

    private final ObservableList<BorrowingTM> borrowingTMS = FXCollections.observableArrayList();

    QueryService queryService = (QueryService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.QUERY);

    BorrowingDetailsService borrowingDetailsService = (BorrowingDetailsService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BORROWINGDETAILS);

    public void initialize(){
        setCellValueFactory();
        loadAllBorrowings();
    }

    private void setCellValueFactory(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colBorrow.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colDue.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colReturn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
    }

    private void loadAllBorrowings() {
        borrowingTMS.clear();
        //get discovers
        for (BorrowingDTO dto : queryService.getAllBorrowings()) {
            borrowingTMS.add(new BorrowingTM(
                    dto.getName(),
                    dto.getBookID(),
                    dto.getTitle(),
                    dto.getBranchName(),
                    dto.getBorrowDate(),
                    dto.getDueDate(),
                    dto.getReturnDate())
            );
        }
        BorrowTable.setItems(borrowingTMS);
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        FilteredList<BorrowingTM> filteredData = new FilteredList<>(borrowingTMS, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(BorrowingTM -> {

                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                String id = String.valueOf(BorrowingTM.getBookID());
                String borrow = String.valueOf(BorrowingTM.getBorrowDate());
                String due = String.valueOf(BorrowingTM.getDueDate());
                String ret = String.valueOf(BorrowingTM.getReturnDate());

                if(id.toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else if(BorrowingTM.getName().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else if(BorrowingTM.getTitle().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if(BorrowingTM.getBranchName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if(borrow.toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if(borrow.toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if(due.toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if(ret.toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else
                    return false;
            });
        });

        SortedList<BorrowingTM> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(BorrowTable.comparatorProperty());

        BorrowTable.setItems(sortedData);
    }

    @FXML
    void tableOnAction(MouseEvent event) {
        index = BorrowTable.getSelectionModel().getSelectedIndex();

        if(index <= -1){
            return;
        }

        int id = colBID.getCellData(index);

        System.out.println("borrowing book : "+id);

        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Make This Book As Returned Book", yes, no).showAndWait();

        if(type.orElse(no) == yes) {
            boolean update = borrowingDetailsService.updateReturn(id);

            if (update){
                System.out.println("Returned Book");
            }
        }
        loadAllBorrowings();
    }
}
