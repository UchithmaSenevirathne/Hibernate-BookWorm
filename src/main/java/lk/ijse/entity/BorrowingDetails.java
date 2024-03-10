package lk.ijse.entity;

import lk.ijse.embedded.BorrowingDetailPK;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "borrowing_detail")
public class BorrowingDetails {

    @EmbeddedId
    private BorrowingDetailPK borrowingDetailPK;
    @CreationTimestamp
    @Column(name = "borrow_date")
    private Timestamp borrowDate;
    @CreationTimestamp
    @Column(name = "due_date")
    private Timestamp dueDate;
    @CreationTimestamp
    @Column(name = "return_date")
    private Timestamp returnDate;

    @ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", insertable = false, updatable = false)
    private Book book;

    public BorrowingDetails() {
    }

    public BorrowingDetails(BorrowingDetailPK borrowingDetailPK, Timestamp borrowDate, Timestamp dueDate, Timestamp returnDate) {
        this.borrowingDetailPK = borrowingDetailPK;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public BorrowingDetailPK getBorrowingDetailPK() {
        return borrowingDetailPK;
    }

    public void setBorrowingDetailPK(BorrowingDetailPK borrowingDetailPK) {
        this.borrowingDetailPK = borrowingDetailPK;
    }

    public Timestamp getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Timestamp borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

}
