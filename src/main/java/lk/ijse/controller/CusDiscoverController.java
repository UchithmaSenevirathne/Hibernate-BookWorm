package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.dto.tm.AdminTM;
import lk.ijse.dto.tm.BookTM;
import lk.ijse.dto.tm.DiscoverTM;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.BookService;
import lk.ijse.service.custom.UserService;

import java.io.IOException;

public class CusDiscoverController {
    @FXML
    private TableView<DiscoverTM> bookTable;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colBId;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TextField txtSearch;

    private int index;

    private final ObservableList<DiscoverTM> discoverTMS = FXCollections.observableArrayList();

    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);

    public void initialize(){
        setCellValueFactory();
        loadAllDiscovers();
    }

    private void setCellValueFactory(){
        colBId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }

    private void loadAllDiscovers() {
        discoverTMS.clear();
        //get discovers
        for (BookDTO bookDTO : bookService.getAllBooks()) {
            discoverTMS.add(new DiscoverTM(
                    bookDTO.getBookID(),
                    bookDTO.getTitle(),
                    bookDTO.getAuthor(),
                    bookDTO.getGenre(),
                    bookDTO.getAvailability())
            );
        }
        bookTable.setItems(discoverTMS);
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void tblOnAction(MouseEvent event) {
        index = bookTable.getSelectionModel().getSelectedIndex();

        if(index <= -1){
            return;
        }

        int bId = Integer.parseInt(colBId.getCellData(index).toString());
        String title = colTitle.getCellData(index).toString();
        String author = colAuthor.getCellData(index).toString();
        String genre = colGenre.getCellData(index).toString();
        String availability = colAvailability.getCellData(index).toString();
        String branch = bookService.getBranch(bId);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BorrowBook.fxml"));

            Parent rootNode = loader.load();

            BorrowBook borrowBook = loader.getController();

            borrowBook.setData(
                bId,title,author,genre,availability,branch
            );

            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Borrow Book");
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
