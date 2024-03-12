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
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BorrowingDTO;
import lk.ijse.dto.tm.BorrowingTM;
import lk.ijse.dto.tm.DiscoverTM;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.BorrowingDetailsService;
import lk.ijse.service.custom.QueryService;
import lk.ijse.service.custom.impl.QueryServiceImpl;

public class AdmBorrowingManageController {
    @FXML
    private AnchorPane AdmBorrowPane;

    @FXML
    private TableView<BorrowingTM> BorrowTable;

    @FXML
    private TableColumn<?, ?> colBID;

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

    private final ObservableList<BorrowingTM> borrowingTMS = FXCollections.observableArrayList();

    QueryService queryService = (QueryService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.QUERY);

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

    }

    @FXML
    void tableOnAction(MouseEvent event) {

    }
}
