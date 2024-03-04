package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.dto.tm.BookTM;
import lk.ijse.entity.Branch;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.BookService;
import lk.ijse.service.custom.BranchService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdmBookController {
    @FXML
    private TableView<BookTM> BookTbl;

    @FXML
    private AnchorPane admDashPane;

    @FXML
    private AnchorPane bookPane;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cmbBranch;

    @FXML
    private ComboBox<String> cmbGenre;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colAvailable;

    @FXML
    private TableColumn<?, ?> colBId;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TextField txtAuthor;

    @FXML
    private ComboBox<String> cmbAvailable;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtTitle;

    private int index;

    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);

    private final ObservableList<BookTM> bookTMS = FXCollections.observableArrayList();

    BranchService branchService = (BranchService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BRANCH);

    public void initialize(){
        setCellValueFactory();
        loadAllBooks();
        setComboData();
        initUi();
    }

    private void setCellValueFactory(){
        colBId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        colAvailable.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }
    private void loadAllBooks() {
        bookTMS.clear();
        //get books
        for (BookDTO bookDTO : bookService.getAllBooks()) {
            bookTMS.add(new BookTM(
                    bookDTO.getBookID(),
                    bookDTO.getTitle(),
                    bookDTO.getAuthor(),
                    bookDTO.getGenre(),
                    bookDTO.getBranch(),
                    bookDTO.getAvailability())
            );
        }
        BookTbl.setItems(bookTMS);
    }

    private void setComboData() {
        ObservableList<String> oblistGenre = FXCollections.observableArrayList();
        oblistGenre.add(0, "Educational");
        oblistGenre.add(1, "Adult");
        oblistGenre.add(2, "Detective");
        oblistGenre.add(3, "Horror");
        oblistGenre.add(4, "Historical");
        oblistGenre.add(5, "Scientific");
        oblistGenre.add(6, "Child");
        oblistGenre.add(7, "Comic");

        cmbGenre.setItems(oblistGenre);

        ObservableList<String> oblistAvailable = FXCollections.observableArrayList();
        oblistAvailable.add(0, "YES");
        oblistAvailable.add(1, "NO");

        cmbAvailable.setItems(oblistAvailable);

        ObservableList<String> oblistBranch = FXCollections.observableArrayList();
        List<BranchDTO> branchDTOS =  branchService.getAllBranches();

        for (BranchDTO branchDTO : branchDTOS){
            oblistBranch.add(branchDTO.getBranchName());
        }
        cmbBranch.setItems(oblistBranch);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if(type.orElse(no) == yes) {
            int id = Integer.parseInt(txtId.getText());
            //delete item
            bookService.deleteBook(id);
            System.out.println("deleted");
        }
        loadAllBooks();
        initUi();
    }

    @FXML
    void btnSaveOrUpdateOnAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = cmbGenre.getValue();
        String branch = cmbBranch.getValue();
        String availability = cmbAvailable.getValue();

        if(btnSave.getText().equals("SAVE")){
            boolean bookSaved = bookService.saveBook(new BookDTO(id,title,author,genre,branch,availability));

            if (bookSaved){
                System.out.println("book Saved");
            }
        } else if (btnSave.getText().equals("UPDATE")) {
            bookService.updateBook(new BookDTO(id,title,author,genre,branch,availability));
        }

        loadAllBooks();
        initUi();
    }

    private void initUi() {
        txtId.clear();
        txtTitle.clear();
        txtAuthor.clear();
        cmbGenre.setPromptText("Genre");
        cmbBranch.setPromptText("Available Branch");
        cmbAvailable.setPromptText("Availability");
        btnSave.setText("SAVE");
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        FilteredList<BookTM> filteredData = new FilteredList<>(bookTMS, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(BookTM -> {

                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                String id = String.valueOf(BookTM.getBookID());

                if(id.toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else if(BookTM.getTitle().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else if(BookTM.getAuthor().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if(BookTM.getGenre().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }else if(BookTM.getBranch().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else
                    return false;
            });
        });

        SortedList<BookTM> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(BookTbl.comparatorProperty());

        BookTbl.setItems(sortedData);
    }

    @FXML
    void tblOnAction(MouseEvent event) {
        index = BookTbl.getSelectionModel().getSelectedIndex();

        if(index <= -1){
            return;
        }

        txtId.setText(colBId.getCellData(index).toString());
        txtTitle.setText(colTitle.getCellData(index).toString());
        txtAuthor.setText(colAuthor.getCellData(index).toString());
        cmbGenre.setValue(colGenre.getCellData(index).toString());
        cmbBranch.setValue(colBranch.getCellData(index).toString());
        cmbAvailable.setValue(colAvailable.getCellData(index).toString());

        btnSave.setText("UPDATE");
    }

    @FXML
    void btnAddBranchOnAction(ActionEvent event){
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admBrach.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("BRANCHES");
        stage.centerOnScreen();
        stage.show();
    }

}
