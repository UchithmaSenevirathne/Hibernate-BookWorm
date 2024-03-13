package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.BorrowingDTO;
import lk.ijse.dto.OverDueDTO;
import lk.ijse.dto.tm.BorrowingTM;
import lk.ijse.dto.tm.OverDueTM;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.QueryService;

public class AdmOverDueController {

    @FXML
    private AnchorPane OverDuePane;

    @FXML
    private TableView<OverDueTM> OverDueTable;

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

    private final ObservableList<OverDueTM> overDueTMS = FXCollections.observableArrayList();

    QueryService queryService = (QueryService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.QUERY);

    public void initialize(){
        setCellValueFactory();
        loadAllOverDues();
    }

    private void setCellValueFactory(){
        colBId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
    }

    private void loadAllOverDues() {
        overDueTMS.clear();
        //get overDues
        for (OverDueDTO dto : queryService.getAllOverDues()) {
            overDueTMS.add(new OverDueTM(
                    dto.getBookID(),
                    dto.getTitle(),
                    dto.getName(),
                    dto.getBorrowDate(),
                    dto.getDueDate())
            );
        }
        OverDueTable.setItems(overDueTMS);
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        FilteredList<OverDueTM> filteredData = new FilteredList<>(overDueTMS, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(OverDueTM -> {

                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                String id = String.valueOf(OverDueTM.getBookID());
                String borrow = String.valueOf(OverDueTM.getBorrowDate());
                String due = String.valueOf(OverDueTM.getDueDate());

                if(id.toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else if(OverDueTM.getName().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else if(OverDueTM.getTitle().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if(borrow.toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if(due.toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else
                    return false;
            });
        });

        SortedList<OverDueTM> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(OverDueTable.comparatorProperty());

        OverDueTable.setItems(sortedData);
    }
}
