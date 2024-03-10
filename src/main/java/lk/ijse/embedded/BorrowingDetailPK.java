package lk.ijse.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BorrowingDetailPK implements Serializable {
    @Column(name = "user_name")
    private String userName;
    @Column(name = "book_id")
    private int bookID;

    public BorrowingDetailPK() {
    }

    public BorrowingDetailPK(String userName, int bookID) {
        this.userName = userName;
        this.bookID = bookID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}
