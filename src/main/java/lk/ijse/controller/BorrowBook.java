package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BorrowingDetailsDTO;
import lk.ijse.embedded.BorrowingDetailPK;
import lk.ijse.entity.Book;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.custom.BookService;
import lk.ijse.service.custom.BorrowingDetailsService;
import lk.ijse.service.custom.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

public class BorrowBook {
    @FXML
    private AnchorPane borrowPane;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtAvailability;

    @FXML
    private TextField txtBId;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtBranch;

    private String username;

//    private CustomerNavPane customerNavPane;

    BorrowingDetailsService borrowingDetailsService = (BorrowingDetailsService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BORROWINGDETAILS);

    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);

    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);


    Stage stage;

    @FXML
    void BorrowOnAction(ActionEvent event) {
        String userName = userService.getUserName(username);
        int bookId = Integer.parseInt(txtBId.getText());

        //check if the books are returned
        if (isreturned(userName)) {

            //check book counts that not returned
            if (lessTwo(userName)) {
                Timestamp borrowingDateTime = new Timestamp(System.currentTimeMillis());

                // Convert Timestamp to LocalDateTime
                LocalDateTime localDateTime = borrowingDateTime.toLocalDateTime();

                // Add a week
                int weeksToAdd = 1;
                LocalDateTime updatedTimestamp = localDateTime.plusWeeks(weeksToAdd);

                // Convert back to Timestamp
                Timestamp dueDateTime = Timestamp.valueOf(updatedTimestamp);

                System.out.println("Original Timestamp: " + borrowingDateTime);
                System.out.println("Updated Timestamp (after adding a week): " + dueDateTime);

                BorrowingDetailPK borrowingDetailPK = new BorrowingDetailPK(userName, bookId);

                BorrowingDetailsDTO borrowingDetailsDTO = new BorrowingDetailsDTO(borrowingDetailPK, borrowingDateTime, dueDateTime, null);

                boolean saved = borrowingDetailsService.save(borrowingDetailsDTO);

                if (saved) {
                    System.out.println("saved borrow");
//                    BookDTO bookDTO = bookService.getBook(bookId);
//
//                    System.out.println(bookDTO);
//
//                    bookService.updateBook(new BookDTO(bookDTO.getBookID(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getGenre(), bookDTO.getBranchName(), "NO", bookDTO.getBranchId()));
                }
            }else {
                new Alert(Alert.AlertType.INFORMATION, "You cannot borrow more than two books").show();
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION, "You should return book first").show();
        }

    }

    private boolean lessTwo(String userName) {
        boolean lessTwo = borrowingDetailsService.checkNumberOfBooks(userName);
        return lessTwo;
    }

    private boolean isreturned(String userName) {
        boolean returned = borrowingDetailsService.checkReturns(userName);
        return returned;
    }

    @FXML
    void CancleOnAction(ActionEvent event) {
        stage = (Stage) borrowPane.getScene().getWindow();
        stage.close();
    }


    public void setData(int bId, String title, String author, String genre, String availability, String branch) {
        txtBId.setText(String.valueOf(bId));
        txtTitle.setText(title);
        txtAuthor.setText(author);
        txtGenre.setText(genre);
        txtAvailability.setText(availability);
        txtBranch.setText(branch);
    }

    public void setUserName(String username) {
        System.out.println(username);
        this.username = username;
    }
}
